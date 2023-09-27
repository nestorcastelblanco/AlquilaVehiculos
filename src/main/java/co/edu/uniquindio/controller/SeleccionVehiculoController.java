package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class SeleccionVehiculoController {
    @FXML
    private Label placa, nombre, marca, modelo, foto, kilometraje, alquilerDia,sillas,automatico;
    @FXML
    private Button botonRegreso,botonCargar;
    @FXML
    private ComboBox<Vehiculos> vehiculos;
    ObservableList<Vehiculos> listaVehiculos;
    ArrayList<Vehiculos> arrayVehiculos = new ArrayList<Vehiculos>();
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/pngPrincipal.fxml", e);
        }
    }
    public void recibirVehiculos()
    {
        arrayVehiculos = Metodo.enviarVehiculos();

    }
    public void listarVehiculos(Event event)
    {
        recibirVehiculos();
        llenarListaVehiculos();
        Metodo.llenarVehiculos(vehiculos,listaVehiculos);
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
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos necesarios no llenados");
                alert.setContentText("No ha seleccionado un item valido");
                alert.show();
            }
        }
    }
}
