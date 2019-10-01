-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-10-2019 a las 19:53:30
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
-- Base de datos: `deuzum`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(32) NOT NULL,
  `user_id` int(32) NOT NULL,
  `numero` varchar(16) NOT NULL,
  `dinero` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `user_id`, `numero`, `dinero`) VALUES
(12, 12, '828', 805),
(13, 21, '407', 516),
(14, 20, '268', 968),
(15, 11, '681', 563),
(16, 16, '696', 732);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial`
--

CREATE TABLE `historial` (
  `id` int(128) NOT NULL,
  `emisor_id` int(32) NOT NULL,
  `receptor` int(32) NOT NULL,
  `cantidad` int(128) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `historial`
--

INSERT INTO `historial` (`id`, `emisor_id`, `receptor`, `cantidad`, `fecha`) VALUES
(1, 23, 23, 1232, '2019-10-15'),
(2, 9, 23, 71, '2019-10-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(32) NOT NULL,
  `user` varchar(32) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `nombre` varchar(16) DEFAULT NULL,
  `apellido` varchar(16) DEFAULT NULL,
  `mail` varchar(32) DEFAULT NULL,
  `telefono` varchar(16) DEFAULT NULL,
  `pregSeguridad` int(11) NOT NULL,
  `respuesta` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `user`, `pass`, `nombre`, `apellido`, `mail`, `telefono`, `pregSeguridad`, `respuesta`) VALUES
(7, 'jubilantLapwing8', 'outlyingPorpoise8', NULL, NULL, NULL, NULL, 1, 'wrathfulPear4'),
(8, 'sincereRice3', 'pluckyFerret0', NULL, NULL, NULL, NULL, 1, 'excitedBittern2'),
(9, 'wakefulAntelope1', 'lyingBagels5', NULL, NULL, NULL, NULL, 1, 'solemnClam0'),
(10, 'sugaryLizard9', 'selfishToucan1', NULL, NULL, NULL, NULL, 1, 'shamefulBoa0'),
(11, 'eagerPup0', 'superiorLapwing1', NULL, NULL, NULL, NULL, 1, 'gloomyCamel4'),
(12, 'sheepishCockatoo0', 'solemnCurlew2', NULL, NULL, NULL, NULL, 1, 'zestyWildfowl1'),
(13, 'dopeyRuffs9', 'joyfulRelish9', NULL, NULL, NULL, NULL, 1, 'soreEagle2'),
(14, 'tautLocust0', 'jumpyBuck0', NULL, NULL, NULL, NULL, 1, 'alertChile0'),
(15, 'dearHare6', 'mellowDinosaur3', NULL, NULL, NULL, NULL, 1, 'needfulBoa4'),
(16, 'importedPie4', 'aloofDunbird4', NULL, NULL, NULL, NULL, 1, 'sheepishSyrup5'),
(17, 'humorousHare4', 'giddyTacos5', NULL, NULL, NULL, NULL, 1, 'fondFlamingo5'),
(18, 'artisticGarlic4', 'thriftyOatmeal7', NULL, NULL, NULL, NULL, 1, 'cruelWhiting2'),
(19, 'awedEagle7', 'murkyRelish0', NULL, NULL, NULL, NULL, 1, 'scornfulHyena6'),
(20, 'blissfulPolenta0', 'outlyingPlover4', NULL, NULL, NULL, NULL, 1, 'mercifulCheetah9'),
(21, 'goofyCamel1', 'sugaryCrackers3', NULL, NULL, NULL, NULL, 1, 'insecureSnail5'),
(22, 'lyingMare3', 'ardentTruffle6', NULL, NULL, NULL, NULL, 1, 'thrilledOatmeal7'),
(23, 'sincereHyena0', 'exactingPlover7', NULL, NULL, NULL, NULL, 1, 'curiousGnu5'),
(24, 'unhappyJerky6', 'artisticOatmeal0', NULL, NULL, NULL, NULL, 1, 'jubilantSyrup4'),
(25, 'scornfulApricots7', 'ferventPretzels6', NULL, NULL, NULL, NULL, 1, 'anxiousCamel6'),
(26, 'dearCurlew6', 'resolvedBoa9', NULL, NULL, NULL, NULL, 1, 'grizzledHeron4');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `propietario` (`user_id`);

--
-- Indices de la tabla `historial`
--
ALTER TABLE `historial`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `historial`
--
ALTER TABLE `historial`
  MODIFY `id` int(128) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
