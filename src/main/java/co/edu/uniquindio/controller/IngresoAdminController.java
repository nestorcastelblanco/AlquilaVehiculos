package co.edu.uniquindio.controller;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Vehiculos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import co.edu.uniquindio.model.Metodo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class IngresoAdminController{
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    private final Metodo metodo = Metodo.getInstance();
    @FXML
    private Button botonUtilidades,botonRegistroVehiculo, botonRegreso, botonRegistro,botonAdministrativo,botonContador;
    public void registrarVehiculo (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegistroVehiculo)) {
            metodo.loadStage("/paginaRegistroVehiculo.fxml", e, "Se ingresa a la pestaña de registro");
        }
    }

    public void regresar (ActionEvent e)
    {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            metodo.loadStage("/pngPrincipal.fxml", e, "Se regresa a la pagina principal");
        }
    }
    public void registrarse (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegistro)) {
            metodo.loadStage("/paginaRegistro.fxml", e,"Ingreso al apartado de registro de personas");
        }
    }
    public void administrativa(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonAdministrativo)) {
            metodo.loadStage("/paginaPrestamos.fxml", e,"Ingreso al apartado administrativo");
        }
    }
    public void contador(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonContador)) {
            metodo.loadStage("/paginaEstadisticas.fxml", e,"Ingreso al apartado prestamos");
        }
    }
    public void utilidades(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonUtilidades)) {
            metodo.loadStage("/paginaFacturas.fxml", e,"Ingreso al apartado de utilidades");
        }
    }
}
