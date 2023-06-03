/*BASE DE DATOS SW*/
create database sistema_fijsac;
use sistema_fijsac;

create table producto(
	cod_prod int auto_increment primary key,
    SKU varchar(8),
    fecha datetime not null,
    descripcion varchar(250) not null,
    stock int not null,
    estado varchar(13) not null,
    precio decimal(10,2) not null
);

create table usuario(
	cod int auto_increment primary key,
    cod_us varchar(8),
    dni char(8) unique not null,
    nom_ap varchar(100) not null,
    correo varchar(80) not null,
    contraseña varchar(15) unique not null
);
create table devolucion(
	cod int auto_increment primary key,
    cod_dev varchar(8),
    fecha datetime,
    descripcion varchar(100),
    dni char(8),
    cliente varchar(100),
    monto decimal(10,2)
);
create table venta(
	cod int auto_increment primary key,
    cod_ven varchar(8),
    cod_prod int not null,
    cod_us int not null,
    fecha datetime not null,
    descripcion varchar(100) not null,
    dni char(8),
    correo varchar(80) not null,
    cliente varchar(100),
    monto decimal(10,2) not null,
    cod_dev int,
    foreign key (cod_prod) references producto(cod_prod),
    foreign key (cod_us) references usuario(cod),
    foreign key (cod_dev) references devolucion(cod)
);
create table historial(
	cod int auto_increment primary key,
    cod_op varchar(8),
    fecha datetime not null,
    operacion varchar(50) not null,
    codigo varchar(8),
    usuario varchar(8) not null,
    cod_us int,
    foreign key(cod_us) references usuario(cod)
);

/*Triggers*/
DELIMITER $$
create trigger sku_cod before insert on producto for each row
begin
    declare contar int;
    
    set contar=(select count(*) from producto);
	set new.sku=concat('P', LPAD(contar+1, 7, '0'));
end$$
DELIMITER ;
DELIMITER $$
create trigger cod_us_trig before insert on usuario for each row
begin
    declare contar int;
    
    set contar=(select count(*) from usuario);
	set new.cod_us=concat('F', LPAD(contar+1, 7, '0'));
end$$
DELIMITER ;
DELIMITER $$
create trigger cod_ven_trig before insert on venta for each row
begin
    declare contar int;
    
    set contar=(select count(*) from venta);
	set new.cod_ven=concat('VEN-', LPAD(contar+1, 4, '0'));
end$$
DELIMITER ;
DELIMITER $$
create trigger cod_op_trig before insert on historial for each row
begin
    declare contar int;
    
    set contar=(select count(*) from historial);
	set new.cod_op=concat('R', LPAD(contar+1, 7, '0'));
end$$
DELIMITER ;
DELIMITER $$
create trigger cod_dev_trig before insert on devolucion for each row
begin
    declare contar int;
    
    set contar=(select count(*) from devolucion);
	set new.cod_dev=concat('DEV-', LPAD(contar+1, 4, '0'));
end$$
DELIMITER ;
/*REGISTROS DE PRUEBA*/
/*

USUARIOS

*/
insert into usuario (dni, nom_ap, correo, contraseña) value ('12345678', 'José Fabricio Gonzales Lamas', 'N00224836@upn.pe', '123456');
insert into usuario (dni, nom_ap, correo, contraseña) value ('87654321', 'Sebastián Samuel Zelada Chambi', 'asadada@gmail.com', '654321');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('51023262','OSCAR ORTIZ ROJAS', 'ORTIZ.10015@gmail.com', 'ORTIZ.10015');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('32527229','MARIA DE CARMEN ORTA JUAREZ', 'ORTA.10129@gmail.com', 'ORTA.10129');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('69142248','GRETA MORALES AVILA', 'MORALES.10132@gmail.com', 'MORALES.10132');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('86390523','ANGEL OSORIO EUAN', 'OSORIO.10135@gmail.com', 'OSORIO.10135');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('77508216','PABLO CLEMENTE VEGA', 'CLEMENTE.10138@gmail.com', 'CLEMENTE.10138');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('94497798','JAVIER MORALES LOPEZ', 'MORALES.10141@gmail.com', 'MORALES.10141');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('22108836','GUADALUPE DE LA PAZ ANTUNA MURILLO', 'ANTUNA.10144@gmail.com', 'ANTUNA.10144');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('70050795','DOLORES MORALES MARTINEZ', 'MORALES.10147@gmail.com', 'MORALES.10147');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('66547117','MARCO ANTONIO GARZA GARZA', 'GARZA.10150@gmail.com', 'GARZA.10150');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('51203271','HUGO MANUEL GARCIA SALINAS', 'GARCIA.10153@gmail.com', 'GARCIA.10153');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('65792390','ANDRES CHIPULI PEREZ', 'CHIPULI.10156@gmail.com', 'CHIPULI.10156');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('67767386','BARBARA ESTELA ARELLANO TORRES', 'ARELLANO.10159@gmail.com', 'ARELLANO.10159');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('75323992','ROSA INES APPEDOLE FLORES', 'APPEDOLE.10162@gmail.com', 'APPEDOLE.10162');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('23297441','JESUS RICARDO OROPEZA RODRIGUEZ', 'OROPEZA.10165@gmail.com', 'OROPEZA.10165');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('32580599','QUIRINO MORALES ALONSO', 'MORALES.10168@gmail.com', 'MORALES.10168');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('26093237','ELOISA ZAIDA TORRES ARROYO', 'TORRES.10018@gmail.com', 'TORRES.10018');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('30033358','ROSARIO DEL CARMEN SILVA PEREZ', 'SILVA.10021@gmail.com', 'SILVA.10021');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('67176441','HUGO ALEJANDRO TORRES MENDEZ', 'TORRES.10024@gmail.com', 'TORRES.10024');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('43084567','JAVIER JACOBO NAVARRO', 'JACOBO.10027@gmail.com', 'JACOBO.10027');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('70879454','ALEJANDRO TORRES GATICA', 'TORRES.10030@gmail.com', 'TORRES.10030');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('76008347','IRMA CONCEPCION SILVA MARQUEZ', 'SILVA.10033@gmail.com', 'SILVA.10033');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('44230408','MARIA DEL CARMEN SOSA BASILAKIS', 'SOSA.10171@gmail.com', 'SOSA.10171');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('67217405','JUAN JOSE SERNA ROMERO', 'SERNA.10174@gmail.com', 'SERNA.10174');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('96589443','MARIA DE LOURDES JIMENEZ LARA', 'JIMENEZ.10177@gmail.com', 'JIMENEZ.10177');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('52771773','JOSE FELIPE LLITERAS ABRAMSON', 'LLITERAS.10180@gmail.com', 'LLITERAS.10180');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('35400299','JULIO CESAR SAUCEDO SILVA', 'SAUCEDO.10183@gmail.com', 'SAUCEDO.10183');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('44084953','FERNANDO TOLEDANO DIAZ', 'TOLEDANO.10186@gmail.com', 'TOLEDANO.10186');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('93031190','LAURA ROCIO VAZQUEZ GONZALEZ', 'VAZQUEZ.10189@gmail.com', 'VAZQUEZ.10189');
INSERT INTO USUARIO (dni, nom_ap, correo, contraseña) VALUES ('36561007','EUGENIO LOPEZ CHALCHE', 'LOPEZ.10192@gmail.com', 'LOPEZ.10192');
/*

PRODUCTOS

*/
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Super B Llave Dinamométrica Digital 1/4´´ 3-30Nm', 1, 'Disponible', 713.99);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Destornillador inalámbrico VIGRUE, destornillador eléctrico recargable con juego de 48 piezas, indicador de batería, 7 ajustes de par, mango de 2 posiciones con luz LED, eje flexible', 1, 'Disponible', 225.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Llave dinamométrica LEXIVON con accionamiento de 1/4 de pulgada | 20 ~ 200 pulgadas/2,26 ~ 22,6 Nm (LX-181)', 1, 'Disponible', 205.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Kit de destornilladores de precisión ORIA, 60 en 1 con juego de destornilladores de 56 puntas, kit de destornilladores magnéticos con eje flexible, varilla de extensión para teléfono móvil, teléfono inteligente, consola de juegos, tableta, PC, azul', 1, 'Disponible', 95.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Arnés de escalada de grosor ajustable Arneses de medio cuerpo para rescate de incendios Espeleología Escalada en roca Rappel Protección de árboles Cinturones de seguridad de cintura', 1, 'Disponible', 225.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Bota de trabajo industrial impermeable con punta de seguridad compuesta Timberland PRO para hombre Boondock de 6 pulgadas', 1, 'Disponible', 1039.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Casco con trinquete H8A de 3M con protector facial de policarbonato transparente WP96', 1, 'Disponible', 209.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Estación de soldadura TXINLEI 8586 de 110 V, estación de soldadura de aire caliente SMD con pantalla digital 2 en 1 y soldador, 12 piezas de puntas de soldadura diferentes, alambre de soldadura, pinzas, bomba desoldadora, 700 W 480 °C', 1, 'Disponible', 345.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Calibrador digital electrónico NEIKO 01407A | 0-6 pulgadas | Construcción de acero inoxidable con pantalla LCD grande | Botón de cambio rápido para conversiones de pulgadas/fracciones/milímetros', 1, 'Disponible', 145.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'Mini medidor de altura digital Wixey WR25 de 3»', 1, 'Disponible', 169.00);
insert into producto (fecha, descripcion, stock, estado, precio) value (now(),'DEWALT DWHT36226S 26/ 8 m XP - Cinta métrica', 1, 'Disponible', 222.94);


/*


NO EJECUTAR AÚN


*/
/*VENTAS*/
insert into venta (cod_prod, cod_us, fecha, descripcion, dni, correo, cliente, monto) values 
(1, 1, now(), 'Esta es una prueba para venta', '12345678', 'n00224836@upn.pe', 'jose gonzales lamas', 100.95);
insert into venta (cod_prod, cod_us, fecha, descripcion, dni, correo, cliente, monto) values 
(1, 1, now(), 'Esta es una prueba para venta', '12345678', 'n00224836@upn.pe', 'jose gonzales lamas', 95.95);
/*HISTORIAL*/
insert into historial (fecha, operacion, codigo, usuario, cod_us) values (now(), 'Esta es una prueba de operación', 'P0000001', 'F0000001', 1);
/*DEVOLUCIÓN*/
insert into devolucion (fecha, descripcion, dni, cliente, monto) values (now(), 'Esta es una prueba para devolucion', '12345678', 
'sebastian samuel zelada chambi', 100.95);