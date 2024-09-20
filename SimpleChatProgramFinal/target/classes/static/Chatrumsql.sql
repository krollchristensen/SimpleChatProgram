-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Vært: 127.0.0.1:3306
-- Genereringstid: 17. 09 2024 kl. 19:49:28
-- Serverversion: 8.2.0
-- PHP-version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";   


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chatrum`
--

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `chatrooms`
--

DROP TABLE IF EXISTS `chatrooms`;
CREATE TABLE IF NOT EXISTS `chatrooms` (
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `room_name` varchar(100) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `room_name` (`room_name`),
    KEY `created_by` (`created_by`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `userchatrooms`
--

DROP TABLE IF EXISTS `userchatrooms`;
CREATE TABLE IF NOT EXISTS `userchatrooms` (
                                               `user_id` int NOT NULL,
                                               `room_id` int NOT NULL,
                                               `joined_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                               PRIMARY KEY (`user_id`,`room_id`),
    KEY `room_id` (`room_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur-dump for tabellen `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `username` varchar(50) NOT NULL,
    `password_hash` varchar(255) NOT NULL,
    `email` varchar(100) DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Begrænsninger for dumpede tabeller
--

--
-- Begrænsninger for tabel `chatrooms`
--
ALTER TABLE `chatrooms`
    ADD CONSTRAINT `chatrooms_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`);

--
-- Begrænsninger for tabel `userchatrooms`
--
ALTER TABLE `userchatrooms`
    ADD CONSTRAINT `userchatrooms_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `userchatrooms_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `chatrooms` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
