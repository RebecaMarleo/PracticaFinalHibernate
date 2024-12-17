package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.practica_final_hibernate.Util.JavaFxUtils;
import org.example.practica_final_hibernate.Util.R;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {

    @FXML
    private Button CrearParteBtt;

    @FXML
    private Button crearProfesorBtt;

    @FXML
    private Button listaAlumnosBtt;

    @FXML
    private Button listaPartesBtt;

    @FXML
    private Button logoutBtt;

    @FXML
    void onCrearParte(ActionEvent event) {
        JavaFxUtils.abrirPantallaEnStage(((Stage) this.CrearParteBtt.getScene().getWindow()), "CrearParte.fxml", "Crear Parte");
    }

    @FXML
    void onCrearProfesor(ActionEvent event) {
        JavaFxUtils.abrirPantallaEnStage(((Stage) this.crearProfesorBtt.getScene().getWindow()), "CrearProfesor.fxml", "Crear Profesor");
    }

    @FXML
    void onListaAlumnos(ActionEvent event) {
        JavaFxUtils.abrirPantallaEnStage(((Stage) this.listaAlumnosBtt.getScene().getWindow()), "ListaAlumnos.fxml", "Listado de Alumnos");
    }

    @FXML
    void onListaPartes(ActionEvent event) {
        JavaFxUtils.abrirPantallaEnStage(((Stage) this.listaPartesBtt.getScene().getWindow()), "ListaPartes.fxml", "Listado de Partes");
    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        R.profesorActual = null;
        JavaFxUtils.abrirPantallaEnStage(((Stage) this.listaPartesBtt.getScene().getWindow()), "LogIn.fxml", "Iniciar sesión");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Hago una booleana encargada de comprobar si el usuaro que ha iniciado sesión no es jefe de estudios
        boolean usuarioNoEsJefeDeEstudios = !R.profesorActual.getTipo().equals("Jefe de Estudios");
        //Si no lo es, se desactivan los botones que no pueden usar los que no son jefe de estudios
        //NOTA: Los Disable son true para desactivarlos, y los Visible son false para hacerlos invisibles
        crearProfesorBtt.setDisable(usuarioNoEsJefeDeEstudios);
        crearProfesorBtt.setVisible(!usuarioNoEsJefeDeEstudios);
        listaAlumnosBtt.setDisable(usuarioNoEsJefeDeEstudios);
        listaAlumnosBtt.setVisible(!usuarioNoEsJefeDeEstudios);
        listaPartesBtt.setDisable(usuarioNoEsJefeDeEstudios);
        listaPartesBtt.setVisible(!usuarioNoEsJefeDeEstudios);
    }
}

