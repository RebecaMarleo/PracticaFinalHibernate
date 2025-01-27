package org.example.practica_final_hibernate.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.practica_final_hibernate.DAO.AlumnoDAO;
import org.example.practica_final_hibernate.DAO.ParteDAO;
import org.example.practica_final_hibernate.Model.Alumno;
import org.example.practica_final_hibernate.Model.Parte;
import org.example.practica_final_hibernate.Util.JavaFxUtils;

import java.util.Optional;

public class VerParteController extends Controller{

    private ListarPartesController formerController;
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


    void iniciar(Parte parte, ListarPartesController controller){ //Como siempre va a recibir un parámetro, esta función puede servir como un inicializador
        this.formerController = controller; //Guarda el controller de la pantalla anterior para su futuro uso
        this.parte = parte; //Guarda el parte

        //Rellena todos los campos
        alumLb.setText(parte.getAlumno().getNombre());
        descripcionLb.setText(parte.getDescripcion());
        descripcionLb.setStyle("-fx-background-color: " + parte.getColor().getHex() + ";");
        fechaLb.setText(parte.getFecha().toString());
        grupoLb.setText(parte.getGrupo().getNombre());
        horaLb.setText(parte.getHora());
        profeLb.setText(parte.getProfesor().getNombre());
        sancionLb.setText(parte.getSancion());
        sancionLb.setStyle("-fx-background-color: " + parte.getColor().getHex() + ";");

        //Cambia el color al del tipo del parte
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

        fechaJustificanteLb.setText("En Valladolid a " + parte.getFecha().getDayOfMonth() + " del " + parte.getFecha().getMonth().getValue() + " de " + parte.getFecha().getYear());

        profesorLb.setText(profesorLb.getText() + ": " + parte.getProfesor().getNombre());
    }


    @FXML
    void onEdit(ActionEvent event) { //Función que salta al dar al botón de editar
        EditarParteController controller = (EditarParteController) JavaFxUtils.abrirPantallaEnStage((Stage) this.editBtt.getScene().getWindow(), "EditarParte.fxml", "Editar Parte");
        if(controller!=null) { //Si se abre correctamente la pantalla:
            controller.setItems(this.parte, this.formerController); //Pasa el parte y el controlador de la pantalla anterior a la nueva pantalla
        }
    }

    @FXML
    void onErase(ActionEvent event) { //Función que salta al dar al botón de borrar
        Optional<ButtonType> opcion = JavaFxUtils.mostrarAlert(Alert.AlertType.CONFIRMATION, "¿Estás seguro de querer borrar el parte?\nLa acción no se podrá retirar", "Quitar un parte");
        if (opcion.isPresent()&&opcion.get()==ButtonType.OK){
            Alumno alumno = parte.getAlumno(); //Coge al alumno para restar su número de faltas menos el valor que tiene el parte
            alumno.setPuntos(alumno.getPuntos() - parte.getColor().getPuntuacion());
            new AlumnoDAO().modificar(alumno); //Se modifica el alumno
            new ParteDAO().eliminar(parte); //Se borra el parte
            JavaFxUtils.mostrarAlert(Alert.AlertType.INFORMATION, "Borrado con exito", "Quitar un parte");
            this.formerController.refresh(); //Se refresca la tabla de partes
            ((Stage) this.eraseBtt.getScene().getWindow()).close(); //Se cierra esta ventana
        }
    }
}
