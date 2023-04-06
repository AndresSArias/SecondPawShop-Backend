/* ---------------------------------------------------- */
/*              Creado por JSJSOLUTIONS	                */
/* Script que crea la base de datos con sus objetos.    */
/* ---------------------------------------------------- */

/*Creación de la base de datos de SecondPawShop*/
CREATE DATABASE IF NOT EXISTS  DBSECONDPAWSHOP;

/*Establecer la base de datos para hacer las operaciones de creación*/
USE DBSECONDPAWSHOP;

/*Se desactiva temporalmente la verificación de llaves foraneas para la creación de todas la bd*/
SET FOREIGN_KEY_CHECKS=0; 

/* Drop de las tablas si existe */
DROP TABLE IF EXISTS PRODUCTO CASCADE;

DROP TABLE IF EXISTS USUARIO CASCADE;

DROP TABLE IF EXISTS VENTA CASCADE;

/* Creación de las tablas */

CREATE TABLE PRODUCTO
(
	IDUSUARIOFK VARCHAR(15) NOT NULL,
	NOMBRE VARCHAR(50) NOT NULL,
	CATEGORIA VARCHAR(10) NOT NULL,
	DESCRIPCION VARCHAR(200) NOT NULL,
	CANTIDAD INT NOT NULL,
	PRECIO INT NOT NULL,
	IMAGEN VARCHAR(200) NOT NULL,
	ESTADO VARCHAR(15) NOT NULL,
	CONSTRAINT PK_TABLE_ONE PRIMARY KEY (NOMBRE ASC, IDUSUARIOFK ASC)
)
COMMENT = 'Tabla que contiene los registros de los productos de la tienda. ya sea que existan o no.'

;

CREATE TABLE USUARIO
(
	IDUSUARIO VARCHAR(15) NOT NULL COMMENT 'Clave primaria de la tabla Usuario',
	NOMBRE VARCHAR(50) NOT NULL COMMENT 'Columna que continene el nombre de los Usuarios',
	APELLIDO VARCHAR(50) NOT NULL COMMENT 'Columna que contiene los apellidos de los usuarios.',
	CORREO VARCHAR(50) NOT NULL COMMENT 'Columna que contendrá los correos de los usuarios registrados',
	CELULAR VARCHAR(10) NOT NULL COMMENT 'Columna que contendrá el número de contacto de los usuarios',
	DIRECCION VARCHAR(50) NOT NULL COMMENT 'Columna que contiene los registros de las direcciones de los usuarios del sistema.',
	CONTRASENA VARCHAR(20) NOT NULL COMMENT 'Columna que contiene la contrasena del usuario para su autentificación en el sistema.',
	ROL VARCHAR(7) NOT NULL COMMENT 'Columna que contiene el rol del usuario para su autentificación en el sistema', 
    CONSTRAINT PK_TABLE_TWO PRIMARY KEY (IDUSUARIO ASC)
)
COMMENT = 'Esta tabla contiene los registros de los clientes de la aplicación'

;

CREATE TABLE VENTA
(
	IDVENTA VARCHAR(50) NOT NULL COMMENT 'id',
	IDUSUARIOPROPETARIO VARCHAR(50) NOT NULL,
	NOMBREPRODUCTO VARCHAR(50) NOT NULL,
	IDUSUARIOCOMPRADOR VARCHAR(50) NOT NULL,
	CANTIDADACOMPRAR INT NOT NULL,
	PRECIOTOTAL INT NOT NULL,
	ESTADO VARCHAR(50) NOT NULL,
	CONSTRAINT PK_VENTA PRIMARY KEY (IDVENTA ASC, IDUSUARIOPROPETARIO ASC, NOMBREPRODUCTO ASC, IDUSUARIOCOMPRADOR ASC)
)
COMMENT = 'Tabla la cual contendrá los registros de compras por parte los usuarios del sistema y los productos en estado publicado.'

;

/* Creación de Primary Keys, Indexes, Uniques, Checks */
/*Se crear los checks para la tablas producto*/
ALTER TABLE PRODUCTO 
 ADD CONSTRAINT CHECK_ESTADO CHECK (ESTADO IN ('VERIFICANDO', 'PUBLICADO', 'ACABADO'))
;

ALTER TABLE PRODUCTO 
 ADD CONSTRAINT CHECK_CATEGORIA CHECK (CATEGORIA IN ('Salud', 'Bienestar', 'Ropa', 'Accesorio'))
;

ALTER TABLE USUARIO
	ADD CONSTRAINT CHECK_ROL CHECK (ROL IN ('ADMIN', 'CLIENTE')) ;
    
/*Se crea el index de la tabla producto*/
ALTER TABLE PRODUCTO 
 ADD INDEX IXFK_PRODUCTO_USUARIO (IDUSUARIOFK ASC)
;

/*Se crea un trigger de la tabla producto que cuando se actualice su catidad = 0, se cambie el estado a acabado*/
DELIMITER $$
CREATE TRIGGER ACTUALIZAR_ESTADO_PRODUCTO
BEFORE UPDATE ON PRODUCTO
FOR EACH ROW
BEGIN
    IF NEW.CANTIDAD = 0 THEN
        SET NEW.ESTADO = 'ACABADO';
    END IF;
END$$
DELIMITER ;

/*Se agrega el check de la tabla venta*/
ALTER TABLE VENTA 
 ADD CONSTRAINT CHECK_ESTADOVENTA CHECK (ESTADO IN ('CARRO', 'CANCELADO', 'COMPRADO'))
;
/*Se agrega el index de la tabla venta con respecto a producto*/
ALTER TABLE VENTA 
 ADD INDEX IXFK_VENTA_PRODUCTO (NOMBREPRODUCTO ASC, IDUSUARIOPROPETARIO ASC)
;
/*Se agrega el idenx de la tabla venta con respecto a usuario*/
ALTER TABLE VENTA 
 ADD INDEX IXFK_VENTA_USUARIO (IDUSUARIOCOMPRADOR ASC)
;

/*Se crea un trigger en la tabla venta que actualiza la cantidad de la tabla producto cuando en el estado este completado alguna venta*/
DELIMITER $$
CREATE TRIGGER ACTUALIZAR_CANTIDAD_PRODUCTO_INSERT
AFTER INSERT ON VENTA
FOR EACH ROW
BEGIN
    IF NEW.ESTADO = 'COMPRADO' THEN
        UPDATE PRODUCTO
        SET CANTIDAD = CANTIDAD - NEW.CANTIDADACOMPRAR
        WHERE PRODUCTO.IDUSUARIOFK = NEW.IDUSUARIOPROPETARIO AND PRODUCTO.NOMBRE = NEW.NOMBREPRODUCTO;
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER ACTUALIZAR_CANTIDAD_PRODUCTO_UPDATE
AFTER UPDATE ON VENTA
FOR EACH ROW
BEGIN
	IF NEW.ESTADO = 'COMPRADO' THEN
        UPDATE PRODUCTO
        SET CANTIDAD = CANTIDAD - NEW.CANTIDADACOMPRAR
        WHERE PRODUCTO.IDUSUARIOFK = NEW.IDUSUARIOPROPETARIO AND PRODUCTO.NOMBRE = NEW.NOMBREPRODUCTO;
    END IF;
END$$
DELIMITER ;


/* Se crean los contraints de llaves foranes*/
/*Llave forane de producto con respecto a usuar (relacion 1 a muchos) y que es debil, por eso es compuesta*/
ALTER TABLE PRODUCTO 
 ADD CONSTRAINT FK_PRODUCTO_USUARIO
	FOREIGN KEY (IDUSUARIOFK) REFERENCES USUARIO (IDUSUARIO) ON DELETE Restrict ON UPDATE Restrict
;
/*Se agrega la foranea de venta conrespecto a Producto, que tiene una primaria compuesta*/
ALTER TABLE VENTA 
 ADD CONSTRAINT FK_VENTA_PRODUCTO
	FOREIGN KEY (NOMBREPRODUCTO, IDUSUARIOPROPETARIO) REFERENCES Producto (NOMBRE,IDUSUARIOFK) ON DELETE Restrict ON UPDATE Restrict
;
/*Se agrega la foranea de venta conrespecto a Usuario*/
ALTER TABLE VENTA 
 ADD CONSTRAINT FK_VENTA_USUARIO
	FOREIGN KEY (IDUSUARIOCOMPRADOR) REFERENCES USUARIO (IDUSUARIO) ON DELETE Restrict ON UPDATE Restrict
;

/*Se reestable la comprobación de llaves foraneas*/
SET FOREIGN_KEY_CHECKS=1; 
