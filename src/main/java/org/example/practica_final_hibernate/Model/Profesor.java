package org.example.practica_final_hibernate.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "profesores")
public class Profesor implements Serializable {
    @Id
    @Column(name = "id_profesor")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String tipo;
    private String numero_asignado;
    private String contrasena;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Parte> partes;

    public Profesor() {
    }

    public Profesor(String nombre, String tipo, String numero_asignado, String contrasena) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.numero_asignado = numero_asignado;
        this.contrasena = contrasena;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero_asignado() {
        return numero_asignado;
    }

    public void setNumero_asignado(String numero_asignado) {
        this.numero_asignado = numero_asignado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return nombre + " (" + numero_asignado + ")";
    }
}


/*
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
 */