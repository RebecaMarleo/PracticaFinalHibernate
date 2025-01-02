package org.example.practica_final_hibernate.Controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import org.example.practica_final_hibernate.DAO.AlumnoDAO;
import org.example.practica_final_hibernate.DAO.TipoParteDAO;
import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.TipoParte;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ListarAlumnosController extends Controller implements Initializable {

    private TipoParteDAO tipoDAO;
    private TipoParte parteVerde;
    private TipoParte parteNaranja;
    private TipoParte parteRojo;

    private AlumnoDAO alumnoDAO;
    @FXML
    private Pagination alumnosPagination;

    @FXML
    private TableView<Alumno> alumnosTView;

    @FXML
    private Button buscarBtt;

    @FXML
    private TableColumn<Alumno, Integer> expTCol;

    @FXML
    private TextField expedienteTField;

    @FXML
    private TableColumn<Alumno, String> grupoTCol;

    @FXML
    private TableColumn<Alumno, String> nombreTCol;

    @FXML
    private TableColumn<Alumno, Integer> puntosTCol;

    List<Alumno> listaAlumnos;

    ArrayList<Alumno> listaAlumnosFiltrada;

    boolean filtrada = false;

    @FXML
    void onBuscarPorNumExp() {
        filtrada = !expedienteTField.getText().isEmpty(); //True si el campo tiene texto
        prepareTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Lista de alumnos
        alumnoDAO = new AlumnoDAO();
        listaAlumnos = alumnoDAO.listar();

        //Saco los tipos de parte
        tipoDAO = new TipoParteDAO();
        parteNaranja = tipoDAO.buscar("Naranja");
        parteRojo = tipoDAO.buscar("Rojo");
        parteVerde = tipoDAO.buscar("Verde");

        //TableView
        expTCol.setCellValueFactory(new PropertyValueFactory<>("expediente"));
        grupoTCol.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getGrupo().getNombre()));
        nombreTCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        puntosTCol.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        alumnosTView.setRowFactory(trow -> new TableRow<>(){
                    @Override
                    protected void updateItem(Alumno alumno, boolean b) {
                        super.updateItem(alumno, b);
                        if (alumno!=null){
                            String s;
                            if (alumno.getPuntos()<parteNaranja.getPuntuacion()){
                                s = parteVerde.getHex();
                            } else if (alumno.getPuntos()>=parteRojo.getPuntuacion()){
                                s = parteRojo.getHex();
                            } else {
                                s = parteNaranja.getHex();
                            }
                            setStyle("-fx-background-color: #" + s + ";");
                        } else {
                            setStyle("-fx-background-color: white;");
                        }
                        /*
                        Esto está hecho teniendo en cuenta futuros cambios de la puntuación de los partes:
                            - Si tiene menos puntos que los que vale un parte naranja, aparece en verde
                            - Si tiene tantos puntos o más que los que vale un parte rojo, aparece en rojo
                            - Si tiene tantos o más puntos que un parte naranja, pero menos que un rojo, aparece en rojo
                         */
                    }
                }
        );
        prepareTable(); //Prepara la tabla

        //Da un listener a la paginación, el cual se activa al cambiar de página
        alumnosPagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            actualizarTabla();
        });

    }

    void prepareTable(){ //Preparación de tabla
        listaAlumnosFiltrada = new ArrayList<>(listaAlumnos); //ArrayList (mutable) que toma de primeras la lista principal
        Iterator<Alumno> iteradorFiltro = listaAlumnosFiltrada.iterator(); //Iterador del ArrayList
        while (iteradorFiltro.hasNext()){
            Alumno a = iteradorFiltro.next();
            if (filtrada && !String.valueOf(a.getExpediente()).equals(expedienteTField.getText())){
                //Si el filtro está activado y los expedientes no coinciden, se borra
                iteradorFiltro.remove();
            }
        }
        //Se divide el tamaño de la lista entre 5 y se redondea hacia arriba
        alumnosPagination.setPageCount((int) Math.ceil((double) listaAlumnosFiltrada.size()/5));
        actualizarTabla(); //Actualizar tabla
    }

    private void actualizarTabla() {
        int indice = alumnosPagination.getCurrentPageIndex(); //Indice actual
        int tope = ((indice*5)+5>=listaAlumnosFiltrada.size())? (listaAlumnosFiltrada.size()):(indice*5)+5;
        /*
        Como daba error al meter items por problemas con los indices de la sublista hice lo siguiente:
        - Si el indice*5+5 es mayor o igual al tamaño de la lista, se usa el tamaño de la lista
        - Si no, se usa indice*5+5
         */
        alumnosTView.setItems(FXCollections.observableArrayList(listaAlumnosFiltrada.subList((indice*5), (tope))));
        //Pone en la tabla una cantidad de items desde n hasta n+4 (0-4, 5-9, 10-14...)
    }
}
