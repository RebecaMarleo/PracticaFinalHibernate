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
        filtroFechaHora = (filterDPicker.getValue()!=null || horaCBox.getValue()!=null);
        prepareTable();
    }

    @FXML
    void onBuscarPorNumExp(ActionEvent event) {
        filtroExpediente = !expedienteTField.getText().isEmpty();
        prepareTable();
    }

    @FXML
    void onContentClick(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DAO
        parteDAO = new ParteDAO();

        //ComboBox
        horaCBox.setItems(FXCollections.observableList(R.horas));

        //TableView
        nombreTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getAlumno().getNombre()));
        sancionTCol.setCellValueFactory(new PropertyValueFactory<>("sancion"));
        descTCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        expTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getAlumno().getExpediente()));
        profeTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getProfesor().getNombre()));
        grupoTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getGrupo().getNombre()));
        fechaTCol.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getFecha().toString()));
        partesTView.setRowFactory(trow -> new TableRow<>(){
            @Override
            protected void updateItem(Parte parte, boolean b) {
                super.updateItem(parte, b);
                if (b||parte==null){
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: #" + parte.getColor().getHex() + ";");
                }
            }
        });
        listaPartes = parteDAO.listar();

        prepareTable();


    }

    private void prepareTable() {
        listaFiltrada = new ArrayList<>(listaPartes);
        Iterator<Parte> partesIterator = listaFiltrada.iterator();
        while (partesIterator.hasNext()){
            Parte parte = partesIterator.next();
            if (filtroExpediente && Integer.parseInt(expedienteTField.getText())!=parte.getAlumno().getExpediente()){
                partesIterator.remove();
            } else {
                if (filtroFechaHora) {
                    if (filterDPicker.getValue()!=null && parte.getFecha()!=filterDPicker.getValue()){
                        partesIterator.remove();
                    } else if(horaCBox.getValue()!=null && !horaCBox.getValue().equals(parte.getHora())){
                        partesIterator.remove();
                    }
                }
            }
        }
        partesPagination.setPageCount(listaFiltrada.size()/3);
        partesTView.setItems(FXCollections.observableList(listaFiltrada));
    }
}

