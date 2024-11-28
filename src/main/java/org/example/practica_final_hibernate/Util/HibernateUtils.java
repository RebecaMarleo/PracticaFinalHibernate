package org.example.practica_final_hibernate.Util;

import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.Grupo;
import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Model.Profesor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {

    static SessionFactory factory = null;

    static {
        Configuration cfg = new Configuration();
        cfg.configure(R.getCFG("hibernate.cfg.xml"));
        // Se registran las clases que hay que MAPEAR con cada tabla de la base de datos

        cfg.addAnnotatedClass(Alumno.class);
        cfg.addAnnotatedClass(Profesor.class);
        cfg.addAnnotatedClass(Parte.class);
        cfg.addAnnotatedClass(Grupo.class);
        cfg.addAnnotatedClass(Parte.class);

        //configuration.addAnnotatedClass(Clase1.class);
        //configuration.addAnnotatedClass(Clase2.class);
        //configuration.addAnnotatedClass(Clase3.class);

        factory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }


    public static Session getSession() {
        return factory.openSession();
    }




}
