package org.example.practica_final_hibernate.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.validation.ValidateEvent;
import org.example.practica_final_hibernate.DAO.DAO;
import org.example.practica_final_hibernate.DAO.ProfesorDAO;
import org.example.practica_final_hibernate.Model.Profesor;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearProfesorController extends Controller implements Initializable {

    public Label nameSizeLbl;
    public Label passSizeLbl;
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
        reset(); //elimina posibles mensajes de error que hayan quedado activados anteriormente
        boolean valido = validarCampos();
        if (valido) {
            Profesor profesorExistente = dao.buscar(numTF.getText());
            if (profesorExistente == null) {
                String nombre = nombreTF.getText();
                String num = numTF.getText();
                String pass = passTF.getText();
                String passEncriptada = DigestUtils.sha256Hex(pass);
                String tipo = tipoCB.getSelectionModel().getSelectedItem();
                Profesor profesor = new Profesor(nombre, tipo, num, passEncriptada);
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

    private boolean validarCampos() {
        boolean valido = true;
        boolean vacio = false;

        if (nombreTF.getText().isEmpty()) { //Si el nombre está vacío
            nombreErrLbl.setVisible(true);
            valido = false;
            vacio = true;
        }
        if (numTF.getText().isEmpty()) { //Si el número está vacío
            numErrLbl.setVisible(true);
            valido = false;
            vacio = true;
        }
        if (passTF.getText().isEmpty()) { //Si la contraseña está vacía
            passErrLbl.setVisible(true);
            valido = false;
            vacio = true;
        }
        if (tipoCB.getSelectionModel().getSelectedItem() == null) { //Si el tipo está vacío
            tipoErrLbl.setVisible(true);
            valido = false;
            vacio = true;
        }
        if (vacio) {
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Los datos del profesor están incompletos", "Faltan datos");
        }

        if (!Validator.validar("nombre", nombreTF.getText())) { //Si el nombre es incorrecto
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "El nombre no debe contener números ni caracteres especiales, ni puede empezar por un espacio en blanco", "Nombre inválido");
            valido = false;
        }
        if (!Validator.validar("numero", numTF.getText())) { //Si el número es incorrecto
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "El número asignado debe ser un número entero", "Número asignado inválido");
            valido = false;
        }
        if (!Validator.validar("pass", passTF.getText())) { //Si la contraseña es incorrecta
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "La contraseña debe tener al menos 4 caracteres", "Contraseña inválida");
            valido = false;
        }
        return valido;
    }

    public void onNameTyped(KeyEvent keyEvent) {
        checkField(nombreTF, nameSizeLbl);
    }

    public void onPassTyped(KeyEvent keyEvent) {
        checkField(passTF, passSizeLbl);
    }

    public void checkField(TextField tf, Label lb){
        int size = tf.getText().length();
        if (size>255){
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "El campo especificado no puede tener mas de 255 caracteres", "Error de texto");
            tf.setText(tf.getText().substring(0, 255));
        }
        lb.setText(tf.getText().length()+ "/255");
    }
}