package org.example.practica_final_hibernate.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.practica_final_hibernate.Controllers.Controller;

import java.util.Optional;

public class JavaFxUtils {
    /*Función que cambia entre escenas*/
    public static Controller abrirPantallaEnStage(Stage stage, String ruta, String titulo){
        try{
            FXMLLoader loader = new FXMLLoader(R.getResource(ruta)); //Hace un loader con la ruta que recibe por parámetro
            Scene scene = new Scene(loader.load()); //Carga el loader en una escena
            stage.setTitle(titulo); //Cambia el título
            stage.setScene(scene);  //Cambia la escena
            return loader.getController();
        }catch (Exception e){ //Por si hay un fallo cargando el XML:
            mostrarAlert(Alert.AlertType.ERROR, "No se ha podido acceder a la ruta especificada", "Error de FXML");
        }
        return null;
    }
    /*Función que abre una Alerta en pantalla*/
    public static Optional<ButtonType> mostrarAlert(Alert.AlertType alertType, String mensaje, String titulo){
        Alert alert = new Alert(alertType); //Ponemos el tipo de alerta
        alert.setTitle(titulo); //Se pone el titulo
        alert.setContentText(mensaje); //Se pone el mensaje
        alert.setHeaderText(null); //Alert sin cabecera
        return alert.showAndWait(); //Devuelve el botón que pulse el usuario (Por si acaso necesitamos hacer confirmaciones)
    }

    public static Controller abrirPantallaEnNuevoStage(String ruta, String titulo){
        try{
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(R.getResource(ruta)); //Hace un loader con la ruta que recibe por parámetro
            Scene scene = new Scene(loader.load()); //Carga el loader en una escena
            stage.setTitle(titulo); //Cambia el título
            stage.setScene(scene);  //Cambia la escena
            stage.initModality(Modality.APPLICATION_MODAL); //Hago que no se pueda acceder a la pantalla de debajo
            stage.show();
            return loader.getController();
        }catch (Exception e){ //Por si hay un fallo cargando el XML:
            mostrarAlert(Alert.AlertType.ERROR, "No se ha podido acceder a la ruta especificada", "Error de FXML");
        }
        return null;
    }
}
