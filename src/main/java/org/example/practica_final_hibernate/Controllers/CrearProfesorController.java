package org.example.practica_final_hibernate.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.practica_final_hibernate.DAO.DAO;
import org.example.practica_final_hibernate.DAO.ProfesorDAO;
import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Util.JavaFxUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearProfesorController implements Initializable {

    @FXML
    private TextField nombreTF;

    @FXML
    private Label nombreErrLbl;

    @FXML
    private TextField numTF;

    @FXML
    private Label numErrLbl;

    @FXML
    private PasswordField passTF;

    @FXML
    private Label passErrLbl;

    @FXML
    private ComboBox<String> tipoCB;

    @FXML
    private Label tipoErrLbl;

    @FXML
    private Button crearBtt;

    private final ProfesorDAO dao = new ProfesorDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreErrLbl.setVisible(false);
        numErrLbl.setVisible(false);
        passErrLbl.setVisible(false);
        tipoErrLbl.setVisible(false);

        String[] tipos = {"Profesor", "Jefe de estudios"};
        tipoCB.setItems(FXCollections.observableArrayList(tipos));
    }

    @FXML
    void crearProfesorClick(ActionEvent event) {
        reset();
        if (nombreTF.getText().isEmpty() || numTF.getText().isEmpty() || passTF.getText().isEmpty() || tipoCB.getSelectionModel().getSelectedItem() == null) {
            if (nombreTF.getText().isEmpty()) { //Si el nombre está vacío
                nombreErrLbl.setVisible(true);
            }
            if (numTF.getText().isEmpty()) { //Si el número está vacío
                numErrLbl.setVisible(true);
            }
            if (passTF.getText().isEmpty()) { //Si la contraseña está vacía
                passErrLbl.setVisible(true);
            }
            if (tipoCB.getSelectionModel().getSelectedItem() == null) { //Si el tipo está vacío
                tipoErrLbl.setVisible(true);
            }
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Los datos del profesor están incompletos", "Faltan datos");
        }
        else {
            Profesor profesorExistente = dao.buscar(numTF.getText());
            if (profesorExistente == null) {
                String nombre = nombreTF.getText();
                String num = numTF.getText();
                String pass = passTF.getText();
                //encriptar contraseña
                String tipo = tipoCB.getSelectionModel().getSelectedItem();
                Profesor profesor = new Profesor(nombre, tipo, num, pass);
                dao.insertar(profesor);
                limpiar();
            }
            else {
                JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "El número asignado ya corresponde a un profesor en la base de datos", "Profesor inválido");
            }
        }
    }

    private void reset() {
        nombreErrLbl.setVisible(false);
        numErrLbl.setVisible(false);
        passErrLbl.setVisible(false);
        tipoErrLbl.setVisible(false);
    }

    private void limpiar() {
        tipoCB.setValue("");
    }
}

/*
package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;

public class CrearProfesorController {
    public void crearProfesorClick(ActionEvent actionEvent) {
    }
}
 */