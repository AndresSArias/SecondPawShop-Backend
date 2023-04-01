/* ---------------------------------------------------- */
/*              Creado por JSJSOLUTIONS	                */
/* Script que crea la base de datos con sus objetos.    */
/* ---------------------------------------------------- */

/*Creación de la base de datos de SecondPawShop*/
CREATE DATABASE IF NOT EXISTS  dbSecondPawShop;

/*Establecer la base de datos para hacer las operaciones de creación*/
USE dbSecondPawShop;

/*Se desactiva temporalmente la verificación de llaves foraneas para la creación de todas la bd*/
SET FOREIGN_KEY_CHECKS=0; 

/* Drop de las tablas si existe */
DROP TABLE IF EXISTS Producto CASCADE;

DROP TABLE IF EXISTS Usuario CASCADE;

DROP TABLE IF EXISTS Venta CASCADE;

/* Creación de las tablas */

CREATE TABLE Producto
(
	idUsuarioFK VARCHAR(15) NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	categoria VARCHAR(10) NOT NULL,
	descripcion VARCHAR(200) NOT NULL,
	cantidad INT NOT NULL,
	precio INT NOT NULL,
	imagen VARCHAR(200) NOT NULL,
	estado VARCHAR(15) NOT NULL,
	CONSTRAINT PK_Table_One PRIMARY KEY (nombre ASC, idUsuarioFK ASC)
)
COMMENT = 'Tabla que contiene los registros de los productos de la tienda. ya sea que existan o no.'

;

CREATE TABLE Usuario
(
	idUsuario VARCHAR(15) NOT NULL COMMENT 'Clave primaria de la tabla Usuario',
	nombre VARCHAR(50) NOT NULL COMMENT 'Columna que continene el nombre de los Usuarios',
	apellido VARCHAR(50) NOT NULL COMMENT 'Columna que contiene los apellidos de los usuarios.',
	correo VARCHAR(50) NOT NULL COMMENT 'Columna que contendrá los correos de los usuarios registrados',
	celular VARCHAR(10) NOT NULL COMMENT 'Columna que contendrá el número de contacto de los usuarios',
	direccion VARCHAR(50) NOT NULL COMMENT 'Columna que contiene los registros de las direcciones de los usuarios del sistema.',
	contrasena VARCHAR(20) NOT NULL COMMENT 'Columna que contiene la contrasena del usuario para su autentificación en el sistema.',
	rol VARCHAR(7) NOT NULL COMMENT 'Columna que contiene el rol del usuario para su autentificación en el sistema', 
    CONSTRAINT PK_Table_Two PRIMARY KEY (idUsuario ASC)
)
COMMENT = 'Esta tabla contiene los registros de los clientes de la aplicación'

;

CREATE TABLE Venta
(
	idVenta VARCHAR(50) NOT NULL COMMENT 'id',
	idUsuarioPropetario VARCHAR(50) NOT NULL,
	nombreProducto VARCHAR(50) NOT NULL,
	idUsuarioComprador VARCHAR(50) NOT NULL,
	cantidadAComprar INT NOT NULL,
	precioTotal INT NOT NULL,
	estado VARCHAR(50) NOT NULL,
	CONSTRAINT PK_Venta PRIMARY KEY (idVenta ASC, idUsuarioPropetario ASC, nombreProducto ASC, idUsuarioComprador ASC)
)
COMMENT = 'Tabla la cual contendrá los registros de compras por parte los usuarios del sistema y los productos en estado publicado.'

;

/* Creación de Primary Keys, Indexes, Uniques, Checks */
/*Se crear los checks para la tablas producto*/
ALTER TABLE Producto 
 ADD CONSTRAINT Check_estado CHECK (estado IN ('VERIFICANDO', 'PUBLICADO', 'ACABADO'))
;

ALTER TABLE Producto 
 ADD CONSTRAINT Check_categoria CHECK (categoria IN ('Salud', 'Bienestar', 'Ropa', 'Accesorio'))
;

ALTER TABLE Usuario
	ADD CONSTRAINT Check_rol CHECK (rol IN ('ADMIN', 'CLIENTE')) ;
    
/*Se crea el index de la tabla producto*/
ALTER TABLE Producto 
 ADD INDEX IXFK_Producto_Usuario (idUsuarioFK ASC)
;

/*Se crea un trigger de la tabla producto que cuando se actualice su catidad = 0, se cambie el estado a acabado*/
DELIMITER $$
CREATE TRIGGER actualizar_estado_producto
BEFORE UPDATE ON Producto
FOR EACH ROW
BEGIN
    IF NEW.cantidad = 0 THEN
        SET NEW.estado = 'ACABADO';
    END IF;
END$$
DELIMITER ;

/*Se agrega el check de la tabla venta*/
ALTER TABLE Venta 
 ADD CONSTRAINT Check_estadoVenta CHECK (estado IN ('CARRO', 'CANCELADO', 'COMPRADO'))
;
/*Se agrega el index de la tabla venta con respecto a producto*/
ALTER TABLE Venta 
 ADD INDEX IXFK_Venta_Producto (nombreProducto ASC, idUsuarioPropetario ASC)
;
/*Se agrega el idenx de la tabla venta con respecto a usuario*/
ALTER TABLE Venta 
 ADD INDEX IXFK_Venta_Usuario (idUsuarioComprador ASC)
;

/*Se crea un trigger en la tabla venta que actualiza la cantidad de la tabla producto cuando en el estado este completado alguna venta*/
DELIMITER $$
CREATE TRIGGER actualizar_cantidad_producto_insert
AFTER INSERT ON Venta
FOR EACH ROW
BEGIN
    IF NEW.estado = 'COMPRADO' THEN
        UPDATE Producto
        SET cantidad = cantidad - NEW.cantidadAComprar
        WHERE Producto.idUsuarioFK = NEW.idUsuarioPropetario AND Producto.nombre = NEW.nombreProducto;
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER actualizar_cantidad_producto_update
AFTER UPDATE ON Venta
FOR EACH ROW
BEGIN
    IF NEW.estado = 'COMPRADO' THEN
        UPDATE Producto
        SET cantidad = cantidad - NEW.cantidadAComprar
        WHERE Producto.idUsuarioFK = NEW.idUsuarioPropetario AND Producto.nombre = NEW.nombreProducto;
    END IF;
END$$
DELIMITER ;


/* Se crean los contraints de llaves foranes*/
/*Llave forane de producto con respecto a usuar (relacion 1 a muchos) y que es debil, por eso es compuesta*/
ALTER TABLE Producto 
 ADD CONSTRAINT FK_Producto_Usuario
	FOREIGN KEY (idUsuarioFK) REFERENCES Usuario (idUsuario) ON DELETE Restrict ON UPDATE Restrict
;
/*Se agrega la foranea de venta conrespecto a Producto, que tiene una primaria compuesta*/
ALTER TABLE Venta 
 ADD CONSTRAINT FK_Venta_Producto
	FOREIGN KEY (nombreProducto, idUsuarioPropetario) REFERENCES Producto (nombre,idUsuarioFK) ON DELETE Restrict ON UPDATE Restrict
;
/*Se agrega la foranea de venta conrespecto a Usuario*/
ALTER TABLE Venta 
 ADD CONSTRAINT FK_Venta_Usuario
	FOREIGN KEY (idUsuarioComprador) REFERENCES Usuario (idUsuario) ON DELETE Restrict ON UPDATE Restrict
;

/*Se reestable la comprobación de llaves foraneas*/
SET FOREIGN_KEY_CHECKS=1; 
