package org.example.practica_final_hibernate.DAO;

import java.util.List;

public interface DAO<T>{
    public void insertar(T objeto);
    public void modificar(T objeto);
    public void eliminar(T objeto);
    public List<T> listar();
    public T buscar(String valor);
}
