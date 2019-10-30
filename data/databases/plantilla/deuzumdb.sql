-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-10-2019 a las 12:55:03
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
-- Estructura de tabla para la tabla `clientecategoria`
--

CREATE TABLE `clientecategoria` (
  `nombre` varchar(15) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `numero_cuenta` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `dinero` int(11) NOT NULL,
  `tipo_cuenta` varchar(15) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `categoria` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentacategoria`
--

CREATE TABLE `cuentacategoria` (
  `nombre` varchar(20) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE `grupo` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) CHARACTER SET latin1 NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupomiembro`
--

CREATE TABLE `grupomiembro` (
  `id` int(10) NOT NULL,
  `id_grupo` int(10) NOT NULL,
  `id_miembro` int(10) NOT NULL,
  `permisos` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupopermiso`
--

CREATE TABLE `grupopermiso` (
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupopermiso`
--

INSERT INTO `grupopermiso` (`nombre`, `poder`) VALUES
('administrador', 1),
('miembro', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infousuario`
--

CREATE TABLE `infousuario` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `apellido` varchar(15) NOT NULL,
  `telefono` varchar(9) NOT NULL,
  `email` varchar(40) NOT NULL,
  `direccion` varchar(50) NOT NULL DEFAULT 'Bilbao',
  `fecha_nacimiento` date NOT NULL DEFAULT current_timestamp(),
  `sexo` enum('F','M','O') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `nombre` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `poder` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`nombre`, `poder`) VALUES
('administrador', 1),
('programador', 2),
('cliente', 3);

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
  `descripcion` text NOT NULL,
  `deuda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectomiembro`
--

CREATE TABLE `proyectomiembro` (
  `id` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `id_miembro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectotransaccion`
--

CREATE TABLE `proyectotransaccion` (
  `id` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `id_miembro` int(11) NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `razon` text NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sessionhandler`
--

CREATE TABLE `sessionhandler` (
  `user_id` int(10) NOT NULL,
  `ip` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `hash` varchar(20) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `codigo` int(10) NOT NULL,
  `source` int(10) NOT NULL,
  `destino` int(10) NOT NULL,
  `dinero` int(10) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(32) NOT NULL,
  `contraseña` varchar(32) NOT NULL,
  `fecha_creacion` date DEFAULT current_timestamp(),
  `preg_seguridad` int(11) NOT NULL,
  `resp_seguridad` varchar(32) NOT NULL,
  `permisos` varchar(15) DEFAULT NULL,
  `categoria` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientecategoria`
--
ALTER TABLE `clientecategoria`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`numero_cuenta`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `cuentacategoria`
--
ALTER TABLE `cuentacategoria`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `grupo`
--
ALTER TABLE `grupo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `grupomiembro`
--
ALTER TABLE `grupomiembro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_grupo` (`id_grupo`),
  ADD UNIQUE KEY `permisos` (`permisos`),
  ADD UNIQUE KEY `miembro` (`id_miembro`);

--
-- Indices de la tabla `grupopermiso`
--
ALTER TABLE `grupopermiso`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`nombre`),
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
-- Indices de la tabla `proyectomiembro`
--
ALTER TABLE `proyectomiembro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_miembro` (`id_miembro`),
  ADD KEY `id_proyecto` (`id_proyecto`);

--
-- Indices de la tabla `proyectotransaccion`
--
ALTER TABLE `proyectotransaccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_proyecto` (`id_proyecto`),
  ADD KEY `id_miembro` (`id_miembro`);

--
-- Indices de la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD UNIQUE KEY `hash` (`hash`),
  ADD UNIQUE KEY `ip` (`ip`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `source` (`source`),
  ADD KEY `dest` (`destino`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `preg_seguridad` (`preg_seguridad`),
  ADD KEY `categoria` (`categoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `numero_cuenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `grupomiembro`
--
ALTER TABLE `grupomiembro`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

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
-- AUTO_INCREMENT de la tabla `proyectomiembro`
--
ALTER TABLE `proyectomiembro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proyectotransaccion`
--
ALTER TABLE `proyectotransaccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `codigo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `cuentacategoria` (`nombre`);

--
-- Filtros para la tabla `grupomiembro`
--
ALTER TABLE `grupomiembro`
  ADD CONSTRAINT `grupomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `grupomiembro_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`);

--
-- Filtros para la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD CONSTRAINT `infousuario_ibfk_1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`);

--
-- Filtros para la tabla `proyectomiembro`
--
ALTER TABLE `proyectomiembro`
  ADD CONSTRAINT `proyectomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `proyectomiembro_ibfk_2` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`);

--
-- Filtros para la tabla `proyectotransaccion`
--
ALTER TABLE `proyectotransaccion`
  ADD CONSTRAINT `proyectotransaccion_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`),
  ADD CONSTRAINT `proyectotransaccion_ibfk_2` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD CONSTRAINT `sessionhandler_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `dest` FOREIGN KEY (`destino`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `source` FOREIGN KEY (`source`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`source`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `transaccion_ibfk_2` FOREIGN KEY (`destino`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`preg_seguridad`) REFERENCES `pregseguridad` (`id`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `clientecategoria` (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
