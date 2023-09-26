package co.edu.uniquindio.app;

import co.edu.uniquindio.model.Metodo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Ingreso extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Ingreso.class.getResource("/pngPrincipal.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Ingreso ALQUILER");
        stage.show();
    }

    public static void main(String[] args) {
        Metodo.cargarDatos();
        launch( Ingreso.class, args );
    }
}
