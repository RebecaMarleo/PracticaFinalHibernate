package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Grupo;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO implements DAO<Grupo> {
    SessionFactory factory;
    public GrupoDAO(){
        factory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void insertar(Grupo objeto) {
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
    public void modificar(Grupo objeto) {
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
    public void eliminar(Grupo objeto) {
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
    public List<Grupo> listar() {
        List<Grupo> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from Curso", Grupo.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Grupo buscar(String valor) {
        return null;
    }
}
