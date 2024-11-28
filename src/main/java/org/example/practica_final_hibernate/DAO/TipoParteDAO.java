package org.example.practica_final_hibernate.DAO;

import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Model.TipoParte;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class TipoParteDAO implements DAO<TipoParte> {
    SessionFactory factory;
    public TipoParteDAO(){
        factory = HibernateUtils.getSessionFactory();
    }
    @Override
    public void insertar(TipoParte objeto) {
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
    public void modificar(TipoParte objeto) {
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
    public void eliminar(TipoParte objeto) {
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
    public List<TipoParte> listar() {
        List<TipoParte> lista = new ArrayList<>();
        try{
            Session session = factory.openSession();
            lista = session.createQuery(" from TipoParte", TipoParte.class).getResultList();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }

    @Override
    public TipoParte buscar(String valor) {
        TipoParte tipoParte = null;
        try{
            Session session = factory.openSession();
            tipoParte = session.createQuery("from TipoParte where color = '" + valor + "'", TipoParte.class).getSingleResult();
            session.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return tipoParte;
    }
}
