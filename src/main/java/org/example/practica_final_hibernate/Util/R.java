package org.example.practica_final_hibernate.Util;

import java.io.InputStream;
import java.net.URL;

public class R {
    public static URL getResource(String path){
        return Thread.currentThread().getContextClassLoader().getResource("views/"+path);
    }
    public static URL getCFG(String path){
        return Thread.currentThread().getContextClassLoader().getResource("cfg/"+path);
    }
}
