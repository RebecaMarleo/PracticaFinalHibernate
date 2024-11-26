package org.example.practica_final_hibernate.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo implements Serializable {
    @Id
    @Column(name = "id_grupo")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Id
    @Column(name = "nombre_grupo")
    private String nombre;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Parte> partes;

    public Grupo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
/*
DROP TABLE IF EXISTS `gestionpartes`.`grupos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `nombre_grupo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */