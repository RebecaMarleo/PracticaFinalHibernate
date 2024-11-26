package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.practica_final_hibernate.Model.Grupo;
import org.example.practica_final_hibernate.Model.Profesor;

public class CrearParteController {

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
    void onCrear(ActionEvent event) {

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

}

