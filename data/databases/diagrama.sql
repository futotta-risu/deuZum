CREATE TABLE `usuario` (
  `id` int PRIMARY KEY,
  `user` varchar(255) UNIQUE NOT NULL,
  `pass` varchar(255) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `pregSeguridad` int,
  `respSeguridad` varchar(255) NOT NULL
);

CREATE TABLE `informacionUsuario` (
  `id` int PRIMARY KEY,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `telefono` varchar(255),
  `email` varchar(255),
  `direccion` varchar(255)
);

CREATE TABLE `sesionHandler` (
  `id` int PRIMARY KEY,
  `user_id` int,
  `ip` varchar(255) UNIQUE,
  `hash` varchar(255) UNIQUE
);

CREATE TABLE `preguntasSeguridad` (
  `id` int PRIMARY KEY,
  `pregunta` varchar(255) UNIQUE NOT NULL
);

CREATE TABLE `cuentas` (
  `id` int PRIMARY KEY,
  `id_usuario` U,
  `numeroCuenta` int UNIQUE NOT NULL,
  `dinero` int NOT NULL DEFAULT 0,
  `permisos` P
);

CREATE TABLE `transacciones` (
  `code` int PRIMARY KEY,
  `source` U NOT NULL,
  `dest` U NOT NULL,
  `dinero` int NOT NULL,
  `fecha` date NOT NULL
);

CREATE TABLE `Permisos` (
  `id` int PRIMARY KEY,
  `nombre` varchar(255),
  `poder` int
);

CREATE TABLE `ClientCategorias` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `descripcion` text
);

CREATE TABLE `CuentaCategorias` (
  `id` int PRIMARY KEY,
  `name` varchar(255),
  `descripcion` text
);

CREATE TABLE `ClientClustering` (
  `user_id` int PRIMARY KEY,
  `coordenadas` nDoubleArray,
  `categoria` CCt
);

CREATE TABLE `CuentaClusteringInfo` (
  `user_id` int PRIMARY KEY,
  `coords` nDoubleArray,
  `categoria` CCu
);

CREATE TABLE `Grupo` (
  `id` int PRIMARY KEY,
  `nombre` varcahr NOT NULL,
  `miembros` C NOT NULL
);

CREATE TABLE `ProyectosDeudas` (
  `id` int PRIMARY KEY,
  `proyecto` PR,
  `usuario` U,
  `pagaCobra` boolean,
  `cantidad` int
);

CREATE TABLE `Proyectos` (
  `id` int PRIMARY KEY,
  `grupo` GP,
  `deudas` DE
);

ALTER TABLE `usuario` ADD FOREIGN KEY (`id`) REFERENCES `informacionUsuario` (`id`);

ALTER TABLE `usuario` ADD FOREIGN KEY (`pregSeguridad`) REFERENCES `preguntasSeguridad` (`id`);

ALTER TABLE `sesionHandler` ADD FOREIGN KEY (`user_id`) REFERENCES `cuentas` (`id`);

ALTER TABLE `cuentas` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

ALTER TABLE `cuentas` ADD FOREIGN KEY (`permisos`) REFERENCES `Permisos` (`id`);

ALTER TABLE `transacciones` ADD FOREIGN KEY (`source`) REFERENCES `cuentas` (`id`);

ALTER TABLE `transacciones` ADD FOREIGN KEY (`dest`) REFERENCES `cuentas` (`id`);

ALTER TABLE `ClientClustering` ADD FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`);

ALTER TABLE `ClientCategorias` ADD FOREIGN KEY (`id`) REFERENCES `ClientClustering` (`categoria`);

ALTER TABLE `CuentaClusteringInfo` ADD FOREIGN KEY (`user_id`) REFERENCES `cuentas` (`id`);

ALTER TABLE `CuentaCategorias` ADD FOREIGN KEY (`id`) REFERENCES `CuentaClusteringInfo` (`categoria`);

ALTER TABLE `Grupo` ADD FOREIGN KEY (`miembros`) REFERENCES `cuentas` (`id`);

ALTER TABLE `ProyectosDeudas` ADD FOREIGN KEY (`usuario`) REFERENCES `cuentas` (`id`);

ALTER TABLE `Proyectos` ADD FOREIGN KEY (`grupo`) REFERENCES `Grupo` (`id`);

ALTER TABLE `ProyectosDeudas` ADD FOREIGN KEY (`id`) REFERENCES `Proyectos` (`deudas`);
