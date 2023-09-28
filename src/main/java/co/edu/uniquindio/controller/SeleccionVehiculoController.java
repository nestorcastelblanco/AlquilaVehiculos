package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionVehiculoController {
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label txtImagen, placa, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
    @FXML
    private Button botonRegreso,botonCargar, botonAlquilar;
    @FXML
    private ImageView imagenVehiculo;
    @FXML
    private ComboBox<Vehiculos> vehiculos;
    Cliente clienteSistema;
    ObservableList<Vehiculos> listaVehiculos;
    ArrayList<Vehiculos> arrayVehiculos = new ArrayList<Vehiculos>();
    private int indiceCombo;
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/pngPrincipal.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("BotonRegresoVenSeleccion.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Se regreso al portal de ingreso");
        }
    }
    public void recibirVehiculos()
    {
        arrayVehiculos = Metodo.enviarVehiculos();
    }
    public void listarVehiculos(Event event)
    {
        recibirClienteSesion();
        recibirVehiculos();
        llenarListaVehiculos();
        Metodo.llenarVehiculos(vehiculos,listaVehiculos);
    }
    private void recibirClienteSesion() {
        clienteSistema = Metodo.enviarCliente();
    }
    public void llenarListaVehiculos() {
        for (int i = 0; i < arrayVehiculos.size(); i++) {
            arrayVehiculos.stream().toList();
            listaVehiculos = FXCollections.observableArrayList(arrayVehiculos);
            System.out.print("Lista Collentions" + listaVehiculos);
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
                alquilerDia.setText(vehiculoSeleccionado.getPrecioAlquilerDia());
                sillas.setText(vehiculoSeleccionado.getNumeroSillas());
                automatico.setText(vehiculoSeleccionado.getAutomatico());
                txtImagen.setText(vehiculoSeleccionado.toString());
                Image imagen = new Image(vehiculoSeleccionado.getFoto());
                imagenVehiculo.setImage(imagen);
                try {
                    LOGGER.addHandler((new FileHandler("BotonCargarVehiculo.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se cargaron las propiedades de los vehiculos para mostrarlos");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos necesarios no llenados");
                alert.setContentText("No ha seleccionado un item valido");
                alert.show();
                try {
                    LOGGER.addHandler((new FileHandler("errorCarga.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se intentaron cargar atributos sin selccionar vehiculo");
            }
        }
    }
    public void alquilar(ActionEvent actionEvent)
    {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonAlquilar))
        {
            if (indiceCombo != -1)
            {
                Metodo.cargarRegistro(clienteSistema,vehiculos.getSelectionModel().getSelectedItem());
                Metodo.imprimirRegistros();
                try {
                    LOGGER.addHandler((new FileHandler("AlquilarVehiculo.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se genero un alquiler de vehiculo");
            }else {
                try {
                    LOGGER.addHandler((new FileHandler("errorVehiculo.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se intento alquilar un vehiculo sin seleccionarlo");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos necesarios no llenados");
                alert.setContentText("No ha seleccionado un item valido");
                alert.show();
            }
        }
    }

}
