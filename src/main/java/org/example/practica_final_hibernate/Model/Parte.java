package org.example.practica_final_hibernate.Model;

public class Parte {
}
/*
DROP TABLE IF EXISTS `gestionpartes`.`partes_incidencia`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`partes_incidencia` (
  `id_alum` INT NULL DEFAULT NULL,
  `id_grupo` INT NULL DEFAULT NULL,
  `id_parte` INT NOT NULL AUTO_INCREMENT,
  `id_profesor` INT NULL DEFAULT NULL,
  `descripcion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha` VARCHAR(255) NULL DEFAULT NULL,
  `hora` VARCHAR(255) NULL DEFAULT NULL,
  `sancion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_parte`),
  INDEX `FKqrx661g5lij25bl2plx6cb2pl` (`id_alum` ASC),
  INDEX `FKckq2ajm1w9wbi3kunm8q3ldp3` (`id_grupo` ASC),
  INDEX `FKniytl2x2lvm632ic1904a1bhb` (`id_profesor` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */