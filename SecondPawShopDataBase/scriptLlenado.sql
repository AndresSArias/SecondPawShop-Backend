/* ---------------------------------------------------- */
/*              Creado por JSJSOLUTIONS	                */
/* Script que hace el llenado de la base de datos.       */
/* ---------------------------------------------------- */

/*INSERCIÓN DE USUARIOS*/
INSERT INTO USUARIO (idUsuario,nombre,apellido,correo,celular,direccion,contrasena) 
VALUES ('1233906548','Andrés','Arias','andres31321@gmail.com','3142294642','Calle 133 #118-4','clave1');
INSERT INTO USUARIO (idUsuario,nombre,apellido,correo,celular,direccion,contrasena) 
VALUES ('4242123121','José','Yepés','yepes@gmail.com','3184282638','Carrera 24 #90-23','clave2');
INSERT INTO USUARIO (idUsuario,nombre,apellido,correo,celular,direccion,contrasena) 
VALUES ('7987897897','Santiago','Ventura','santiago@gmail.com','3123427615','Transversal 97 #26-30','clave3');
INSERT INTO USUARIO (idUsuario,nombre,apellido,correo,celular,direccion,contrasena) 
VALUES ('5754664764','Gabriel','Ventura','gabriel@gmail.com','3004123123','Avenida 23 #12-5','clave4');
INSERT INTO USUARIO (idUsuario,nombre,apellido,correo,celular,direccion,contrasena) 
VALUES ('1029384756','Dona','Parra','parraDona@gmail.com','3142294642','Calle 80 #126-52','Andrys');

/*Inserción de PRODUCTOS*/
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('7987897897','Cama','Bienestar','Cama para perro o gato color café claro con poco uso',1,50000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088253556051693690/cama.webp','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('4242123121','Camisa','Ropa','Camisa para perro o gato talla m con poco uso',2,100000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088255438878941194/camisa-p.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('1233906548','Chalecos','Ropa','Chalecos para perros de tamaño pequeño, un poco usados',2,150000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088257723503755274/chalecos.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('1233906548','Collar de Cuero','Accesorio','Collar de cuero de alta calidad con poco uso',1,200000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088259106160267284/collar-cuero.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('5754664764','Disfraz','Ropa','Disfraz de una pieza con mucho uso y un precio de locura',1,50000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088259974658007040/disfraz.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('5754664764','Gafas Gato','Accesorio','Gafas para gato como nuevas',1,25000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088260345199591524/gafas-gato.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('4242123121','Impermeable','Ropa','Impermeable para lluvias casi nuevo',4,80000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088261238431154248/impermeable.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('4242123121','Juguete Perro','Bienestar','Juguestes para perro mordiclables con un poco de uso',3,20000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088261806746783864/jugueteperro.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('1233906548','Pastilla Antipulgas','Salud','Pastilla comestible para el contro de pulgas',5,12000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088328266840027216/pastilla-antipulgas.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('5754664764','Saco','Ropa','Saco con poco uso muy comodo para perritos mediano',1,65000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088329107080753152/saco.jpg','PUBLICADO');
INSERT INTO PRODUCTO (idUsuarioFK,nombre,categoria,descripcion,cantidad,precio,imagen,estado) 
VALUES ('1233906548','Saco rojo','Ropa','Saco para perro pequeño, de color rojo es casi nuevo',1,95000,'https://cdn.discordapp.com/attachments/1065754163205648404/1088330146974531634/sacoPerro.jpg','VERIFICANDO');

/*INSERCION DE VENTAS */
INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)
VALUES ('1','1233906548','Pastilla Antipulgas','1029384756',1,12000,'COMPRADO');
INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)
VALUES ('2','4242123121','Impermeable','1233906548',1,80000,'COMPRADO');
INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)
VALUES ('3','4242123121','Impermeable','5754664764',1,80000,'COMPRADO');
INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)
VALUES ('3','1233906548','Pastilla Antipulgas','5754664764',1,12000,'COMPRADO');
INSERT INTO VENTA (idVenta,idUsuarioPropetario,nombreProducto,idUsuarioComprador,cantidadAComprar,precioTotal,estado)
VALUES ('3','4242123121','Juguete Perro','5754664764',1,20000,'COMPRADO');