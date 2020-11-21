CREATE DATABASE parquimetros;
USE parquimetros;

CREATE TABLE conductores(
    dni INT unsigned NOT NULL,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    direccion VARCHAR(45) NOT NULL,
    telefono VARCHAR(45) ,
    registro INT unsigned NOT NULL,

    CONSTRAINT pk_conductores
    PRIMARY KEY (dni),

    KEY(registro)
) ENGINE=InnoDB;

CREATE TABLE automoviles(
    patente VARCHAR(6) NOT NULL,
    marca  VARCHAR(45) NOT NULL,
    modelo VARCHAR(45) NOT NULL,
    color VARCHAR(45) NOT NULL,
	 dni INT UNSIGNED NOT NULL,
	CONSTRAINT pk_automoviles
	PRIMARY KEY (patente),
	
    CONSTRAINT FK_automoviles_conductores
    FOREIGN KEY (dni) REFERENCES conductores(dni)
	ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;
CREATE TABLE tipos_tarjeta(
	tipo VARCHAR(45) NOT NULL,
	descuento DECIMAL(3,2) unsigned 
		CHECK (descuento >=0.00 OR descuento <= 1.00) NOT NULL,
	
	CONSTRAINT pk_tipo
	PRIMARY KEY (tipo)
)ENGINE=InnoDB;
CREATE TABLE tarjetas(
	id_tarjeta SMALLINT unsigned NOT NULL AUTO_INCREMENT,
	saldo DECIMAL(5,2) NOT NULL,
	tipo VARCHAR(45) NOT NULL,
	patente VARCHAR(6) NOT NULL,
	CONSTRAINT pk_tarjetas
	PRIMARY KEY(id_tarjeta),
	
	CONSTRAINT fk_tarjetas_tipos_tarjetas
	FOREIGN KEY (tipo) REFERENCES tipos_tarjeta(tipo),
	
	CONSTRAINT fk_tarjetas_automoviles
	FOREIGN KEY (patente) REFERENCES automoviles(patente)
)ENGINE=InnoDB;
CREATE TABLE inspectores (
	legajo INT unsigned NOT NULL, 
	dni INT unsigned NOT NULL,
	nombre VARCHAR(45) NOT NULL,
	apellido VARCHAR(45) NOT NULL,
	password CHAR(32) NOT NULL,
	
	CONSTRAINT pk_inspectores
	PRIMARY KEY (legajo)

)ENGINE=InnoDB;
CREATE TABLE ubicaciones(
	calle VARCHAR(45) NOT NULL,
	altura SMALLINT unsigned NOT NULL,
	tarifa DECIMAL(5,2) unsigned NOT NULL,
	
	CONSTRAINT pk_ubicaciones
	PRIMARY KEY(calle,altura)
)ENGINE=InnoDB;
CREATE TABLE parquimetros(
	id_parq SMALLINT unsigned NOT NULL,
	numero SMALLINT unsigned NOT NULL,
	calle VARCHAR(45) NOT NULL,
	altura SMALLINT unsigned NOT NULL,
	
	CONSTRAINT pk_parquimetros
	PRIMARY KEY (id_parq),
	
	CONSTRAINT fk_parquimetros_ubicaciones
	FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)
		ON DELETE RESTRICT ON UPDATE CASCADE
		
)ENGINE=InnoDB;
CREATE TABLE estacionamientos(
	id_tarjeta SMALLINT unsigned NOT NULL,
	id_parq SMALLINT unsigned NOT NULL,
	fecha_ent DATE NOT NULL,
	hora_ent TIME (0) NOT NULL,
	fecha_sal DATE ,
	hora_sal TIME (0) ,
	CONSTRAINT pk_estacionamientos
	PRIMARY KEY(fecha_ent,hora_ent,id_parq),
	
	CONSTRAINT fk_estacionamientos_parquimetros
	FOREIGN KEY (id_parq) REFERENCES parquimetros(id_parq)
		ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT fk_estacionamientos_tarjetas
	FOREIGN KEY (id_tarjeta) REFERENCES tarjetas(id_tarjeta)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB;
CREATE TABLE accede (
	legajo INT unsigned NOT NULL,
	id_parq SMALLINT unsigned NOT NULL,
	fecha DATE NOT NULL, 
	hora TIME (0) NOT NULL,
	
	CONSTRAINT pk_accede
	PRIMARY KEY (id_parq,fecha,hora),
	
	CONSTRAINT fk_accede_parquimetros
	FOREIGN KEY (id_parq) REFERENCES parquimetros (id_parq)
		ON DELETE RESTRICT ON UPDATE CASCADE,
		
	CONSTRAINT fk_accede_inspectores
	FOREIGN KEY (legajo) REFERENCES inspectores (legajo)
		ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE=InnoDB;
CREATE TABLE asociado_con(
	id_asociado_con SMALLINT unsigned AUTO_INCREMENT,
	legajo INT unsigned NOT NULL,
	calle  VARCHAR(45) NOT NULL,
	altura SMALLINT unsigned NOT NULL,
	dia ENUM("do","lu","ma","mi","ju","vi","sa") NOT NULL,
	turno ENUM("m","t") NOT NULL,
	
	CONSTRAINT pk_asociado
	PRIMARY KEY (id_asociado_con),
	
	CONSTRAINT fk_asociado_inspectores
	FOREIGN KEY (legajo) REFERENCES inspectores(legajo)
		ON DELETE RESTRICT ON UPDATE CASCADE,
		
	CONSTRAINT fk_asociado_ubicaciones
	FOREIGN KEY (calle,altura) REFERENCES ubicaciones(calle,altura)
		ON DELETE RESTRICT ON UPDATE CASCADE

)ENGINE=InnoDB;
CREATE TABLE multa(
	numero SMALLINT unsigned AUTO_INCREMENT,
	fecha DATE NOT NULL,
	hora TIME (0) NOT NULL,
	patente VARCHAR(6) NOT NULL,
	id_asociado_con SMALLINT unsigned NOT NULL,
	
	CONSTRAINT pk_multa
	PRIMARY KEY (numero),
	
	CONSTRAINT fk_multa_automoviles
	FOREIGN KEY (patente) REFERENCES automoviles (patente)
		ON DELETE RESTRICT ON UPDATE CASCADE,
		
	CONSTRAINT fk_multa_asociado 
	FOREIGN KEY (id_asociado_con) REFERENCES asociado_con(id_asociado_con)
		ON DELETE RESTRICT ON UPDATE CASCADE
		
)ENGINE=InnoDB;

#-------------------------------------------------------------------------------------------------------------------------------------
CREATE VIEW estacionados AS
SELECT p.calle,p.altura,t.patente 
FROM estacionamientos AS e NATURAL JOIN tarjetas AS t NATURAL JOIN parquimetros AS p
WHERE e.fecha_sal IS NULL AND e.hora_sal IS NULL ;

CREATE VIEW info_inspectores AS
SELECT legajo,password FROM inspectores;
#-------------------------------------------------------------------------------------------------------------------------------------
# Creacion de usuarios

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON parquimetros.* TO 'admin'@'localhost' WITH GRANT OPTION;


CREATE USER 'venta'@'%' IDENTIFIED BY 'venta';

GRANT INSERT ON parquimetros.tarjetas TO 'venta'@'%';

CREATE USER 'inspector'@'%' IDENTIFIED BY 'inspector';

GRANT SELECT ON parquimetros.parquimetros TO 'inspector'@'%';

GRANT SELECT ON parquimetros.estacionados TO 'inspector'@'%';

GRANT SELECT ON parquimetros.multa TO 'inspector'@'%';

GRANT INSERT ON parquimetros.multa TO 'inspector'@'%';

GRANT INSERT ON parquimetros.accede TO 'inspector'@'%';

GRANT SELECT ON parquimetros.ubicaciones TO 'inspector'@'%';

GRANT SELECT ON parquimetros.asociado_con TO 'inspector'@'%';

GRANT SELECT ON parquimetros.tarjetas TO 'inspector'@'%';

