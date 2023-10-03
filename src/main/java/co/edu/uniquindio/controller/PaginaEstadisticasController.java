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

public class PaginaEstadisticasController {
        private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        @FXML
        private Label txtImagen, placa,alquiler, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
        @FXML
        private Button botonRegreso, botonBuscar;
        @FXML
        private ImageView imagenVehiculo;
        @FXML
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
                Vehiculos vehiculo;
                vehiculo = Metodo.buscarVehiculoAlquiler();
                nombre.setText(vehiculo.getNombre());
                modelo.setText(vehiculo.getModelo());
                placa.setText(vehiculo.getPlaca());
                marca.setText(vehiculo.getMarca());
                kilometraje.setText(vehiculo.getKilometraje());
                alquilerDia.setText(String.valueOf(vehiculo.getPrecioAlquilerDia()));
                sillas.setText(vehiculo.getNumeroSillas());
                automatico.setText(vehiculo.getAutomatico());
                txtImagen.setText(vehiculo.toString());
                alquiler.setText(vehiculo.getContPrestamos()+"");
                Image imagen = new Image(vehiculo.getFoto());
                imagenVehiculo.setImage(imagen);
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