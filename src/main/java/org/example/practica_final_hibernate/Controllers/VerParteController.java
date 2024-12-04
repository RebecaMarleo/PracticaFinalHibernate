package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Util.R;

import java.time.format.TextStyle;
import java.util.Locale;

public class VerParteController extends Controller{

    private Parte parte;

    @FXML
    private Label alumLb;

    @FXML
    private Label descripcionLb;

    @FXML
    private Label fechaLb;

    @FXML
    private Label fechaJustificanteLb;

    @FXML
    private Label grupoLb;

    @FXML
    private Label horaLb;

    @FXML
    private Label profeLb;

    @FXML
    private Label profesorLb;

    @FXML
    private Label sancionLbl;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label sancionLb;

    @FXML
    private Label tipoFaltaLb;

    @FXML
    private Label tipoParteLb;

    @FXML
    private Button editBtt;

    @FXML
    private Button eraseBtt;


    void iniciar(Parte parte){
        this.parte = parte;
        alumLb.setText(parte.getAlumno().getNombre());
        descripcionLb.setText(parte.getDescripcion());
        descripcionLb.setStyle("-fx-background-color: " + parte.getColor().getHex() + ";");
        fechaLb.setText(parte.getFecha().toString());
        grupoLb.setText(parte.getGrupo().getNombre());
        horaLb.setText(parte.getHora());
        profeLb.setText(parte.getProfesor().getNombre());
        sancionLb.setText(parte.getSancion());
        sancionLb.setStyle("-fx-background-color: " + parte.getColor().getHex() + ";");

        pane.setStyle("-fx-background-color: " + parte.getColor().getHex() + ";");

        String tipoParte = switch (parte.getColor().getColor()){
            case "Verde" -> "Parte VERDE de advertencia";
            case "Naranja" -> "Parte NARANJA de Nota Negativa";
            case "Rojo" -> "Parte ROJO de Nota Negativa";
            default -> "";
        };

        tipoParteLb.setText(tipoParte);

        tipoFaltaLb.setText((parte.getColor().getColor().equals("Verde"))?"Faltas leves de disciplina contrarias a las normas de convivencia":"");

        sancionLbl.setText((parte.getProfesor().getTipo().equals("Jefe de Estudios"))? "La sanción que recibes por parte de Jefatura de Estudios:":"La sanción que recibes (A determinar por el profesor):");

        fechaJustificanteLb.setText("En Valladolid a " + parte.getFecha().getDayOfMonth() + " de " + parte.getFecha().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " de " + parte.getFecha().getYear());

    }


    @FXML
    void onEdit(ActionEvent event) {

    }

    @FXML
    void onErase(ActionEvent event) {

    }

}
