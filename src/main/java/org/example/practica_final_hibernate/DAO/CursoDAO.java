package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.Curso;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements DAO<Curso> {
    SessionFactory factory;
    public CursoDAO(){
        factory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void insertar(Curso objeto) {
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
    public void modificar(Curso objeto) {
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
    public void eliminar(Curso objeto) {
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
    public List<Curso> listar() {
        List<Curso> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from Curso", Curso.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Curso buscar() {
        return null;
    }
}
