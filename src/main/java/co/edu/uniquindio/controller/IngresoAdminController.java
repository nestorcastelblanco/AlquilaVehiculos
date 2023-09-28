package co.edu.uniquindio.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.model.Metodo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresoAdminController {
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button botonRegistroVehiculo, botonRegreso, botonRegistro;
    public void registrarVehiculo (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegistroVehiculo)) {
            Metodo.loadStage("/paginaRegistroVehiculo.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("ingresoRegistroVehiculo.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Se ingreso al registro vehicular");
        }
    }
    public void regresar (ActionEvent e)
    {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/pngPrincipal.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("regresarAdmin.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Se regreso a la pagina principal");
        }
    }
    public void registrarse (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegistro)) {
            Metodo.loadStage("/paginaRegistro.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("ingresoRegistroCliente.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Ingreso al apartado de registro de personas");
        }
    }
}
