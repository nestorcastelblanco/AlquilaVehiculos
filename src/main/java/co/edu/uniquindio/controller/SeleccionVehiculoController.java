package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.w3c.dom.events.Event;

import java.util.ArrayList;

public class SeleccionVehiculoController {
    public static ArrayList<Vehiculos> vehiculosCargados = new ArrayList<Vehiculos>();
    @FXML
    private static ComboBox<Vehiculos> vehiculos;
    public static ObservableList<Vehiculos> listaVehiculos;

    public static void cargarComboVehiculo(){
        Metodo.llenarVehiculos(vehiculos, listaVehiculos);

    }
    public static void cargarVehiculos()
    {
        vehiculosCargados = Metodo.enviarVehiculos();
        cargarLista();
        cargarComboVehiculo();
    }
    public static void cargarLista()
    {
        for(int i = 0; i < vehiculosCargados.size(); i++)
        {
            listaVehiculos = FXCollections.observableArrayList(vehiculosCargados.get(i));
        }
    }
}
