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
        /*
        REQUISITOS PARA EL LOGIN:
            1. Que los campos estén rellenos
            2. Que el número de referencia aparezca en la base de datos
            3. Que la contraseña coincida con la del profesor
         */
        if (numTF.getText().isEmpty() || passTF.getText().isEmpty()){ //Si alguno de los campos está vacío
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Los campos no pueden esta vacíos", "Error de campos");
        } else {
            String pass = DigestUtils.sha256Hex(passTF.getText());
            String numRef = numTF.getText();
            Profesor profesor =profesorDAO.buscar(numRef);
            if (profesor==null){
                JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "No se ha encontrado en la base de datos al profesor", "Error de búsqueda");
            } else if (!profesor.getContrasena().equals(pass)){
                JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Contraseña incorrecta", "Error de inicio de sesión");
            } else {
                R.profesorActual = profesor; //Guardo el usuario para su uso en otras clases
                Stage st = (Stage) this.logBtt.getScene().getWindow();
                JavaFxUtils.abrirPantalla(st, "CrearParte.fxml", "Crear Parte");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profesorDAO = new ProfesorDAO();
    }
}
