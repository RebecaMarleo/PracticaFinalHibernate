package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.practica_final_hibernate.DAO.ProfesorDAO;
import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.R;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    ProfesorDAO profesorDAO;

    @FXML
    private Button logBtt;

    @FXML
    private TextField numTF;

    @FXML
    private PasswordField passTF;

    @FXML
    void logInClick(ActionEvent event) {
        if (numTF.getText().isEmpty() || passTF.getText().isEmpty()){
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Los campos no pueden esta vacíos", "Error de campos");
        } else {
            String pass = DigestUtils.sha256Hex(passTF.getText());
            String name = numTF.getText();
            Profesor profesor =profesorDAO.buscar(name);
            if (profesor==null){

            } else if (!profesor.getContrasena().equals(pass)){

            } else {
                R.profesorActual = profesor;
                Stage st = (Stage) this.logBtt.getScene().getWindow();
                JavaFxUtils.abrirPantalla(st, "CrearParte.fxml", "Crear");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profesorDAO = new ProfesorDAO();
    }
}
