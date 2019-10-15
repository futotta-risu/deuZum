-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2019 a las 12:20:39
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

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
-- Estructura de tabla para la tabla `grupopermiso`
--

CREATE TABLE `grupopermiso` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupoproyecto`
--

CREATE TABLE `grupoproyecto` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gruposmiembros`
--

CREATE TABLE `gruposmiembros` (
  `id` int(10) NOT NULL,
  `miembro` int(10) NOT NULL,
  `permisos` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infousuario`
--

CREATE TABLE `infousuario` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(15) NOT NULL,
  `telefono` varchar(9) NOT NULL
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
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ususario` (`id_usuario`),
  ADD KEY `permisos` (`permisos`);

--
-- Indices de la tabla `grupopermiso`
--
ALTER TABLE `grupopermiso`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`,`poder`);

--
-- Indices de la tabla `grupoproyecto`
--
ALTER TABLE `grupoproyecto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gruposmiembros`
--
ALTER TABLE `gruposmiembros`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD KEY `id` (`id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`,`poder`) USING BTREE;

--
-- Indices de la tabla `pregseguridad`
--
ALTER TABLE `pregseguridad`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `hash` (`hash`),
  ADD UNIQUE KEY `ip` (`ip`);

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
-- AUTO_INCREMENT de la tabla `grupoproyecto`
--
ALTER TABLE `grupoproyecto`
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
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `id_ususario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permisos` FOREIGN KEY (`permisos`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `pregseguridad` FOREIGN KEY (`pregSeguridad`) REFERENCES `pregseguridad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
