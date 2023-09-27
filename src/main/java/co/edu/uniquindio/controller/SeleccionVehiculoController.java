package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class SeleccionVehiculoController {
    @FXML
    private Button botonRegreso;
    @FXML
    private ComboBox vehiculos;
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
    public void llenarListaVehiculos()
    {
        for(int i = 0; i<arrayVehiculos.size();i++)
        {
            listaVehiculos = FXCollections.observableArrayList(arrayVehiculos.get(i));
        }

    }


}
