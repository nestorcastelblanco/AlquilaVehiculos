package co.edu.uniquindio.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.model.Metodo;
public class IngresoAdminController {
    @FXML
    private Button botonRegistroVehiculo, botonRegreso;
    public void registrarVehiculo (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegistroVehiculo)) {
            Metodo.loadStage("/paginaRegistroVehiculo.fxml", e);
        }
    }
    public void regresar (ActionEvent e)
    {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/pngPrincipal.fxml", e);
        }
    }
}
