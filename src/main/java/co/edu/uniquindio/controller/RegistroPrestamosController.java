package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroPrestamosController {
        private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        private final Metodo metodo = Metodo.getInstance();
        @FXML
        private Label txtImagen, placa,nombreCliente,identificacion, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
        @FXML
        private Button botonRegreso,botonCargar, botonBuscar;
        @FXML
        private ImageView imagenVehiculo;
        @FXML
        private ComboBox<Vehiculos> vehiculos;
        @FXML
        private TextField diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
        private LocalDate inicio, fin;
        ObservableList<Vehiculos> listaVehiculos;
        HashSet<Vehiculos> arrayVehiculos = new HashSet<>();
        private int indiceCombo;
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pagina de administrador");
            }
        }
        public void llenarListaVehiculos(HashSet<Vehiculos> array) {
            for (int i = 0; i < array.size(); i++) {
                array.stream().toList();
                listaVehiculos = FXCollections.observableArrayList(array);
                System.out.print("Lista Collentions" + listaVehiculos);
            }
        }
        public void buscar(ActionEvent actionEvent) {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                if (Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText()) != null && Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText()) != null)
                {
                    inicio = Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText());
                    fin = Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText());
                    arrayVehiculos = Metodo.listarVehiculosAlquilados(inicio, fin);
                    llenarListaVehiculos(arrayVehiculos);
                    Metodo.llenarVehiculos(vehiculos, listaVehiculos);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de fechas erroneos", "Validacion Rechazada", 1);
                    LOGGER.log(Level.INFO, "Se intentaron cargar fechas erroneas");
                }
            }
        }
    public void cargar(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonCargar))
        {
            if (vehiculos.getSelectionModel().getSelectedIndex() != -1)
            {
                indiceCombo = vehiculos.getSelectionModel().getSelectedIndex();
                Vehiculos vehiculoSeleccionado = vehiculos.getSelectionModel().getSelectedItem();
                System.out.print(vehiculoSeleccionado.toString());
                nombre.setText(vehiculoSeleccionado.getNombre());
                modelo.setText(vehiculoSeleccionado.getModelo());
                placa.setText(vehiculoSeleccionado.getPlaca());
                marca.setText(vehiculoSeleccionado.getMarca());
                kilometraje.setText(vehiculoSeleccionado.getKilometraje());
                alquilerDia.setText(String.valueOf(vehiculoSeleccionado.getPrecioAlquilerDia()));
                sillas.setText(vehiculoSeleccionado.getNumeroSillas());
                automatico.setText(vehiculoSeleccionado.getAutomatico());
                txtImagen.setText(vehiculoSeleccionado.toString());
                nombreCliente.setText(vehiculoSeleccionado.getCliente().getNombre());
                identificacion.setText(vehiculoSeleccionado.getCliente().getCedula());
                Image imagen = new Image(vehiculoSeleccionado.getFoto());
                imagenVehiculo.setImage(imagen);
                LOGGER.log(Level.INFO, "Se cargaron las propiedades de los vehiculos para mostrarlos");
            } else {
                LOGGER.log(Level.INFO, "Se intentaron cargar atributos sin selccionar vehiculo");
            }
        }
    }
}


