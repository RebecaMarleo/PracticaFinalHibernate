package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.practica_final_hibernate.DAO.ParteDAO;
import org.example.practica_final_hibernate.Model.Grupo;
import org.example.practica_final_hibernate.Model.Profesor;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearParteController implements Initializable {

    @FXML
    private Button NaranjaBtt;

    @FXML
    private Button RojoBtt;

    @FXML
    private Button VerdeBtt;

    @FXML
    private Button crearBtt;

    @FXML
    private TextArea descripcionTArea;

    @FXML
    private TextField expAlumnoTF;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private ComboBox<Grupo> nombreGrupoCB;

    @FXML
    private ComboBox<Profesor> profesorCB;

    @FXML
    private Label tipoParteLB;

    private ParteDAO parteDAO = new ParteDAO();

    @FXML
    void onCrear(ActionEvent event) {
        String expendiente = expAlumnoTF.getText();

    }

    @FXML
    void onParteNaranja(ActionEvent event) {

    }

    @FXML
    void onParteRojo(ActionEvent event) {

    }

    @FXML
    void onParteVerde(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

