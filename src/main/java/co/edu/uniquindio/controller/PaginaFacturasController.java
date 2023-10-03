package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaginaFacturasController {
        private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        @FXML
        private Label precio;
        @FXML
        private Button botonRegreso, botonBuscar;
        @FXML
        private TextField diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
        private LocalDate inicio, fin;
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                Metodo.loadStage("/paginaPrincipalAdmin.fxml", e);
                try {
                    LOGGER.addHandler((new FileHandler("BotonRegresoVenSeleccion.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se regreso al portal de ingreso");
            }
        }
        public void buscar(ActionEvent actionEvent) {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                if (Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText()) != null && Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText()) != null)
                {
                    inicio = Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText());
                    fin = Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText());
                    precio.setText(Metodo.adquirirUtilidades(inicio,fin) +"");
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de fechas erroneos", "Validacion Rechazada", 1);
                    try {
                        LOGGER.addHandler((new FileHandler("errorFecha.xml", true)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    LOGGER.log(Level.INFO, "Se intentaron cargar fechas erroneas");
                }
            }
        }
}