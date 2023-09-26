package co.edu.uniquindio.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistroVehiculo extends Application {
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Ingreso.class.getResource("/paginaRegistroVehiculo.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Ingreso Registro Vehiculo");
        stage.show();
    }
}
