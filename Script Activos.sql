DROP SCHEMA SistemaGestorActivos;
CREATE SCHEMA IF NOT EXISTS `SistemaGestorActivos` DEFAULT CHARACTER SET utf8 ;
USE `SistemaGestorActivos` ;


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Rol` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Puesto` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Funcionario` (
  `Id` VARCHAR(25) NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `Dependencia` INT NOT NULL,
  `Puesto` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `funcionario_fk_puesto_idx` (`Puesto` ASC),
  INDEX `funcionario_fk_dependencia_idx` (`Dependencia` ASC),
  CONSTRAINT `funcionario_fk_puesto`
    FOREIGN KEY (`Puesto`)
    REFERENCES `SistemaGestorActivos`.`Puesto` (`Id`)

);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Usuario` (
  `Id` VARCHAR(25) NOT NULL,
  `Clave` VARCHAR(64) NOT NULL,
  `Rol` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `usuario_fk_rol_idx` (`Rol` ASC),
  CONSTRAINT `funcionario_fk_rol`
    FOREIGN KEY (`Rol`)
    REFERENCES `SistemaGestorActivos`.`Rol` (`Id`),
  CONSTRAINT `usuario_fk_func`
    FOREIGN KEY (`Id`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Dependencia` (
  `Id` INT NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `Administrador` VARCHAR(25) NULL,
  PRIMARY KEY (`Id`),
  INDEX `dependencia_fk_admin_idx` (`Administrador` ASC),
  CONSTRAINT `dependencia_fk_admin`
    FOREIGN KEY (`Administrador`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);

ALTER TABLE Funcionario ADD CONSTRAINT `funcionario_fk_dependencia` 
FOREIGN KEY (`Dependencia`)
REFERENCES `SistemaGestorActivos`.`Dependencia` (`Id`);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Estado` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Solicitud` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Comprobante` VARCHAR(25) NOT NULL,
  `Fecha` DATE NOT NULL,
  `Tipo` VARCHAR(25) NOT NULL,
  `Cantidad` INT NOT NULL,
  `Total` FLOAT NOT NULL,
  `Estado` INT NOT NULL,
  `Dependencia` INT NOT NULL,
  `Registrador` VARCHAR(25) NULL,
  PRIMARY KEY (`Id`),
  INDEX `Solicitud_fk_estado_idx` (`Estado` ASC),
  INDEX `Solicitud_fk_dep_idx` (`Dependencia` ASC),
  INDEX `Solicitud_fk_reg_idx` (`Registrador` ASC),
  CONSTRAINT `Solicitud_fk_estado`
    FOREIGN KEY (`Estado`)
    REFERENCES `SistemaGestorActivos`.`Estado` (`Id`),
  CONSTRAINT `Solicitud_fk_dep`
    FOREIGN KEY (`Dependencia`)
    REFERENCES `SistemaGestorActivos`.`Dependencia` (`Id`),
  CONSTRAINT `Solicitud_fk_reg`
    FOREIGN KEY (`Registrador`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`ERazon` (
  `IdEstado` INT NOT NULL,
  `IdSolicitud` INT NOT NULL,
  `Comentario` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`IdSolicitud`),
  INDEX `ERazon_fk_Soli_idx` (`IdSolicitud` ASC),
  CONSTRAINT `ERazon_fk_estado`
    FOREIGN KEY (`IdEstado`)
    REFERENCES `SistemaGestorActivos`.`Estado` (`Id`),
  CONSTRAINT `ERazon_fk_Soli`
    FOREIGN KEY (`IdSolicitud`)
    REFERENCES `SistemaGestorActivos`.`Solicitud` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Categoria` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Codigo` VARCHAR(6) NOT NULL,
  `Descripcion` VARCHAR(40) NOT NULL,
  `Consecutivo` INT NOT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Bien` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(25) NOT NULL,
  `Marca` VARCHAR(25) NOT NULL,
  `Modelo` VARCHAR(25) NOT NULL,
  `Precio` FLOAT NOT NULL,
  `Cantidad` INT NOT NULL,
  `Solicitud` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Bien_fk_Sol_idx` (`Solicitud` ASC),
  CONSTRAINT `Bien_fk_Sol`
    FOREIGN KEY (`Solicitud`)
    REFERENCES `SistemaGestorActivos`.`Solicitud` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Activo` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Bien` INT(11) NOT NULL,
  `Categoria` INT(11) NOT NULL,
  `Funcionario` VARCHAR(25) NULL,
  `ConsecutivoActual` INT(11) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `Activo_fk_Bien_idx` (`Bien` ASC),
  INDEX `Activo_fk_Cat_idx` (`Categoria` ASC),
  INDEX `Activo_fk_Func_idx` (`Funcionario` ASC),
  CONSTRAINT `Activo_fk_Bien`
    FOREIGN KEY (`Bien`)
    REFERENCES `SistemaGestorActivos`.`Bien` (`Id`),
  CONSTRAINT `Activo_fk_Cat`
    FOREIGN KEY (`Categoria`)
    REFERENCES `SistemaGestorActivos`.`Categoria` (`Id`),
  CONSTRAINT `Activo_fk_Func`
    FOREIGN KEY (`Funcionario`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);


-- Insertando los posibles roles en el sistema
insert into rol(id,descripcion) values(1,'Admin');
insert into rol(id,descripcion) values(2,'SOCCB');
insert into rol(id,descripcion) values(3,'JOCCB');
insert into rol(id,descripcion) values(4,'Registrador');
insert into rol(id,descripcion) values(7,'Registrador');
insert into rol(id,descripcion) values(10,'Registrador');
insert into rol(id,descripcion) values(5,'JefeRH');

-- Insertando los posibles puestos en el sistema
insert into puesto(id,descripcion) values(1,'Administrador');
insert into puesto(id,descripcion) values(2,'Secretaria');
insert into puesto(id,descripcion) values(3,'Registrador');
insert into puesto(id,descripcion) values(4,'Jefe');
insert into puesto(id,descripcion) values(5,'Profesor');
insert into puesto(id,descripcion) values(6,'Cajero');

-- Insertando las posibles dependencias en el sistema
insert into dependencia(id,nombre) values(1,'Esc Informatica');
insert into dependencia(id,nombre) values(2,'Esc Musica');
insert into dependencia(id,nombre) values(3,'OCCB');
insert into dependencia(id,nombre) values(4,'Registro');
insert into dependencia(id,nombre) values(5,'RRHH');

-- Insertando las posibles funcionarios en el sistema
-- Funcionarios con roles en el sistema
insert into funcionario(id,nombre,dependencia,puesto) values('1','Ivannia',1,1);
insert into funcionario(id,nombre,dependencia,puesto) values('2','Arelis',3,2);
insert into funcionario(id,nombre,dependencia,puesto) values('3','Orlando',3,4);
insert into funcionario(id,nombre,dependencia,puesto) values('4','Pedro',3,3);
insert into funcionario(id,nombre,dependencia,puesto) values('5','Ronald',5,1);

-- Funcionarios ordinarios
insert into funcionario(id,nombre,dependencia,puesto) values('6','Diego',1,5);
insert into funcionario(id,nombre,dependencia,puesto) values('7','Sofia',3,3);
insert into funcionario(id,nombre,dependencia,puesto) values('8','Arturo',2,5);
insert into funcionario(id,nombre,dependencia,puesto) values('9','Carlos',2,5);
insert into funcionario(id,nombre,dependencia,puesto) values('10','Roger',3,3);

-- Insertando las posibles usuarios en el sistema
insert into usuario(id,clave,rol) values ('1',SHA2('1', 256),1);
insert into usuario(id,clave,rol) values ('2',SHA2('2', 256),2);
insert into usuario(id,clave,rol) values ('3',SHA2('3', 256),3);
insert into usuario(id,clave,rol) values ('4',SHA2('4', 256),4);
insert into usuario(id,clave,rol) values ('5',SHA2('5', 256),5);
insert into usuario(id,clave,rol) values ('8',SHA2('8', 256),1);
insert into usuario(id,clave,rol) values ('7',SHA2('7', 256),4);
insert into usuario(id,clave,rol) values ('10',SHA2('10', 256),4);

-- Insertando las posibles estados de la solicitud en el sistema
insert into estado(id,descripcion) values(1,'Recibida');
insert into estado(id,descripcion) values(2,'Por verificar');
insert into estado(id,descripcion) values(3,'Rechazada');
insert into estado(id,descripcion) values(4,'En espera');
insert into estado(id,descripcion) values(5,'Procesada');

-- Insertando las posibles solicitudes en el sistema
insert into solicitud(id,comprobante,fecha,tipo,cantidad,total,estado,dependencia) 
values(1,'A001',STR_TO_DATE('20-03-2019','%d-%m-%Y'),'Compra',5,1000000,1,1);
insert into solicitud(id,comprobante,fecha,tipo,cantidad,total,estado,dependencia) 
values(2,'A002',STR_TO_DATE('20-03-2019','%d-%m-%Y'),'Compra',10,200000,1,1);
insert into solicitud(id,comprobante,fecha,tipo,cantidad,total,estado,dependencia) 
values(3,'A003',STR_TO_DATE('10-02-2019','%d-%m-%Y'),'Compra',1,600000,1,2);

-- Insertando las posibles bienes en el sistema
insert into bien(id,descripcion,marca,modelo,precio,cantidad,solicitud) 
values(1,'Computadora','HP','Escritorio',200000,5,1);
insert into bien(id,descripcion,marca,modelo,precio,cantidad,solicitud) 
values(2,'Sillas','Mesh','Ejecutivas',20000,10,2);
insert into bien(id,descripcion,marca,modelo,precio,cantidad,solicitud) 
values(3,'Piano','Yamaha','De cola',600000,1,3);

-- Insertando las posibles categorias en el sistema
insert into categoria(id,codigo,descripcion,consecutivo) values (1,'SiLab','Silla de Laboratorio',1);
insert into categoria(id,codigo,descripcion,consecutivo) values (2,'EsLab','Escritorio de Laboratorio',1);
insert into categoria(id,codigo,descripcion,consecutivo) values (3,'ComEs','Computadora de Escritorio',1);
insert into categoria(id,codigo,descripcion,consecutivo) values (4,'InsMu','Instrumento Musical',1);

desc categoria;
update dependencia set administrador=1 where id=1;
update dependencia set administrador=8 where id=2;

select d.nombre from Usuario u, Funcionario f, Dependencia d where u.id=f.id and f.dependencia=d.id;

select s.id, s.comprobante,s.fecha,s.tipo,s.cantidad,s.total,s.estado
from Usuario u, Funcionario f, Dependencia d, Solicitud s
where u.id=f.id and f.id=d.administrador and d.id=s.dependencia;

select distinct s.id, s.comprobante,s.fecha,s.tipo,s.cantidad,s.total,e.descripcion
from Usuario u, Funcionario f, Dependencia d, Solicitud s, Estado e
where 1=f.id and f.id=d.administrador and d.id=s.dependencia and s.estado=e.id;

select distinct s.id, s.comprobante,s.fecha,s.tipo,s.cantidad,s.total,e.descripcion
from Usuario u, Funcionario f, Dependencia d, Solicitud s, Estado e
where u.id=f.id and f.id=d.administrador and d.id=s.dependencia and s.comprobante like '%%1%' 
and s.estado=e.id;


select distinct d.id, d.nombre,d.administrador 
from Dependencia d, Usuario u, Funcionario f
where 1=f.id and f.id=d.administrador;

select b.id, b.descripcion, b. marca,b.modelo,b.precio,b.cantidad
from Dependencia d, Bien b,Solicitud s
where d.id=s.dependencia and b.solicitud=s.id;

select distinct b.id, b.descripcion, b. marca,b.modelo,b.precio,b.cantidad
from Dependencia d, Bien b,Solicitud s
where 1=s.dependencia and b.solicitud=1;

select distinct s.id, s.comprobante, s.fecha, s.tipo,s.cantidad,s.total,s.estado,s.dependencia
from Solicitud s
where id=1;

select distinct s.id, s.comprobante,s.fecha,s.tipo
from Solicitud s
where s.estado=2;

select * from solicitud where estado=2 and registrador is null;
select * from puesto;

select f.id,f.nombre,p.descripcion 
from funcionario f, puesto p
where f.puesto=p.id and p.descripcion="Registrador";

select f.id,f.nombre
from funcionario f, puesto p
where f.puesto=p.id and p.id=3;

select * from puesto;
select f.id,f.nombre,p.descripcion 
from funcionario f, puesto p
where f.puesto=p.id;

select * from funcionario;
update funcionario set puesto=3, dependencia=3 where id=7;

desc solicitud;
select * from categoria;

select * from funcionario;

select * from solicitud;
update solicitud set registrador=1 where id=1;
update solicitud set registrador=null where id=1;

select * from categoria;
update categoria set descripcion='Silla Lab' where id=1;
update categoria set descripcion='Escritorio Lab' where id=2;
update categoria set descripcion='PC Lab' where id=3;

select * from bien;
select * from solicitud;
select * from solicitud where registrador = 10;

select id, comprobante, fecha, tipo
from solicitud
where registrador='10';

select * from funcionario;
select * from puesto;

desc activo;

select a.id, b.descripcion, c.descripcion, a.consecutivoactual
from activo a, bien b, categoria c
where a.bien=b.id and a.categoria=c.id;

select * from categoria;
delete from categoria where id=11;

select a.id, b.descripcion, c.codigo, a.consecutivoactual
from activo a, bien b, categoria c
where a.bien=b.id and a.categoria=c.id and
a.id like '%%%' and funcionario is null;

select a.id, s.id solicitud, b.descripcion, c.codigo, a.consecutivoactual
from activo a, bien b, categoria c, solicitud s
where a.bien=b.id and a.categoria=c.id and b.solicitud=s.id
and a.id like '%%%' and funcionario is null;

select * from solicitud;
select * from bien;
select * from activo;

select id, comprobante, fecha, tipo
from Solicitud
where estado=2 and registrador= 4 and comprobante like '%%%';

select * from solicitud;

select * from funcionario;

select f.id, f.nombre,d.nombre, p.descripcion
from funcionario f, dependencia d, puesto p
where f.dependencia = d.id and f.puesto=p.id;

select count(*) total
from activo a, solicitud s, bien b 
where a.bien=b.id and b.solicitud=s.id 
and s.id=1 and a.funcionario is null and s.estado=4;

select * 
from solicitud
where estado=4;
