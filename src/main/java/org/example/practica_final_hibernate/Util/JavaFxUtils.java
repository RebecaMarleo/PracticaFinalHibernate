package org.example.practica_final_hibernate.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class JavaFxUtils {
    public static void abrirPantalla(Stage stage, String ruta, String titulo){
        try{
            FXMLLoader loader = new FXMLLoader(R.getResource(ruta));
            Scene scene = new Scene(loader.load());
            stage.setTitle(titulo);
            stage.setScene(scene);
        }catch (Exception e){
            mostrarAlert(Alert.AlertType.ERROR, "No se ha podido acceder a la ruta especificada", "Error de FXML");
        }
    }
    public static Optional<ButtonType> mostrarAlert(Alert.AlertType alertType, String mensaje, String titulo){
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.setHeaderText(null);
        return alert.showAndWait();
    }
}
