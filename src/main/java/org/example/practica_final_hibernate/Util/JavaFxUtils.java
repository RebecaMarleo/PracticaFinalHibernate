package org.example.practica_final_hibernate.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class JavaFxUtils {
    /*Función que cambia entre escenas*/
    public static void abrirPantalla(Stage stage, String ruta, String titulo){
        try{
            FXMLLoader loader = new FXMLLoader(R.getResource(ruta)); //Hace un loader con la ruta que recibe por parámetro
            Scene scene = new Scene(loader.load()); //Carga el loader en una escena
            stage.setTitle(titulo); //Cambia el título
            stage.setScene(scene);  //Cambia la escena
        }catch (Exception e){ //Por si hay un fallo cargando el XML:
            mostrarAlert(Alert.AlertType.ERROR, "No se ha podido acceder a la ruta especificada", "Error de FXML");
        }
    }
    /*Función que abre una Alerta en pantalla*/
    public static void mostrarAlert(Alert.AlertType alertType, String mensaje, String titulo){
        Alert alert = new Alert(alertType); //Ponemos el tipo de alerta
        alert.setTitle(titulo); //Se pone el titulo
        alert.setContentText(mensaje); //Se pone el mensaje
        alert.setHeaderText(null); //Alert sin cabecera
        alert.showAndWait();
    }
}
