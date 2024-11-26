package org.example.practica_final_hibernate.Util;

import org.example.practica_final_hibernate.Model.Profesor;

import java.io.InputStream;
import java.net.URL;

public class R {
    public static URL getResource(String path){
        return Thread.currentThread().getContextClassLoader().getResource("views/"+path);
    }
    public static URL getCFG(String path){
        return Thread.currentThread().getContextClassLoader().getResource("cfg/"+path);
    }
    public static Profesor profesorActual;
}
