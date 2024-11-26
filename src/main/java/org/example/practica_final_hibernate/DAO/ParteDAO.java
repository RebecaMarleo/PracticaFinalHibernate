package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ParteDAO implements DAO<Parte>{

    SessionFactory factory;
    public ParteDAO(){
        factory = HibernateUtils.getSessionFactory();
    }

    @Override
    public void insertar(Parte objeto) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void modificar(Parte objeto) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.update(objeto);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public void eliminar(Parte objeto) {
        Session session = factory.openSession();
        try{
            session.beginTransaction();
            session.remove(objeto);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        session.close();
    }

    @Override
    public List<Parte> listar() {
        List<Parte> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from Parte", Parte.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Parte buscar() {
        return null;
    }
}
