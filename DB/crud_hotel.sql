-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-04-2025 a las 05:08:48
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `crud_hotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `city`
--

CREATE TABLE `city` (
  `id_city` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `city`
--

INSERT INTO `city` (`id_city`, `name`) VALUES
(6, 'Medellín'),
(7, 'Pitalito'),
(8, 'Neiva'),
(10, 'Garzon2'),
(29, 'Cundinamarca'),
(33, 'Jamaica2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `id_document_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`id`, `document_number`, `email`, `first_name`, `last_name`, `phone`, `id_document_type`) VALUES
(1, '12345678', 'juan.perez@example.com', 'Juan', 'Pérez', '+5491112345678', 1),
(3, '98765432', 'ana.gomez@example.com', 'Ana', 'Gómez', '+5491123456789', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `document_type`
--

CREATE TABLE `document_type` (
  `id_document_type` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `document_type`
--

INSERT INTO `document_type` (`id_document_type`, `name`) VALUES
(1, 'Pasaporte'),
(3, 'Cédula de Identidad'),
(4, 'Licencia de Conducir'),
(5, 'pruebas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employee`
--

CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL,
  `document_number` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `id_document_type` int(11) NOT NULL,
  `id_hotel` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `employee`
--

INSERT INTO `employee` (`id_employee`, `document_number`, `email`, `first_name`, `last_name`, `phone`, `id_document_type`, `id_hotel`, `id_role`) VALUES
(1, '45678912', 'carlos.ramirez@example.com', 'Carlos Andres', 'Ramírez', '+51 987654321', 3, 9, 1),
(3, 'LM87654321', 'laura.mendoza@hotelperu.com', 'Laura', 'Mendoza', '+51 987654321', 4, 10, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hotel`
--

CREATE TABLE `hotel` (
  `id_hotel` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hotel`
--

INSERT INTO `hotel` (`id_hotel`, `address`, `email`, `name`, `phone`, `city_id`) VALUES
(9, 'Av. Central 456, Cusco, Perú', 'reservas@miradorandes.com', 'Hotel Mirador de los Andes', '+51 84 765432', 6),
(10, 'Carrera 12 #45-67, San Gil, Santander, Colombia', 'contacto@elparaisohotel.com', 'Hotel El Paraíso', '+57 7 7245678', 7),
(13, 'calle 3', 'hotel@gmail.com', 'hotel  si ', '11222', 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invoice`
--

CREATE TABLE `invoice` (
  `id_invoice` int(11) NOT NULL,
  `issue_date` datetime(6) NOT NULL,
  `payment_status` enum('CANCELLED','PAID','PENDING') NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `tax` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `id_reservation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `invoice`
--

INSERT INTO `invoice` (`id_invoice`, `issue_date`, `payment_status`, `subtotal`, `tax`, `total`, `id_reservation`) VALUES
(6, '2025-04-20 15:30:13.000000', 'PENDING', 460000.00, 73600.00, 533600.00, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `notes` varchar(500) DEFAULT NULL,
  `status` enum('CANCELLED','CONFIRMED','PENDING') NOT NULL,
  `customer_id` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `number_night` int(11) DEFAULT NULL,
  `numberday` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `created_at`, `notes`, `status`, `customer_id`, `id_employee`, `number_night`, `numberday`) VALUES
(5, '2025-04-20 15:30:13.000000', 'Reserva para cliente frecuente', 'PENDING', 1, 3, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservation_room`
--

CREATE TABLE `reservation_room` (
  `id_reservation_room` int(11) NOT NULL,
  `applied_price` decimal(10,2) NOT NULL,
  `id_reservation` int(11) NOT NULL,
  `id_room` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reservation_room`
--

INSERT INTO `reservation_room` (`id_reservation_room`, `applied_price`, `id_reservation`, `id_room`) VALUES
(17, 230000.00, 5, 5),
(18, 230000.00, 5, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id_role`, `name`) VALUES
(1, 'Administrador'),
(2, 'Recepcionista'),
(4, 'Supervisor2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `id_room` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `room_number` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_type_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id_room`, `name`, `room_number`, `status`, `hotel_id`, `room_type_id`) VALUES
(5, 'Suite', '101', 'Available', 10, 9),
(7, 'Suite2', '102', 'Available', 10, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `type_room`
--

CREATE TABLE `type_room` (
  `id_type_room` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price_day` decimal(10,2) NOT NULL,
  `price_night` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `type_room`
--

INSERT INTO `type_room` (`id_type_room`, `name`, `price_day`, `price_night`) VALUES
(6, 'Habitación Doble Premium', 95000.00, 120000.00),
(7, 'Suite Presidencial', 250000.00, 300000.00),
(9, 'Habitación Estándar2', 50000.00, 65000.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id_city`);

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKmcxhs25405p3p7mrre6he23nf` (`document_number`),
  ADD UNIQUE KEY `UKdwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD KEY `FKdapguopy08vw6t21nqsvvo5o1` (`id_document_type`);

--
-- Indices de la tabla `document_type`
--
ALTER TABLE `document_type`
  ADD PRIMARY KEY (`id_document_type`);

--
-- Indices de la tabla `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id_employee`),
  ADD KEY `fk_employee_document_type` (`id_document_type`),
  ADD KEY `fk_employee_rol` (`id_role`),
  ADD KEY `fk_employee_hotel` (`id_hotel`);

--
-- Indices de la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id_hotel`),
  ADD KEY `fk_hotel_city` (`city_id`);

--
-- Indices de la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`id_invoice`),
  ADD UNIQUE KEY `UKdqu73ugl1dsjvwjbbbgpxox7b` (`id_reservation`);

--
-- Indices de la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `FK41v6ueo0hiran65w8y1cta2c2` (`customer_id`),
  ADD KEY `FKpo3tapoa95x2q0t7nhg3db1er` (`id_employee`);

--
-- Indices de la tabla `reservation_room`
--
ALTER TABLE `reservation_room`
  ADD PRIMARY KEY (`id_reservation_room`),
  ADD KEY `FK5nd78mgepq6yoqp781ul3b866` (`id_reservation`),
  ADD KEY `FKfq5b1is1cphup8ym60m9pp2fb` (`id_room`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id_room`),
  ADD UNIQUE KEY `UK4l8mm4fqoos6fcbx76rvqxer` (`name`),
  ADD UNIQUE KEY `UKfvetq5dj3wcvmdf19bbof0os6` (`room_number`),
  ADD KEY `fk_room_type` (`room_type_id`),
  ADD KEY `fk_room_hotel` (`hotel_id`);

--
-- Indices de la tabla `type_room`
--
ALTER TABLE `type_room`
  ADD PRIMARY KEY (`id_type_room`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `city`
--
ALTER TABLE `city`
  MODIFY `id_city` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `document_type`
--
ALTER TABLE `document_type`
  MODIFY `id_document_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `employee`
--
ALTER TABLE `employee`
  MODIFY `id_employee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id_hotel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `invoice`
--
ALTER TABLE `invoice`
  MODIFY `id_invoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `reservation_room`
--
ALTER TABLE `reservation_room`
  MODIFY `id_reservation_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `id_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `type_room`
--
ALTER TABLE `type_room`
  MODIFY `id_type_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FKdapguopy08vw6t21nqsvvo5o1` FOREIGN KEY (`id_document_type`) REFERENCES `document_type` (`id_document_type`);

--
-- Filtros para la tabla `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKmttrp76s8rdjg2lar9ajjcctx` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `FKnuack8282ftag72johkicpnhl` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FKs8566rlm2h51sg9p26jlptl5t` FOREIGN KEY (`id_document_type`) REFERENCES `document_type` (`id_document_type`),
  ADD CONSTRAINT `fk_employee_document_type` FOREIGN KEY (`id_document_type`) REFERENCES `document_type` (`id_document_type`),
  ADD CONSTRAINT `fk_employee_hotel` FOREIGN KEY (`id_hotel`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `fk_employee_rol` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Filtros para la tabla `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `fk_hotel_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id_city`);

--
-- Filtros para la tabla `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `FKqluyrgfq2ljxt1uj6mfnwdik1` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`id_reservation`);

--
-- Filtros para la tabla `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK41v6ueo0hiran65w8y1cta2c2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKpo3tapoa95x2q0t7nhg3db1er` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`);

--
-- Filtros para la tabla `reservation_room`
--
ALTER TABLE `reservation_room`
  ADD CONSTRAINT `FK5nd78mgepq6yoqp781ul3b866` FOREIGN KEY (`id_reservation`) REFERENCES `reservation` (`id_reservation`),
  ADD CONSTRAINT `FKfq5b1is1cphup8ym60m9pp2fb` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `fk_room_hotel` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id_hotel`),
  ADD CONSTRAINT `fk_room_type` FOREIGN KEY (`room_type_id`) REFERENCES `type_room` (`id_type_room`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
