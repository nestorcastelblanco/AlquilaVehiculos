package co.edu.uniquindio.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IngresoAdmin extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Ingreso.class.getResource("/paginaPrincipalAdmin.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("INGRESO ADMINISTRADOR");
        stage.show();
    }
    public static void main(String[] args) {    

        launch( IngresoAdmin.class, args );
    }
}
