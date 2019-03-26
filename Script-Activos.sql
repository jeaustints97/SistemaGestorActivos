DROP SCHEMA SistemaGestorActivos;
CREATE SCHEMA IF NOT EXISTS `SistemaGestorActivos` DEFAULT CHARACTER SET utf8 ;
USE `SistemaGestorActivos` ;

CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Rol` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Id`));


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Puesto` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`Id`));

CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Funcionario` (
  `Id` INT NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `Dependencia` VARCHAR(45) NOT NULL,
  `Rol` INT NULL,
  `Puesto` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `funcionario_fk_puesto_idx` (`Puesto` ASC),
  INDEX `funcionario_fk_rol_idx` (`Rol` ASC),
  CONSTRAINT `funcionario_fk_rol`
    FOREIGN KEY (`Rol`)
    REFERENCES `SistemaGestorActivos`.`Rol` (`Id`),
  CONSTRAINT `funcionario_fk_puesto`
    FOREIGN KEY (`Puesto`)
    REFERENCES `SistemaGestorActivos`.`Puesto` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Usuario` (
  `Id` INT NOT NULL,
  `Clave` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `usuario_fk_func`
    FOREIGN KEY (`Id`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Dependencia` (
  `Id` INT NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `Administrador` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `dependencia_fk_admin_idx` (`Administrador` ASC),
  CONSTRAINT `dependencia_fk_admin`
    FOREIGN KEY (`Administrador`)
    REFERENCES `SistemaGestorActivos`.`Funcionario` (`Id`)
);


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
  `Registrador` INT NULL,
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
  PRIMARY KEY (`IdEstado`),
  INDEX `ERazon_fk_Soli_idx` (`IdSolicitud` ASC),
  CONSTRAINT `ERazon_fk_estado`
    FOREIGN KEY (`IdEstado`)
    REFERENCES `SistemaGestorActivos`.`Estado` (`Id`),
  CONSTRAINT `ERazon_fk_Soli`
    FOREIGN KEY (`IdSolicitud`)
    REFERENCES `SistemaGestorActivos`.`Solicitud` (`Id`)
);


CREATE TABLE IF NOT EXISTS `SistemaGestorActivos`.`Categoria` (
  `Id` INT NOT NULL,
  `Descripcion` VARCHAR(25) NOT NULL,
  `Consecutivo` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`),
  KEY `Consecutivo` (`Consecutivo`)
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
  `Bien` INT NOT NULL,
  `Categoria` INT NOT NULL,
  `Funcionario` INT NOT NULL,
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
insert into funcionario(id,nombre,dependencia,rol,puesto) values(1,'Ivannia',1,1,1);
insert into funcionario(id,nombre,dependencia,rol,puesto) values(2,'Arelis',3,2,2);
insert into funcionario(id,nombre,dependencia,rol,puesto) values(3,'Orlando',3,3,4);
insert into funcionario(id,nombre,dependencia,rol,puesto) values(4,'Pedro',3,4,3);
insert into funcionario(id,nombre,dependencia,rol,puesto) values(5,'Ronald',5,5,1);

-- Funcionarios ordinarios
insert into funcionario(id,nombre,dependencia,puesto) values(6,'Diego',1,5);
insert into funcionario(id,nombre,dependencia,puesto) values(7,'Sofia',1,5);
insert into funcionario(id,nombre,dependencia,puesto) values(8,'Arturo',2,5);
insert into funcionario(id,nombre,dependencia,puesto) values(9,'Carlos',2,5);
insert into funcionario(id,nombre,dependencia,puesto) values(10,'Roger',4,6);


-- Insertando las posibles usuarios en el sistema
insert into usuario(id,clave) values(1,'1');
insert into usuario(id,clave) values(2,'2');
insert into usuario(id,clave) values(3,'3');
insert into usuario(id,clave) values(4,'4');
insert into usuario(id,clave) values(5,'5');

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

