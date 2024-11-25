package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO implements DAO<Profesor> {
    SessionFactory factory;
    public ProfesorDAO(){
        factory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void insertar(Profesor objeto) {
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
    public void modificar(Profesor objeto) {
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
    public void eliminar(Profesor objeto) {
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
    public List<Profesor> listar() {
        List<Profesor> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from Parte", Profesor.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Profesor buscar() {
        return null;
    }
}
