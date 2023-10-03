package co.edu.uniquindio.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import co.edu.uniquindio.model.Metodo;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class IngresoAdminController {
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button botonRegistroVehiculo, botonRegreso, botonRegistro,botonAdministrativo,botonContador;
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
    public void administrativa(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonAdministrativo)) {
            Metodo.loadStage("/paginaPrestamos.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("ingresoPagPrestamos.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Ingreso al apartado prestamos");
        }
    }

    public void contador(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonContador)) {
            Metodo.loadStage("/paginaEstadisticas.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("ingresoPagEstadisticas.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Ingreso al apartado prestamos");
        }
    }
}
