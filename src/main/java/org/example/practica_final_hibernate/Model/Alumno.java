package org.example.practica_final_hibernate.Model;

import javax.persistence.*;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @Column(name = "id_alum")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
}
/*
DROP TABLE IF EXISTS `gestionpartes`.`alumnos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`alumnos` (
  `id_alum` INT NOT NULL AUTO_INCREMENT,
  `id_grupo` INT NOT NULL,
  `puntos_acumulados` INT NOT NULL,
  `nombre_alum` VARCHAR(255) NULL DEFAULT NULL,
  `numero_expediente` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_alum`),
  INDEX `FKoif6noujgnb1q4hfucdj3by8q` (`id_grupo` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;

 */