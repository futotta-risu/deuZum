﻿-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-11-2019 a las 15:20:16
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
-- create database deuzumdb;
-- use deuzumdb;
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
  `categoria` varchar(20) DEFAULT NULL,
  CHECK (numero_cuenta BETWEEN 0 and 99999999999),
  CHECK (id_usuario BETWEEN 0 and 99999999999),
  CHECK (estado BETWEEN 0 and 99999999999)
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
  `descripcion` text DEFAULT NULL,
  CHECK (id BETWEEN 0 and 9999999999) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupomiembro`
--

CREATE TABLE `grupomiembro` (
  `id` int(10) NOT NULL,
  `id_grupo` int(10) NOT NULL,
  `id_miembro` int(10) NOT NULL,
  `permisos` int(10) NOT NULL,
  CHECK (id BETWEEN 0 and 9999999999), 
  CHECK (id_grupo BETWEEN 0 and 9999999999), 
  CHECK (id_miembro BETWEEN 0 and 9999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupopermiso`
--

CREATE TABLE `grupopermiso` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL,
  CHECK (id BETWEEN 0 and 9999999999), 
  CHECK (poder BETWEEN 0 and 9999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupopermiso`
--

INSERT INTO `grupopermiso` (`id`, `nombre`, `poder`) VALUES
(1, 'administrador', 1),
(2, 'miembro', 2);

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
  `direccion` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL DEFAULT current_timestamp(),
  `sexo` enum('F','M','O') NOT NULL DEFAULT 'O',
  CHECK (id BETWEEN 0 and 9999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `poder` int(10) NOT NULL,
  CHECK (id BETWEEN 0 and 9999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id`, `nombre`, `poder`) VALUES
(1, 'administrador', 1),
(2, 'programador', 2),
(3, 'cliente', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregseguridad`
--

CREATE TABLE `pregseguridad` (
  `id` int(10) NOT NULL,
  `pregunta` text NOT NULL,
  CHECK (id BETWEEN 0 and 9999999999)
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
  `deuda` int(11) DEFAULT NULL,
  CHECK (id BETWEEN 0 and 9999999999),
  CHECK (id_grupo BETWEEN 0 and 9999999999), 
  CHECK (id_deuda BETWEEN 0 and 99999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectomiembro`
--

CREATE TABLE `proyectomiembro` (
  `id` int(11) NOT NULL,
  `id_proyecto` int(11) NOT NULL,
  `id_miembro` int(11) NOT NULL,
  CHECK (id BETWEEN 0 and 99999999999), 
  CHECK (id_proyecto BETWEEN 0 and 99999999999), 
  CHECK (id_usuario BETWEEN 0 and 99999999999)
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
  `fecha` date NOT NULL DEFAULT current_timestamp(),
  CHECK (id BETWEEN 0 and 99999999999),
  CHECK (id_proyecto BETWEEN 0 and 99999999999),
  CHECK (id_miembro BETWEEN 0 and 99999999999), 
  CHECK (tipo BETWEEN 0 and 9), 
  CHECK (cantidad BETWEEN 0 and 99999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sessionhandler`
--

CREATE TABLE `sessionhandler` (
  `user_id` int(10) NOT NULL,
  `ip` varchar(15) CHARACTER SET utf8mb4 NOT NULL,
  `hash` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  CHECK (user_id BETWEEN 0 and 9999999999)
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
  `fecha` date NOT NULL DEFAULT current_timestamp(),
  CHECK (codigo BETWEEN 0 and 9999999999), 
  CHECK (source BETWEEN 0 and 9999999999), 
  CHECK (destino BETWEEN 0 and 9999999999), 
  CHECK (dinero BETWEEN 0 and 9999999999)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(32) NOT NULL,
  `contraseña` varchar(32) NOT NULL,
  `fecha_creacion` timestamp NULL DEFAULT current_timestamp(),
  `preg_seguridad` int(11) NOT NULL,
  `resp_seguridad` varchar(32) NOT NULL,
  `permisos` int(10) DEFAULT NULL,
  `categoria` varchar(15) DEFAULT NULL,
  CHECK (id BETWEEN 0 and 99999999999), 
  CHECK (preg_seguridad BETWEEN 0 and 99999999999), 
  CHECK (permisos BETWEEN 0 and 99999999999)
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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

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
  ADD KEY `categoria` (`categoria`),
  ADD KEY `permisos` (`permisos`);

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
-- AUTO_INCREMENT de la tabla `grupopermiso`
--
ALTER TABLE `grupopermiso`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `infousuario`
--
ALTER TABLE `infousuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `cuentacategoria` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `grupomiembro`
--
ALTER TABLE `grupomiembro`
  ADD CONSTRAINT `grupomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `grupomiembro_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `grupomiembro_ibfk_3` FOREIGN KEY (`permisos`) REFERENCES `grupopermiso` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `infousuario`
--
ALTER TABLE `infousuario`
  ADD CONSTRAINT `infousuario_ibfk_1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `proyecto_ibfk_1` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyectomiembro`
--
ALTER TABLE `proyectomiembro`
  ADD CONSTRAINT `proyectomiembro_ibfk_1` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proyectomiembro_ibfk_2` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `proyectotransaccion`
--
ALTER TABLE `proyectotransaccion`
  ADD CONSTRAINT `proyectotransaccion_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proyectotransaccion_ibfk_2` FOREIGN KEY (`id_miembro`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sessionhandler`
--
ALTER TABLE `sessionhandler`
  ADD CONSTRAINT `sessionhandler_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `dest` FOREIGN KEY (`destino`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `source` FOREIGN KEY (`source`) REFERENCES `cuentas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_1` FOREIGN KEY (`source`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaccion_ibfk_2` FOREIGN KEY (`destino`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`preg_seguridad`) REFERENCES `pregseguridad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `clientecategoria` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`permisos`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
 