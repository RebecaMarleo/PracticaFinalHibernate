-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gestionpartes
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestionpartes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestionpartes`;

-- -----------------------------------------------------
-- Table `gestionpartes`.`grupos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`grupos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `nombre_grupo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `gestionpartes`.`alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`alumnos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`alumnos` (
  `id_alum` INT NOT NULL AUTO_INCREMENT,
  `id_grupo` INT NOT NULL,
  `puntos_acumulados` INT NOT NULL,
  `nombre_alum` VARCHAR(255) NULL DEFAULT NULL,
  `numero_expediente` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_alum`),
  INDEX `FKoif6noujgnb1q4hfucdj3by8q` (`id_grupo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`alumnos`
  ADD CONSTRAINT `FKoif6noujgnb1q4hfucdj3by8q`
  FOREIGN KEY (`id_grupo`)
  REFERENCES `gestionpartes`.`grupos` (`id_grupo`);

-- -----------------------------------------------------
-- Table `gestionpartes`.`profesores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`profesores`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`profesores` (
  `id_profesor` INT NOT NULL AUTO_INCREMENT,
  `contrasena` VARCHAR(255) NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NULL DEFAULT NULL,
  `numero_asignado` VARCHAR(255) NULL DEFAULT NULL,
  `tipo` enum("Profesor", "Jefe de Estudios"),
  PRIMARY KEY (`id_profesor`),
  UNIQUE INDEX `UK_p6ltb4s5eu3ymeanq6rdw944v` (`numero_asignado` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

-- -----------------------------------------------------
-- Table `gestionpartes`.`partes_incidencia`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `gestionpartes`.`partes_tipos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_tipos` (
    `color` VARCHAR(255) PRIMARY KEY,
    `puntuacion` INT(255) NOT NULL DEFAULT 0,
    `hex` VARCHAR(6) NOT NULL DEFAULT 0
);

-- -----------------------------------------------------
-- Table `gestionpartes`.`partes_incidencia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestionpartes`.`partes_incidencia`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_incidencia` (
  `id_alum` INT NULL DEFAULT NULL,
  `id_grupo` INT NULL DEFAULT NULL,
  `id_parte` INT NOT NULL AUTO_INCREMENT,
  `id_profesor` INT NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `hora` VARCHAR(255) NULL DEFAULT NULL,
  `sancion` VARCHAR(255) NULL DEFAULT NULL,
  `color` VARCHAR(255) NULL DEFAULT NULL REFERENCES `partes_tipos`(`color`),
  PRIMARY KEY (`id_parte`),
  INDEX `FKqrx661g5lij25bl2plx6cb2pl` (`id_alum` ASC),
  INDEX `FKckq2ajm1w9wbi3kunm8q3ldp3` (`id_grupo` ASC),
  INDEX `FKniytl2x2lvm632ic1904a1bhb` (`id_profesor` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKckq2ajm1w9wbi3kunm8q3ldp3`
  FOREIGN KEY (`id_grupo`)
  REFERENCES `gestionpartes`.`grupos` (`id_grupo`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKniytl2x2lvm632ic1904a1bhb`
  FOREIGN KEY (`id_profesor`)
  REFERENCES `gestionpartes`.`profesores` (`id_profesor`);

ALTER TABLE `gestionpartes`.`partes_incidencia`
  ADD CONSTRAINT `FKqrx661g5lij25bl2plx6cb2pl`
  FOREIGN KEY (`id_alum`)
  REFERENCES `gestionpartes`.`alumnos` (`id_alum`);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Inserción de profesores en la tabla 'profesores'
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Juan Perez', 'Jefe de Estudios', 1001, 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Alberto Perez', 'Profesor', 1002, 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Maria Lopez', 'Profesor', 1003, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Carlos Sanchez', 'Profesor', 1004, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Laura Gomez', 'Profesor', 1005, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');
INSERT INTO profesores (nombre, tipo, numero_asignado, contrasena) VALUES('Fernando Ruiz', 'Profesor', 1006, '1b18033d8286c4efc126b8a131e85db079c731aca276c9204b6221ca00fedbb0');

-- Inserción de grupos en la tabla 'grupos'
INSERT INTO grupos (nombre_grupo) VALUES('1º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('1º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('2º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('2º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('3º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('3º ESO B');
INSERT INTO grupos (nombre_grupo) VALUES('4º ESO A');
INSERT INTO grupos (nombre_grupo) VALUES('4º ESO B');

-- Inserción de alumnos en la tabla 'alumnos'
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(1, 4, 'Alejandro García', '1001');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(1, 6, 'María Fernández', '1002');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(2, 4, 'Juan López', '1003');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(2, 12, 'Laura Martínez', '1004');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(3, 9, 'Pablo Sánchez', '1005');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(3, 1, 'Sara González', '1006');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(4, 4, 'David Rodríguez', '1007');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(4, 8, 'Lucía Pérez', '1008');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(5, 7, 'Daniel Gómez', '1009');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(5, 20, 'Elena Díaz', '1010');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(6, 33, 'Javier Ruiz', '1011');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(6, 4, 'Marta Morales', '1012');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(7, 4, 'Carlos Álvarez', '1013');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(7, 9, 'Ana Ortega', '1014');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(8, 16, 'Luis Navarro', '1015');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(8, 10, 'Carmen Ramírez', '1016');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(1, 4, 'Alberto Torres', '1017');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(2, 1, 'Paula Ibáñez', '1018');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(3, 8, 'Miguel Romero', '1019');
INSERT INTO alumnos (id_grupo, puntos_acumulados, nombre_alum, numero_expediente) VALUES(4, 3, 'Isabel Hernández', '1020');


-- Inserción de tipos de parte
INSERT INTO partes_tipos (color, puntuacion, hex) VALUES('Verde', 1, 'befc77');
INSERT INTO partes_tipos (color, puntuacion, hex) VALUES('Naranja', 6, 'ffa500');
INSERT INTO partes_tipos (color, puntuacion, hex) VALUES('Rojo', 12, 'ff0000');

-- Inserción de partes de incidencia en la tabla 'partes_incidencia'
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(1, 1, 1, 'Incidente menor', '2024-01-01', '08:30-09:20', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(2, 1, 2, 'Incidente moderado', '2024-01-02', '09:25-10:15', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(3, 2, 3, 'Incidente grave', '2024-01-03', '10:20-11:10', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(4, 2, 4, 'Incidente menor', '2024-01-04', '11:40-12:30', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(5, 3, 5, 'Incidente moderado', '2024-01-05', '12:35-13:25', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(6, 3, 6, 'Incidente grave', '2024-01-06', '13:30-14:20', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(7, 4, 1, 'Incidente menor', '2024-01-07', '16:00-16:50', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(8, 4, 2, 'Incidente moderado', '2024-01-08', '16:55-17:45', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(9, 5, 3, 'Incidente grave', '2024-01-09', '17:50-18:40', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(10, 5, 4, 'Incidente menor', '2024-01-10', '18:55-19:45', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(11, 6, 5, 'Incidente moderado', '2024-01-11', '09:25-10:15', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(12, 6, 6, 'Incidente grave', '2024-01-12', '10:20-11:10', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(13, 7, 1, 'Incidente menor', '2024-01-13', '11:40-12:30', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(14, 7, 2, 'Incidente moderado', '2024-01-14', '12:35-13:25', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(15, 8, 3, 'Incidente grave', '2024-01-15', '13:30-14:20', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(16, 8, 4, 'Incidente menor', '2024-01-16', '16:00-16:50', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(17, 1, 5, 'Incidente moderado', '2024-01-17', '16:55-17:45', 'Suspensión', 'Naranja');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(18, 2, 6, 'Incidente grave', '2024-01-18', '17:50-18:40', 'Expulsión', 'Rojo');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(19, 3, 1, 'Incidente menor', '2024-01-19', '18:55-19:45', 'Advertencia', 'Verde');
INSERT INTO partes_incidencia (id_alum, id_grupo, id_profesor, descripcion, fecha, hora, sancion, color) VALUES(20, 4, 2, 'Incidente moderado', '2024-01-20', '09:25-10:15', 'Suspensión', 'Naranja');