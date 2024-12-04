package org.example.practica_final_hibernate.Util;

import org.example.practica_final_hibernate.Model.Profesor;

import java.net.URL;
import java.util.List;

public class R {
    public static URL getResource(String path){
        return Thread.currentThread().getContextClassLoader().getResource("views/"+path);
    }
    public static URL getCFG(String path){
        return Thread.currentThread().getContextClassLoader().getResource("cfg/"+path);
    }
    public static Profesor profesorActual;

    public static List<String> horas = List.of("8:30-9:20", "9:25-10:15", "10:20-11:10", "11:40-12:30", "12:35-13:25", "13:30-14:20", "16:00-16:50", "16:55-17:45", "17:50-18:40", "18:55-19:45", "19:50-20:40", "20:45-21:35");

    public static  List<String> tiposSancion = List.of("Incoación de expediente", "Reunión con la Comisión de Convivencia", "Otra sanción");
}
