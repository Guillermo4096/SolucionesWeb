/********************/
/*BASE DE DATOS SW*/
create database sistema_fijsac;
use sistema_fijsac;

create table proveedor(
	id_prov int auto_increment primary key,
    prov_ruc char(11),
    prov_nombre varchar(100),
    direccion varchar(50),
	telefono char(9)
);
create table cliente(
	id_cli int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    dni char(8) not null,
    celular char(9)
);
create table producto(
	cod_prod int auto_increment primary key,
    SKU varchar(8),
    fecha datetime not null,
    descripcion varchar(250) not null,
    stock int not null,
    estado varchar(13) not null,
    precio decimal(10,2) not null,
    id_prov int not null,
    foreign key (id_prov) references proveedor(id_prov) on delete cascade
);

create table usuario(
	cod int auto_increment primary key,
    cod_us varchar(8),
    dni char(8) unique not null,
    nom_ap varchar(100) not null,
    correo varchar(80) not null,
    contraseña varchar(15) unique not null
);
create table venta(
	cod int auto_increment primary key,
    cod_ven varchar(8),
    cod_prod int not null,
    cod_us int not null,
    id_cli int not null,
    fecha datetime not null,
    descripcion varchar(400),
    cantidad int not null,
    correo varchar(80),
    monto decimal(10,2),
    foreign key (cod_prod) references producto(cod_prod) on delete cascade,
    foreign key (cod_us) references usuario(cod) on delete cascade,
    foreign key (id_cli) references cliente(id_cli) on delete cascade
);
create table historial(
	cod int auto_increment primary key,
    cod_op varchar(8),
    fecha datetime not null,
    operacion varchar(50) not null,
    codigo varchar(8),
    usuario varchar(8) not null,
    cod_us int,
    foreign key(cod_us) references usuario(cod) on delete cascade
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
	set new.cod_ven=concat('V', LPAD(contar+1, 7, '0'));
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

/*CALCULAR MONTO DE VENTA*/
/**/
DELIMITER $$
DROP PROCEDURE IF EXISTS ingresar_monto_venta$$
CREATE PROCEDURE ingresar_monto_venta(IN r int)
BEGIN
	declare UcodVen int;
    declare UcodProdVen int;
    declare precioProd decimal(10,2);
    declare descProd varchar(500);
    declare UcantidadVen int;
    declare Nmonto decimal(10,2);
    
	set UcodVen=(SELECT cod from venta order by cod desc limit 1);
    set UcodProdVen=(select cod_prod from venta order by cod desc limit 1);
    set precioProd=(select precio from producto where cod_prod=UcodProdVen);
    set UcantidadVen=(select cantidad from venta order by cod desc limit 1);
    set Nmonto=(precioProd*UcantidadVen);
    set descProd=(select descripcion from producto where cod_prod=UcodProdVen);
    UPDATE venta set monto=Nmonto where cod=UcodVen;
    UPDATE venta set descripcion=(concat(UcantidadVen, 'x ', descProd)) where cod=UcodVen;
    /*set new.cod_op=concat('R', LPAD(contar+1, 7, '0'));*/
END
$$

/*CALL ingresar_monto_venta(0);*/
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

/*

PROVEEDORES

*/
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('65206875638', 'FERRETERIA MODERNA OR SAS', '26 Oriente  N° 510', '901145497');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('59128069560', 'HERRAMIENTAS TECNICAS SAS', '24 Oriente  N° 512', '890922365');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('75402756919', 'FERROINDUSTRIAL SA', '54 Oriente  N° 120', '890913555');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('21681213354', 'SODIMAC COLOMBIA SA', '28 Oriente  N° 508', '800242106');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('78199638258', 'SUMINISTROS Y CONTROLES ELECTRONICOS SA', '53 Oriente  N° 483', '890943055');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('23096306740', 'FERRETERIA DISTRIVALVULAS SAS', '50 Oriente  N° 486', '811030294');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('26894529398', 'CORTE Y GRAFADO SAS', '23 Oriente  N° 513', '800183807');

/*

PRODUCTOS

*/
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Super B Llave Dinamométrica Digital 1/4´´ 3-30Nm', 1, 'Disponible', 713.99, 1);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Destornillador inalámbrico VIGRUE, destornillador eléctrico recargable con juego de 48 piezas, indicador de batería, 7 ajustes de par, mango de 2 posiciones con luz LED, eje flexible', 1, 'Disponible', 225.00, 2);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Llave dinamométrica LEXIVON con accionamiento de 1/4 de pulgada | 20 ~ 200 pulgadas/2,26 ~ 22,6 Nm (LX-181)', 1, 'Disponible', 205.00, 3);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Kit de destornilladores de precisión ORIA, 60 en 1 con juego de destornilladores de 56 puntas, kit de destornilladores magnéticos con eje flexible, varilla de extensión para teléfono móvil, teléfono inteligente, consola de juegos, tableta, PC, azul', 1, 'Disponible', 95.00, 4);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Arnés de escalada de grosor ajustable Arneses de medio cuerpo para rescate de incendios Espeleología Escalada en roca Rappel Protección de árboles Cinturones de seguridad de cintura', 1, 'Disponible', 225.00, 5);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Bota de trabajo industrial impermeable con punta de seguridad compuesta Timberland PRO para hombre Boondock de 6 pulgadas', 1, 'Disponible', 1039.00,6);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Casco con trinquete H8A de 3M con protector facial de policarbonato transparente WP96', 1, 'Disponible', 209.00, 7);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Estación de soldadura TXINLEI 8586 de 110 V, estación de soldadura de aire caliente SMD con pantalla digital 2 en 1 y soldador, 12 piezas de puntas de soldadura diferentes, alambre de soldadura, pinzas, bomba desoldadora, 700 W 480 °C', 1, 'Disponible', 345.00, 1);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Calibrador digital electrónico NEIKO 01407A | 0-6 pulgadas | Construcción de acero inoxidable con pantalla LCD grande | Botón de cambio rápido para conversiones de pulgadas/fracciones/milímetros', 1, 'Disponible', 145.00, 2);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'Mini medidor de altura digital Wixey WR25 de 3»', 1, 'Disponible', 169.00, 3);
insert into producto (fecha, descripcion, stock, estado, precio, id_prov) value (now(),'DEWALT DWHT36226S 26/ 8 m XP - Cinta métrica', 1, 'Disponible', 222.94, 4);
/*

CLIENTES

*/
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('DANIEL', 'COBOS ESCUDERO', '26298055', '987080808');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARTHA', 'MORENO DURAN', '34685879', '993902191');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARIA EUGENIA', 'ARNAU VADILLO', '75260078', '970415454');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('VICTOR MANUEL', 'ARREOLA MIGUEL', '71074080', '964930588');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('JUANA BAUTISTA', 'PARIENTE GARCIA', '66317439', '922844247');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('FRANCISCO', 'PACHECO HERRERA', '61796387', '938636242');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARIA DEL CARMEN', 'AWAD VAZQUEZ', '92002784', '987604732');


insert into venta(cod_prod, cod_us, id_cli, fecha, cantidad, descripcion) values
(1, 1, 1, now(), 3, '3x Super B Llave Dinamométrica Digital 1/4´´ 3-30Nm');

use sistema_fijsac;
select * from usuario;
select * from cliente;
select * from venta;
select * from producto;
select * from proveedor;