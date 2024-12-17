package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.stage.Stage;
import org.example.practica_final_hibernate.DAO.AlumnoDAO;
import org.example.practica_final_hibernate.DAO.ParteDAO;
import org.example.practica_final_hibernate.DAO.TipoParteDAO;
import org.example.practica_final_hibernate.Model.*;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.R;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditarParteController extends Controller{

    private Parte formerParte; //Parte a editar

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
    private TextField grupoTF;

    @FXML
    private ComboBox<String> horaCB;

    @FXML
    private TextField otrasancionTF;

    @FXML
    private ComboBox<Profesor> profesorCB;

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

    private ListarPartesController formerController;

    @FXML
    void onEditar(ActionEvent event) {
        if (expAlumnoTF.getText().isEmpty() || profesorCB.getValue() == null || fechaPicker.getValue() == null || grupoTF.getText().isEmpty() || horaCB.getValue() == null || descripcionTArea.getText().isEmpty()){
            JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "No se han rellenado todos los campos", "Erro de campos"); //No se ha rellenado lo necesario
        } else {
            //Se guardan los valores
            String expediente = expAlumnoTF.getText();
            Profesor profesor = profesorCB.getValue();
            LocalDate fecha = fechaPicker.getValue();
            String hora = horaCB.getValue();
            String descripcion = descripcionTArea.getText();
            String sancion = sancionTArea.getText();
            String sancionRoja = sancionCB.getValue();
            String otraSancion = otrasancionTF.getText();

            Alumno alumno = alumnoDAO.buscar(expediente);

            if (alumno == null) {
                JavaFxUtils.mostrarAlert(Alert.AlertType.ERROR, "Alumno no encontrado en la base de datos", "Error alumno");
            } else {
                Parte parte;
                if (Objects.equals(color.getColor(), "Verde") || Objects.equals(color.getColor(), "Naranja")) {
                    parte = new Parte(descripcion, sancion, fecha, hora, color, alumno, grupo, profesor); //Si el color es verde o naranja, coge los datos que ve
                } else if (Objects.equals(color.getColor(), "Rojo") && !usuarioNoEsJefeDeEstudios) { //Si es roja y el usuario es jefe de estudios:
                    if (sancionCB.getValue().equals("Otra sanción")) { //Si se ha puesto la opcion "otra sancion", se añade el campo de la sancion
                        parte = new Parte(descripcion, otraSancion, fecha, hora, color, alumno, grupo, profesor);
                    } else { //Si se ha elegido una opción el combobox
                        parte = new Parte(descripcion, sancionRoja, fecha, hora, color, alumno, grupo, profesor);
                    }
                } else { //Si el usuaro no es jefe de estudios y elige rojo, deja la sanción vacía
                    parte = new Parte(descripcion, "", fecha, hora, color, alumno, grupo, profesor);
                }
                parte.setId(formerParte.getId());
                parteDAO.modificar(parte);
                //Se resta al alumno el valor del parte anterior y se suma el nuevo (Si el parte era rojo y ahora es verde, se restan 12 y se suma 1)
                alumno.setPuntos(alumno.getPuntos() + color.getPuntuacion() - formerParte.getColor().getPuntuacion());
                alumnoDAO.modificar(alumno);
                formerController.refresh();
                JavaFxUtils.mostrarAlert(Alert.AlertType.INFORMATION, "Parte editado con éxito!", "Edición de partes");
                closeWindow();
            }
        }
    }

    private void closeWindow() {
        ((Stage) this.ventanaPartes.getScene().getWindow()).close(); //Se cierra esta ventana
    }

    @FXML
    void escribir(KeyEvent event) {
        String numExp = expAlumnoTF.getText();
        Alumno a = alumnoDAO.buscar(numExp);
        if (a!=null){
            grupo = a.getGrupo();
            grupoTF.setText(grupo.getNombre());
        } else {
            grupo = null;
            grupoTF.setText("");
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

    public void setItems(Parte parte, ListarPartesController listarPartesController){
        this.formerController = listarPartesController;
        this.formerParte = parte;
        parteDAO = new ParteDAO();
        alumnoDAO = new AlumnoDAO();
        tipoParteDAO = new TipoParteDAO();
        profesorCB.getItems().add(parte.getProfesor());
        if (parte.getProfesor().getId()!=R.profesorActual.getId()){
            profesorCB.getItems().add(R.profesorActual);
        }
        horaCB.getItems().addAll(R.horas);

        color = formerParte.getColor();
        switch (color.getColor()) {
            case "Verde" -> onParteVerde(null);
            case "Rojo" -> onParteRojo(null);
            case "Naranja" -> onParteNaranja(null);
        }

        expAlumnoTF.setText(String.valueOf(parte.getAlumno().getExpediente()));
        grupoTF.setText(parte.getGrupo().getNombre());
        grupo = parte.getGrupo();
        descripcionTArea.setText(parte.getDescripcion());
        sancionCB.getItems().addAll(R.tiposSancion);
        if (color.getColor().equals("Rojo")){
            if (R.tiposSancion.contains(parte.getSancion())){
                sancionCB.setValue(parte.getSancion());
            } else {
                sancionCB.setValue("Otra sanción");
                onValueChosen(null);
                otrasancionTF.setText(parte.getSancion());
            }
        } else {
            sancionTArea.setText(parte.getSancion());
        }
        horaCB.setValue(parte.getHora());
        fechaPicker.setValue(parte.getFecha());
        profesorCB.setValue(parte.getProfesor());

    }
}