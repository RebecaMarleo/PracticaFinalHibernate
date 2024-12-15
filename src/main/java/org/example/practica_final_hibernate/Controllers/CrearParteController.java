package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import org.example.practica_final_hibernate.DAO.AlumnoDAO;
import org.example.practica_final_hibernate.DAO.ParteDAO;
import org.example.practica_final_hibernate.DAO.TipoParteDAO;
import org.example.practica_final_hibernate.Model.*;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.R;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearParteController extends Controller implements Initializable {

    @FXML
    private Button NaranjaBtt;

    @FXML
    private Button RojoBtt;

    @FXML
    private Button VerdeBtt;

    @FXML
    private Button crearBtt;

    @FXML
    private TextField profesorTField;

    @FXML
    private Label alumnoLbl;

    @FXML
    private TextArea descripcionTArea;

    @FXML
    private TextField expAlumnoTF;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private TextField grupoTF;

    @FXML
    private ComboBox<String> horaCB;

    @FXML
    private TextField otrasancionTF;

    @FXML
    private ComboBox<String> sancionCB;

    @FXML
    private TextArea sancionTArea;

    @FXML
    private Label tipoParteLB;

    @FXML
    private Label sancionLb;

    @FXML
    private AnchorPane ventanaPartes;

    boolean usuarioNoEsJefeDeEstudios = !R.profesorActual.getTipo().equals("Jefe de Estudios");

    TipoParteDAO tipoParteDAO;

    ParteDAO parteDAO;

    AlumnoDAO alumnoDAO;

    Grupo grupo;

    TipoParte color;

    @FXML
    void onCrear(ActionEvent event) {
        if (expAlumnoTF.getText().isEmpty() || fechaPicker.getValue() == null || grupoTF.getText().isEmpty() || horaCB.getValue() == null || descripcionTArea.getText().isEmpty()){
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "No se han rellenado todos los campos", "Erro de campos");
        } else {
            String expediente = expAlumnoTF.getText();
            Profesor profesor = R.profesorActual;
            LocalDate fecha = fechaPicker.getValue();
            String hora = horaCB.getValue();
            String descripcion = descripcionTArea.getText();
            String sancion = sancionTArea.getText();
            String sancionRoja = sancionCB.getValue();
            String otraSancion = otrasancionTF.getText();

            Alumno alumno = alumnoDAO.buscar(expediente);

            if (alumno == null){
                JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Alumno no encontrado en la base de datos", "Error alumno");
            } else if (Objects.equals(color.getColor(), "Verde") || Objects.equals(color.getColor(), "Naranja") ){
                Parte parte = new Parte(descripcion, sancion, fecha, hora, color, alumno, grupo, profesor);
                parteDAO.insertar(parte);

                alumno.setPuntos(alumno.getPuntos() + color.getPuntuacion());

                JavaFxUtils.mostrarAlert(Alert.AlertType.INFORMATION, "Parte añadido con éxito!", "");
            } else if (Objects.equals(color.getColor(), "Rojo") && !usuarioNoEsJefeDeEstudios){
                if (sancionCB.getValue().equals("Otra sanción")){
                    Parte parteOtra = new Parte(descripcion, otraSancion, fecha, hora, color, alumno, grupo, profesor);
                    parteDAO.insertar(parteOtra);
                } else {
                    Parte parte = new Parte(descripcion, sancionRoja, fecha, hora, color, alumno, grupo, profesor);
                    parteDAO.insertar(parte);
                }

                alumno.setPuntos(alumno.getPuntos() + color.getPuntuacion());

                JavaFxUtils.mostrarAlert(Alert.AlertType.INFORMATION, "Parte añadido con éxito!", "");
            } else {
                Parte parte = new Parte(descripcion, "", fecha, hora, color, alumno, grupo, profesor);
                parteDAO.insertar(parte);

                alumno.setPuntos(alumno.getPuntos() + color.getPuntuacion());
                JavaFxUtils.mostrarAlert(Alert.AlertType.INFORMATION, "Parte añadido con éxito!", "");
            }

            alumnoDAO.modificar(alumno);
        }
    }

    @FXML
    void escribir(KeyEvent event) {
        String numExp = expAlumnoTF.getText();
        Alumno a = alumnoDAO.buscar(numExp);
        if (a!=null){
            grupo = a.getGrupo();
            grupoTF.setText(grupo.getNombre());
            alumnoLbl.setText("(" + a.getNombre() + ")");
        } else {
            grupo = null;
            grupoTF.setText("");
            alumnoLbl.setText("");
        }
    }

    @FXML
    void onParteVerde(ActionEvent event) {
        ventanaPartes.setBackground(new Background(new BackgroundFill(Color.rgb(190, 252, 119), null, null)));
        tipoParteLB.setText("PARTE VERDE DE ADVERTENCIA");
        color = tipoParteDAO.buscar("Verde");

        sancionCB.setVisible(false);
        sancionCB.setDisable(true);

        sancionTArea.setVisible(true);
        sancionTArea.setDisable(false);

        otrasancionTF.setDisable(true);
        otrasancionTF.setVisible(false);
    }

    @FXML
    void onParteNaranja(ActionEvent event) {
        ventanaPartes.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        tipoParteLB.setText("PARTE NARANJA DE NOTA NEGATIVA");
        color = tipoParteDAO.buscar("Naranja");

        sancionCB.setVisible(false);
        sancionCB.setDisable(true);

        sancionTArea.setVisible(true);
        sancionTArea.setDisable(false);

        otrasancionTF.setDisable(true);
        otrasancionTF.setVisible(false);
    }

    @FXML
    void onParteRojo(ActionEvent event) {
        ventanaPartes.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        tipoParteLB.setText("PARTE ROJO DE NOTA NEGATIVA");
        color = tipoParteDAO.buscar("Rojo");

        sancionTArea.setVisible(usuarioNoEsJefeDeEstudios);
        sancionTArea.setDisable(!usuarioNoEsJefeDeEstudios);

        sancionCB.setVisible(!usuarioNoEsJefeDeEstudios);
        sancionCB.setDisable(usuarioNoEsJefeDeEstudios);

        otrasancionTF.setVisible(!usuarioNoEsJefeDeEstudios);
        otrasancionTF.setDisable(usuarioNoEsJefeDeEstudios);

        if (sancionCB.getValue() != null){
            if (sancionCB.getValue().equals("Otra sanción")){
                otrasancionTF.setVisible(true);
                otrasancionTF.setDisable(false);
            } else {
                otrasancionTF.setDisable(true);
                otrasancionTF.setVisible(false);
            }
        }

        if (usuarioNoEsJefeDeEstudios){
            sancionTArea.setVisible(false);
            sancionTArea.setDisable(true);
            sancionLb.setVisible(false);
        }
    }

    @FXML
    void onValueChosen(ActionEvent event) {
        if (sancionCB.getValue() != null){
            if (sancionCB.getValue().equals("Otra sanción")){
                otrasancionTF.setVisible(true);
                otrasancionTF.setDisable(false);
            } else {
                otrasancionTF.setDisable(true);
                otrasancionTF.setVisible(false);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parteDAO = new ParteDAO();
        alumnoDAO = new AlumnoDAO();
        tipoParteDAO = new TipoParteDAO();

        color = tipoParteDAO.buscar("Verde");

        profesorTField.setText(R.profesorActual.toString());
        horaCB.getItems().addAll(R.horas);
        sancionCB.getItems().addAll(R.tiposSancion);
    }
}
