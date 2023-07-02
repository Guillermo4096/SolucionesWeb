/********************/
/*BASE DE DATOS SW*/
create database sistema_fijsac;
use sistema_fijsac;

/*
==================
CREACIÓN DE TABLAS
==================
*/

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
    contraseña varchar(80) unique not null,
    enabled TINYINT  not null
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
    codigo varchar(20)
);

CREATE TABLE IF NOT EXISTS `roles` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `user_id` INT NOT NULL,
 `authority` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`id`),
 INDEX `fk_roles_usuarios_idx` (`user_id` ASC) VISIBLE,
 CONSTRAINT `fk_roles_usuarios`
  FOREIGN KEY (`user_id`)
  REFERENCES `usuario` (`cod`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION)
ENGINE = InnoDB;

/*
============================
DISPARADORES PARA LAS TABLAS
============================
*/
/*Triggers*/
DELIMITER $$
DROP TRIGGER IF EXISTS sku_cod$$
create trigger sku_cod before insert on producto for each row
begin
	declare contar int;
    declare maxpk int;
    
    set contar=(select count(*) from producto);
    IF(contar = 0) THEN
		set new.sku=concat('P', LPAD(contar+1, 7, '0'));
	ELSE
        set maxpk=(SELECT MAX(cod_prod) FROM producto);
        set new.sku=concat('P', LPAD(maxpk+1, 7, '0'));
    END IF;
end$$
DELIMITER ;
DELIMITER $$
DROP TRIGGER IF EXISTS cod_us_trig$$
create trigger cod_us_trig before insert on usuario for each row
begin
	declare contar int;
    declare maxpk int;
    
    set contar=(select count(*) from usuario);
    IF(contar = 0) THEN
		set new.cod_us=concat('F', LPAD(contar+1, 7, '0'));
	ELSE
        set maxpk=(SELECT MAX(cod) FROM usuario);
        set new.cod_us=concat('F', LPAD(maxpk+1, 7, '0'));
    END IF;
end$$
DELIMITER ;
DELIMITER $$
DROP TRIGGER IF EXISTS cod_ven_trig$$
create trigger cod_ven_trig before insert on venta for each row
begin
    declare contar int;
    declare maxpk int;
    
    set contar=(select count(*) from venta);
    IF(contar = 0) THEN
		set new.cod_ven=concat('V', LPAD(contar+1, 7, '0'));
	ELSE
        set maxpk=(SELECT MAX(cod) FROM VENTA);
        set new.cod_ven=concat('V', LPAD(maxpk+1, 7, '0'));
    END IF;
end$$
DELIMITER ;

use sistema_fijsac;
DELIMITER $$
DROP TRIGGER IF EXISTS cod_hist_trig$$
create trigger cod_hist_trig before insert on historial for each row
begin
    declare contar int;
    declare maxpk int;
    
    set contar=(select count(*) from historial);
    IF(contar = 0) THEN
		set new.cod_op=concat('R', LPAD(contar+1, 7, '0'));
	ELSE
        set maxpk=(SELECT MAX(cod) FROM historial);
        set new.cod_op=concat('R', LPAD(maxpk+1, 7, '0'));
    END IF;
end$$
DELIMITER ;

/*
==================
PROCEDIMIENTOS ALMACENADOS
==================
*/

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
use sistema_fijsac;
DELIMITER $$
DROP PROCEDURE IF EXISTS editar_monto_venta$$
CREATE PROCEDURE editar_monto_venta(IN a int)
BEGIN
	declare UcodVenA int;
    declare UcodProdVenA int;
    declare precioProdA decimal(10,2);
    declare descProdA varchar(500);
    declare UcantidadVenA int;
    declare NmontoA decimal(10,2);
    
	
    set UcodProdVenA = (SELECT cod_prod FROM venta where cod= a );
    set precioProdA = (SELECT precio FROM producto WHERE cod_prod = UcodProdVenA);
    set UcantidadVenA = (SELECT cantidad FROM venta where cod = a);
    set NmontoA = (precioProdA * UcantidadVenA);
    set descProdA = (SELECT descripcion FROM producto WHERE cod_prod = UcodProdVenA);
    update venta SET monto = NmontoA WHERE cod = a ;
    UPDATE venta SET descripcion = CONCAT(UcantidadVenA, 'x ', descProdA) WHERE cod = a;
END
$$

DELIMITER $$
DROP PROCEDURE IF EXISTS regist_op_hist$$
CREATE PROCEDURE regist_op_hist(operacion varchar(50), codigo varchar(50))
BEGIN
    
    INSERT INTO HISTORIAL (fecha, operacion, codigo) VALUE (now(), operacion, codigo);
END
$$

DELIMITER $$
DROP PROCEDURE IF EXISTS regist_nuev_prod$$
CREATE PROCEDURE regist_nuev_prod(IN o int)
BEGIN
    declare sku varchar(20);
    
    set sku = (SELECT PRODUCTO.SKU from producto order by cod_prod desc limit 1);
    INSERT INTO HISTORIAL (fecha, operacion, codigo) VALUE (now(), 'Nuevo producto registrado', sku);
END
$$

DELIMITER $$
DROP PROCEDURE IF EXISTS regist_nuev_user$$
CREATE PROCEDURE regist_nuev_user(IN o int)
BEGIN
    declare cod_us varchar(20);
    
    set cod_us = (SELECT USUARIO.cod_us from usuario order by cod desc limit 1);
    INSERT INTO HISTORIAL (fecha, operacion, codigo) VALUE (now(), 'Nuevo usuario registrado', cod_us);
END
$$

DELIMITER $$
DROP PROCEDURE IF EXISTS regist_nuev_vent$$
CREATE PROCEDURE regist_nuev_vent(IN o int)
BEGIN
    declare cod_vent varchar(20);
    
    set cod_vent = (SELECT VENTA.cod_ven from venta order by cod desc limit 1);
    INSERT INTO HISTORIAL (fecha, operacion, codigo) VALUE (now(), 'Nueva venta registrada', cod_vent);
END
$$

DELIMITER $$
DROP PROCEDURE IF EXISTS act_stock_prod$$
CREATE PROCEDURE act_stock_prod(IN stock int, cod_prod int)
BEGIN
	IF stock = 0 THEN
        UPDATE PRODUCTO SET PRODUCTO.STOCK=stock, PRODUCTO.ESTADO='No disponible' WHERE PRODUCTO.COD_PROD=cod_prod;
	ELSE
        UPDATE PRODUCTO SET PRODUCTO.STOCK=stock, PRODUCTO.ESTADO='Disponible' WHERE PRODUCTO.COD_PROD=cod_prod;
	END IF;
END
$$

/*
========================
REGISTROS BASE DE PRUEBA
========================
*/
insert into usuario (cod_us, dni, nom_ap, correo, contraseña, enabled) value (NULL,'12345678', 'José Fabricio Gonzales Lamas', 'N00224836@upn.pe', '$2a$12$q5JkPGMKsVshZ2OUlIeqjuSLH3F1dM5Mk1qwRGVT3MV4pgM37ijFm',1);
insert into usuario (cod_us, dni, nom_ap, correo, contraseña, enabled) value (NULL,'87654321', 'Sebastián Samuel Zelada Chambi', 'asadada@gmail.com', '$2a$12$4iZiyOrtPYHFD4GZn3Zv0eRWtkut1n2KMI/Imw9VnSjA.XXYN0Pm2',1);
INSERT INTO USUARIO (cod_us, dni, nom_ap, correo, contraseña, enabled) VALUES (NULL,'51023262','OSCAR ORTIZ ROJAS', 'ORTIZ.10015@gmail.com', '$2a$12$dobvx4HTDgU0urob3CKx9.sYdaj5kkJp8n1qCjgowsAfFtyOI.gg2',1);
INSERT INTO USUARIO (cod_us, dni, nom_ap, correo, contraseña, enabled) VALUES (NULL,'32527229','MARIA DE CARMEN ORTA JUAREZ', 'ORTA.10129@gmail.com', '$2a$12$aKZkDxuYPxJG4nUHXJmUtuI/i3r5vzmQQ1XsnOmS4t4tjwqGGckc.',1);
INSERT INTO USUARIO (cod_us, dni, nom_ap, correo, contraseña, enabled) VALUES (NULL,'69142248','GRETA MORALES AVILA', 'MORALES.10132@gmail.com', '$2a$12$nb/flm718jHRiMOOe.Ikwu9BCAkpwNCWaVR7LGHp0fDMTMN3uQoTu',1);
INSERT INTO USUARIO (cod_us, dni, nom_ap, correo, contraseña, enabled) VALUES (NULL,'86390523','ANGEL OSORIO EUAN', 'OSORIO.10135@gmail.com', '$2a$12$6Jz2Zq/hxp4X5n8eifIT5eU9q3ETOPEPeiLHUqJpdUbBy5VaCGENC',1);


INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('65206875638', 'FERRETERIA MODERNA OR SAS', '26 Oriente  N° 510', '901145497');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('59128069560', 'HERRAMIENTAS TECNICAS SAS', '24 Oriente  N° 512', '890922365');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('75402756919', 'FERROINDUSTRIAL SA', '54 Oriente  N° 120', '890913555');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('21681213354', 'SODIMAC COLOMBIA SA', '28 Oriente  N° 508', '800242106');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('78199638258', 'SUMINISTROS Y CONTROLES ELECTRONICOS SA', '53 Oriente  N° 483', '890943055');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('23096306740', 'FERRETERIA DISTRIVALVULAS SAS', '50 Oriente  N° 486', '811030294');
INSERT INTO PROVEEDOR (prov_ruc, prov_nombre, direccion, telefono) VALUES ('26894529398', 'CORTE Y GRAFADO SAS', '23 Oriente  N° 513', '800183807');

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

INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('DANIEL', 'COBOS ESCUDERO', '26298055', '987080808');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARTHA', 'MORENO DURAN', '34685879', '993902191');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARIA EUGENIA', 'ARNAU VADILLO', '75260078', '970415454');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('VICTOR MANUEL', 'ARREOLA MIGUEL', '71074080', '964930588');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('JUANA BAUTISTA', 'PARIENTE GARCIA', '66317439', '922844247');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('FRANCISCO', 'PACHECO HERRERA', '61796387', '938636242');
INSERT INTO CLIENTE (nombre, apellido, dni, celular) VALUES ('MARIA DEL CARMEN', 'AWAD VAZQUEZ', '92002784', '987604732');

/*
drop database sistema_fijsac;
use sistema_fijsac;
select * from roles;
select * from usuario;
select * from cliente;
select * from venta;
select * from producto;
select * from proveedor;
select * from historial;

*/