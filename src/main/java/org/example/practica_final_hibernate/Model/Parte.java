package org.example.practica_final_hibernate.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "partes_incidencia")
public class Parte implements Serializable {
    @Id
    @Column(name = "id_parte")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String descripcion;

    private String sancion;

    private LocalDate fecha;

    private String hora;

    @ManyToOne
    @JoinColumn(name="id_alum", referencedColumnName = "id_alum")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name="id_grupo", referencedColumnName = "id_grupo")
    private Grupo grupo;

    @ManyToOne
    @JoinColumn(name="id_profesor", referencedColumnName = "id_profesor")
    private Profesor profesor;

    public Parte() {
    }


    public Parte(String descripcion, LocalDate fecha, String hora, Alumno alumno, Grupo grupo, Profesor profesor, String sancion) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.alumno = alumno;
        this.grupo = grupo;
        this.profesor = profesor;
        this.sancion = sancion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
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