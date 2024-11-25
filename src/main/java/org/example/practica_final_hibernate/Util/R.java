package org.example.practica_final_hibernate.Util;

import java.net.URL;

public class R {
    public static URL getResource(String path){
        return Thread.currentThread().getContextClassLoader().getResource("views/"+path);
    }
}
