package org.example.practica_final_hibernate.Controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.practica_final_hibernate.DAO.ParteDAO;
import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ListarPartesController implements Initializable {

    @FXML
    private Button buscarBtt;

    @FXML
    private TableColumn<Parte, String> descTCol;

    @FXML
    private TableColumn<Parte, Integer> expTCol;

    @FXML
    private TextField expedienteTField;

    @FXML
    private TableColumn<Parte, String> fechaTCol;

    @FXML
    private DatePicker filterDPicker;

    @FXML
    private TableColumn<Parte, String> grupoTCol;

    @FXML
    private ChoiceBox<String> horaCBox;

    @FXML
    private TableColumn<Parte, String> nombreTCol;

    @FXML
    private Pagination partesPagination;

    @FXML
    private TableView<Parte> partesTView;

    @FXML
    private TableColumn<Parte, String> profeTCol;

    @FXML
    private TableColumn<Parte, String> sancionTCol;

    private List<Parte> listaPartes;

    private ArrayList<Parte> listaFiltrada;

    private ParteDAO parteDAO;

    private boolean filtroExpediente = false;

    private boolean filtroFechaHora = false;

    @FXML
    void onBuscarPorFecha(ActionEvent event) {
        filtroFechaHora = (filterDPicker.getValue()!=null || horaCBox.getValue()!=null); //True si alguno de los campos tiene valor
        prepareTable();
    }

    @FXML
    void onBuscarPorNumExp(ActionEvent event) {
        filtroExpediente = !expedienteTField.getText().isEmpty(); //True si tiene texto
        prepareTable();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DAO
        parteDAO = new ParteDAO();

        //ComboBox
        horaCBox.setItems(FXCollections.observableList(R.horas)); //Meto la lista estática del horario del centro

        //TableView
        nombreTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getAlumno().getNombre()));
        sancionTCol.setCellValueFactory(new PropertyValueFactory<>("sancion"));
        descTCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        expTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getAlumno().getExpediente()));
        profeTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getProfesor().getNombre()));
        grupoTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getGrupo().getNombre()));
        fechaTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getFecha().toString()));
        partesTView.setRowFactory(trow -> new TableRow<>(){ //Cambio variables de las filas
            @Override
            protected void updateItem(Parte parte, boolean b) {
                super.updateItem(parte, b);
                if (b||parte==null){
                    setStyle("-fx-background-color: white;"); //Pone blanco en espacios vacíos
                } else {
                    setStyle("-fx-background-color: #" + parte.getColor().getHex() + ";"); //Pone el color del tipo de parte que sea
                }
            }
        });
        listaPartes = parteDAO.listar(); //Saca la lista de partes

        prepareTable(); //Se prepara la tabla

        //Añado un Listener a las páginas del Pagination, el cual se activa al dar a pasar página
        partesPagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> {
            actualizarTabla(); //Pasa página
        });
    }

    private void prepareTable() {
        listaFiltrada = new ArrayList<>(listaPartes); //Copio la lista en un arraylist (Es mutable para poder quitar elementos)
        Iterator<Parte> partesIterator = listaFiltrada.iterator(); //Iterador de la lista filtrada
        while (partesIterator.hasNext()){
            Parte parte = partesIterator.next();
            if (filtroExpediente && !expedienteTField.getText().equals(String.valueOf(parte.getAlumno().getExpediente()))){
                //Si el filtro de expediente esta activado y el texto no coincide con el expediente del iterador, lo borra
                partesIterator.remove();
            } else {
                if (filtroFechaHora) { //Si el filtro de la hora está activado:
                    if (filterDPicker.getValue()!=null && !parte.getFecha().equals(filterDPicker.getValue())){
                        //Si el campo de fecha tiene valor y este no coincide con la fecha de la ocurrencia, la borra
                        partesIterator.remove();
                    } else if(horaCBox.getValue()!=null && !horaCBox.getValue().equals(parte.getHora())){
                        //Si el campo de la hora tiene valor y este no coincide con la hora de la ocurrencia, la borra
                        partesIterator.remove();
                    }
                }
            }
        }
        partesPagination.setPageCount((int) Math.ceil((double) listaFiltrada.size()/5));
        //Se pone de tabulaciones el total de la lista entre 5, aproximando hacia arriba (ceil)
        actualizarTabla(); //Se actualiza la tabla
    }

    private void actualizarTabla() { //Funcion encargada de actualizar la tabla tras cambiar de página o filtrar
        try {
            int indice = partesPagination.getCurrentPageIndex(); //Coge el índice de la lista
            int tope = ((indice * 5) + 5 >= listaFiltrada.size()) ? (listaFiltrada.size()) : (indice * 5) + 5;
        /* Calculo un tope:
        Viendo que, al hacer la sublista para que solo me saque x números, saba error al poner indice*5+5 por tener más longitud que la lista,
        hice lo siguiente:
            - Si el indice * 5 + 5 no supera el tamaño de la lista, se usa para la sublista
            - Si no, se usa el tamaño de la lista.
         */
            partesTView.setItems(FXCollections.observableArrayList(listaFiltrada.subList((indice * 5), tope))); //Muestra una sublista desde un punto de la tabla hasta 4 posiciones más adelante (0-4, 5-9...)
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void seleccionarParte(){
        Parte parte = partesTView.getSelectionModel().getSelectedItem();
        if (parte!=null){
            VerParteController controller = (VerParteController) JavaFxUtils.abrirPantallaEnNuevoStage("VerParte.fxml", "Parte de " + parte.getAlumno().getNombre());
            if (controller!=null) {
                controller.iniciar(parte);
            }
        }
    }
}

