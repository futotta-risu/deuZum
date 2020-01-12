-- Esta base de datos constituye la plantilla desde la que la aplicacion funciona.
-- 
-- Autor	: 	Fat Squirrels
-- Version	: 	1.2
-- 


-- ------------------------
-- Creacion de las tablas
-- ------------------------

-- Plantilla de la informacion de las tablas
--
-- Tabla		:	Nombre
-- Descripcion	:	Descripcion de ejemplo


-- Tabla		: 	Usuario
-- Descripcion	:	Esta tabla contiene la informacion de los usuarios de la aplicacion
CREATE TABLE `usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(32) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `preg_seguridad` int(10) NOT NULL,
  `resp_seguridad` varchar(32) NOT NULL,
  `permisos` int(10) DEFAULT NULL DEFAULT 3,
  `categoria` int(10) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `usuario` (`usuario`),
   KEY `preg_seguridad` (`preg_seguridad`),
   KEY `categoria` (`categoria`),
   KEY `permisos` (`permisos`)
);


-- Tabla		:	Permisos
-- Descripcion	:	Permisos que pueden tener los usuarios de la aplicacion. 
--			En esta version se plantean 3 grados de poder:
--				- Aministrador	: 	Poder total sobre el servidor.
--				- Programador	:	Control sobre la base de datos y operaciones internas del servidor.
--				- Cliente		:	Acceso restringido a la base de datos y servidor.
CREATE TABLE `permisos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
);


-- Los permisos de usuario basicos
INSERT INTO `permisos` (`nombre`, `poder`) VALUES
	('administrador', 1),
	('programador', 2),
	('cliente', 3);

-- Tabla		:	Cliente Categoria
-- Descripcion	:	Categorias en las que pueden entrar los clientes tras ser analizados por algoritmos de clustering.
CREATE TABLE `clientecategoria` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `descripcion` text NOT NULL,
  PRIMARY KEY (`id`)
);

-- Los permisos de usuario basicos
INSERT INTO `clientecategoria` (`nombre`, `descripcion`) VALUES
	('Gastador', 'Otro'),
	('Comprador', 'Alguien'),
	('Timador', 'Yo');

 
-- Tabla		:	Informacion de Usuario
-- Descripcion	:	Contiene la informacion de los usuarios. Dentro de esta tabla entra la informacion personal del usuario(nombre, apellido,...).
CREATE TABLE `infousuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NULL,
  `apellido` varchar(15) NULL,
  `telefono` varchar(9) NULL,
  `email` varchar(40) NULL,
  `direccion` varchar(50) NULL,
  `fecha_nacimiento` date NULL DEFAULT current_timestamp(),
  `sexo` enum('Femenino','Masculino','Otro') NULL,
  PRIMARY KEY (`id`)
);

  

-- Tabla		:	Cuenta
-- Descripcion	:	Cada usuario puede tener varias cuentas dentro de la aplicacion. Esta tabla contiene la informacion de las mismas.
CREATE TABLE `cuenta` (
  `numero_cuenta` int(10) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(10) NOT NULL,
  `dinero` int(11) NOT NULL,
  `tipo_cuenta` varchar(15) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`numero_cuenta`),
  KEY `id_usuario` (`id_usuario`)
);


-- Tabla		:	Grupo
-- Descripcion	:	Grupos para los proyectos.
CREATE TABLE `grupo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- Tabla		:	Grupo Miembro
-- Descripcion	:	Tabla de los miembros de los grupos.
CREATE TABLE `grupomiembro` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_grupo` int(10) NOT NULL,
  `id_miembro` int(10) NOT NULL,
  `permisos` int(10) NOT NULL,
  PRIMARY KEY (`id`)
);


-- Tabla		:	Grupo Permisos
-- Descripcion	:	Permisos que pueden tener los miembros dentro de los grupos
-- TODO Añadir la definicion de los poderes
CREATE TABLE `grupopermiso` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
);
  
-- Los elementos basicos de permiso de grupos
INSERT INTO `grupopermiso` (`nombre`, `poder`) VALUES
('administrador', 1),
('miembro', 2);

-- Tabla		:	Pregunta de Seguridad
-- Descripcion	:	Conjunto de preguntas de seguridad con las que los clientes podrian recuperar la cuenta en caso de olvidar la contraseña
CREATE TABLE `pregseguridad` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pregunta` text NOT NULL,
  PRIMARY KEY (`id`)
);

-- Preguntas iniciales
INSERT INTO `pregseguridad` (`pregunta`) VALUES
	('¿Dónde ha nacido?'),
	('¿Quién es tu mejor amigo?'),
	('¿Cómo se llama tu poeta favorito?'),
	('¿Cada cuanto comes pizza?');


CREATE TABLE `proyecto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_grupo` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(32) NULL,
  `deuda` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

  
CREATE TABLE `proyectomiembro` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_proyecto` int(10) NOT NULL,
  `id_miembro` int(10) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `proyectotransaccion` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_proyecto` int(10) NOT NULL,
  `id_miembro` int(10) NOT NULL,
  `tipo` int(1) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `razon` varchar(50) NULL,
  `fecha` date NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
);



CREATE TABLE `sessionhandler` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `hash_var` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hash_var` (`hash_var`)
);


CREATE TABLE `transaccion` (
  `codigo` int(10) NOT NULL AUTO_INCREMENT,
  `source` int(10) NOT NULL,
  `destino` int(10) NOT NULL,
  `dinero` int(10) NOT NULL,
  `fecha` date NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`codigo`)
);


ALTER TABLE `infousuario`
  ADD CONSTRAINT `infousuario_usuario` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;



ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE `grupomiembro`
  ADD CONSTRAINT `grupomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `grupomiembro_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `grupomiembro_ibfk_3` FOREIGN KEY (`permisos`) REFERENCES `grupopermiso` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Filtros para la tabla `proyectomiembro`
--
ALTER TABLE `proyectomiembro`
  ADD CONSTRAINT `proyectomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `proyectomiembro_ibfk_2` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Filtros para la tabla `proyectotransaccion`
--
ALTER TABLE `proyectotransaccion`
  ADD CONSTRAINT `proyectotransaccion_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `proyectotransaccion_ibfk_2` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Filtros para la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD CONSTRAINT `sessionhandler_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `dest` FOREIGN KEY (`destino`) REFERENCES `cuenta` (`numero_cuenta`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `sour` FOREIGN KEY (`source`) REFERENCES `cuenta` (`numero_cuenta`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`source`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_2` FOREIGN KEY (`destino`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;

-- 
-- Filtros para la tabla `usuario`
--

ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`preg_seguridad`) REFERENCES `pregseguridad` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `clientecategoria` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`permisos`) REFERENCES `permisos` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;



