package co.edu.uniquindio.controller;

import co.edu.uniquindio.exceptions.InformacionErronea;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaginaEstadisticasController {
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        @FXML
        private Label placa,alquiler, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
        @FXML
        private Button botonRegreso, botonBuscar;
        @FXML
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pagina de administrador");
            }
        }
    // public void initialize(URL url, ResourceBundle resourceBundle) {
    //
    //  }
        public void buscar(ActionEvent actionEvent) throws InformacionErronea {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                Vehiculos vehiculo;
                vehiculo = Metodo.buscarVehiculoAlquiler();
                System.out.println(vehiculo);
                nombre.setText(vehiculo.getNombre());
                modelo.setText(vehiculo.getModelo());
                placa.setText(vehiculo.getPlaca());
                marca.setText(vehiculo.getMarca());
                kilometraje.setText(vehiculo.getKilometraje());
                alquilerDia.setText(String.valueOf(vehiculo.getPrecioAlquilerDia()));
                sillas.setText(vehiculo.getNumeroSillas());
                automatico.setText(vehiculo.getAutomatico());
                alquiler.setText(vehiculo.getContPrestamos()+"");
                LOGGER.log(Level.INFO,"Se cargaron los datos correspondientes");
                } else {
                    LOGGER.log(Level.INFO,"");
                    throw new InformacionErronea("Se ingreso informacion Erronea");
                }
        }
}