package org.example.practica_final_hibernate.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "partes_tipos")
public class TipoParte implements Serializable {
    @Id
    @Column(name = "color")
    private String color;

    private int puntuacion;

    private String hex;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
    List<Parte> partes;

    public List<Parte> getPartes() {
        return partes;
    }

    public void setPartes(List<Parte> partes) {
        this.partes = partes;
    }

    public TipoParte() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
