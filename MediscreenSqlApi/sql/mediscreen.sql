CREATE DATABASE IF NOT EXISTS mediscreen;
use mediscreen;

CREATE TABLE `patient` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE,
  `password` varchar(255),
  `prenom` varchar(255),
  `nom` varchar(255),
  `dob` datetime,
  `sex` char(1),
  `address` varchar(255),
  `phone` varchar(10),
  `family` int,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `appointment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `patient_id` int,
  `praticien_id` int,
  `reserved_at` datetime,
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `praticien` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) UNIQUE,
  `password` varchar(255),
  `prenom` varchar(255),
  `nom` varchar(255),
  `sex` char(1),
  `address` varchar(255),
  `phone` varchar(10),
  `created_at` datetime DEFAULT (now())
);

CREATE TABLE `test` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `risk` varchar(20)
);

ALTER TABLE `appointment` ADD FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

ALTER TABLE `appointment` ADD FOREIGN KEY (`praticien_id`) REFERENCES `praticien` (`id`);

ALTER TABLE `patient` ADD FOREIGN KEY (`family`) REFERENCES `test` (`id`);

INSERT INTO `test` (risk) VALUES ("TestNone"),("TestBorderline"),("TestInDanger"),("TestEarlyOnset");