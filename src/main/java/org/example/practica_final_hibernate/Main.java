package org.example.practica_final_hibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.practica_final_hibernate.Util.HibernateUtils;
import org.example.practica_final_hibernate.Util.R;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        while (HibernateUtils.getSessionFactory()==null){

        }
        FXMLLoader fxmlLoader = new FXMLLoader(R.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800.0, 640.0);
        stage.setTitle("Iniciar Sesión");
        stage.setScene(scene);
        stage.getIcons().add(new Image(R.getResource("images/LOGO_RIBERA_DE_CASTILLA/LOGO_COLOR_MEDIANO.png").toString()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}