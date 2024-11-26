package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements DAO<Alumno> {
    SessionFactory factory;
    public AlumnoDAO(){
        factory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void insertar(Alumno objeto) {
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
    public void modificar(Alumno objeto) {
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
    public void eliminar(Alumno objeto) {
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
    public List<Alumno> listar() {
        List<Alumno> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from Alumno", Alumno.class).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public Alumno buscar(String valor) {
        Alumno alumno = null;
        try{
            Session session = factory.openSession();
            alumno = session.createQuery("from Alumno where numero_expediente = '" + valor + "'", Alumno.class).getSingleResult();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return alumno;
    }
}
