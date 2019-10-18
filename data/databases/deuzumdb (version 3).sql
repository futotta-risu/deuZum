-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-10-2019 a las 19:10:14
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `deuzumdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientcategorias`
--

CREATE TABLE `clientcategorias` (
  `id` int(10) NOT NULL,
  `name` varchar(15) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientclustering`
--

CREATE TABLE `clientclustering` (
  `user_id` int(10) NOT NULL,
  `coordenadas` varchar(100) NOT NULL,
  `categoria` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentacategorias`
--

CREATE TABLE `cuentacategorias` (
  `id` int(10) NOT NULL,
  `name` varchar(15) CHARACTER SET latin1 NOT NULL,
  `descripcion` text CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentaclusteringinfo`
--

CREATE TABLE `cuentaclusteringinfo` (
  `user_id` int(10) NOT NULL,
  `coords` varchar(100) NOT NULL,
  `categoria` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(10) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  `numeroCuenta` int(10) NOT NULL,
  `dinero` int(10) NOT NULL,
  `permisos` int(10) NOT NULL DEFAULT 3
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `id_usuario`, `numeroCuenta`, `dinero`, `permisos`) VALUES
(1, 1, 223, 1022, 3),
(2, 4, 594, 944, 3),
(3, 4, 774, 751, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupopermiso`
--

CREATE TABLE `grupopermiso` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gruposmiembros`
--

CREATE TABLE `gruposmiembros` (
  `id` int(10) NOT NULL,
  `id_grupo` int(10) NOT NULL,
  `miembro` int(10) NOT NULL,
  `permisos` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infousuario`
--

CREATE TABLE `infousuario` (
  `id` int(10) NOT NULL,
  `id_usuario` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(15) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `email` varchar(40) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `sexo` enum('F','M','O') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `poder` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id`, `nombre`, `poder`) VALUES
(1, '', 0),
(2, 'administrador', 1),
(5, 'invitado', 4),
(3, 'programador', 2),
(4, 'usuario', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregseguridad`
--

CREATE TABLE `pregseguridad` (
  `id` int(10) NOT NULL,
  `pregunta` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pregseguridad`
--

INSERT INTO `pregseguridad` (`id`, `pregunta`) VALUES
(1, '¿Dónde ha nacido?'),
(4, '¿Quién es tu mejor amigo?'),
(6, '¿Cómo se llama tu poeta favorito?'),
(7, '¿Cada cuanto comes pizza?'),
(8, '¿Cual es tu id?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `id` int(10) NOT NULL,
  `id_grupo` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectosdeudas`
--

CREATE TABLE `proyectosdeudas` (
  `id` int(10) NOT NULL,
  `id_proyectos` int(10) NOT NULL,
  `id_usuarios` int(10) NOT NULL,
  `pagacobra` tinyint(1) NOT NULL,
  `cantidad` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sessionhandler`
--

CREATE TABLE `sessionhandler` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `hash` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `id` int(10) NOT NULL,
  `source` int(10) NOT NULL,
  `dest` int(10) NOT NULL,
  `dinero` int(10) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `transaccion`
--

INSERT INTO `transaccion` (`id`, `source`, `dest`, `dinero`, `fecha`) VALUES
(2, 2, 3, 932, '2019-10-15'),
(3, 1, 2, 885, '2019-10-15'),
(4, 2, 3, 881, '2019-10-15'),
(5, 2, 3, 1009, '2019-10-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) NOT NULL,
  `user` varchar(32) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `fechaCreacion` date NOT NULL DEFAULT current_timestamp(),
  `pregSeguridad` int(10) NOT NULL,
  `respSeguridad` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `user`, `pass`, `fechaCreacion`, `pregSeguridad`, `respSeguridad`) VALUES
(1, 'erik', 'weik', '2019-10-02', 1, 'assaddsasa'),
(3, 'dearEagle3', 'empathicMandrill4', '2019-10-15', 4, 'mercifulClam2'),
(4, 'tautBurritos7', 'murkyPudding1', '2019-10-15', 1, 'mereTortoise0'),
(5, 'mercifulGarlic2', 'ecstaticHare1', '2019-10-15', 6, 'excludedIcecream7');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientcategorias`
--
ALTER TABLE `clientcategorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientclustering`
--
ALTER TABLE `clientclustering`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `categoria` (`categoria`);

--
-- Indices de la tabla `cuentacategorias`
--
ALTER TABLE `cuentacategorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cuentaclusteringinfo`
--
ALTER TABLE `cuentaclusteringinfo`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `categoria` (`categoria`),
  ADD UNIQUE KEY `categoria_2` (`categoria`);

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ususario` (`id_usuario`),
  ADD KEY `permisos` (`permisos`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `grupopermiso`
--
ALTER TABLE `grupopermiso`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gruposmiembros`
--
ALTER TABLE `gruposmiembros`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_grupo` (`id_grupo`),
  ADD UNIQUE KEY `permisos` (`permisos`),
  ADD UNIQUE KEY `miembro` (`miembro`);

--
-- Indices de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`,`poder`) USING BTREE,
  ADD UNIQUE KEY `poder` (`poder`);

--
-- Indices de la tabla `pregseguridad`
--
ALTER TABLE `pregseguridad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_grupo` (`id_grupo`);

--
-- Indices de la tabla `proyectosdeudas`
--
ALTER TABLE `proyectosdeudas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_proyectos` (`id_proyectos`,`id_usuarios`);

--
-- Indices de la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `hash` (`hash`),
  ADD UNIQUE KEY `ip` (`ip`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `source` (`source`),
  ADD KEY `dest` (`dest`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`user`),
  ADD UNIQUE KEY `user_2` (`user`),
  ADD KEY `pregseguridad` (`pregSeguridad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `grupopermiso`
--
ALTER TABLE `grupopermiso`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `gruposmiembros`
--
ALTER TABLE `gruposmiembros`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pregseguridad`
--
ALTER TABLE `pregseguridad`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientclustering`
--
ALTER TABLE `clientclustering`
  ADD CONSTRAINT `clientclustering_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `clientcategorias` (`id`),
  ADD CONSTRAINT `clientclustering_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `cuentacategorias`
--
ALTER TABLE `cuentacategorias`
  ADD CONSTRAINT `cuentacategorias_ibfk_1` FOREIGN KEY (`id`) REFERENCES `cuentaclusteringinfo` (`categoria`);

--
-- Filtros para la tabla `cuentaclusteringinfo`
--
ALTER TABLE `cuentaclusteringinfo`
  ADD CONSTRAINT `cuentaclusteringinfo_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `id_ususario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permisos` FOREIGN KEY (`permisos`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `gruposmiembros`
--
ALTER TABLE `gruposmiembros`
  ADD CONSTRAINT `gruposmiembros_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  ADD CONSTRAINT `gruposmiembros_ibfk_2` FOREIGN KEY (`miembro`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `gruposmiembros_ibfk_3` FOREIGN KEY (`permisos`) REFERENCES `grupopermiso` (`id`);

--
-- Filtros para la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD CONSTRAINT `infousuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`);

--
-- Filtros para la tabla `proyectosdeudas`
--
ALTER TABLE `proyectosdeudas`
  ADD CONSTRAINT `proyectosdeudas_ibfk_1` FOREIGN KEY (`id_proyectos`) REFERENCES `proyecto` (`id`);

--
-- Filtros para la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD CONSTRAINT `sessionhandler_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `dest` FOREIGN KEY (`dest`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `source` FOREIGN KEY (`source`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`pregSeguridad`) REFERENCES `pregseguridad` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
