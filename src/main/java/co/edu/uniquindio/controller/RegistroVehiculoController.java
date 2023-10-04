package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroVehiculoController {
    private final Propiedades propiedades = Propiedades.getInstance();
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label txtRegistro,txtMarca,txtPlaca,txtNombre,txtModelo, txtKm,txtPrecio,txtSillas,txtAutomatico;
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private TextField placa, nombre, marca, modelo, foto, km, precio;
    @FXML
    private ComboBox automatico, sillas;
    ObservableList<Integer> listaSillas = FXCollections.observableArrayList(1,2,3,4,5);
    ObservableList<String> listaAutomatico = FXCollections.observableArrayList("Si","No");
    //public void initialize(URL url, ResourceBundle resourceBundle) {
    //  txtRegistro.setText(propiedades.getResourceBundle().getString("textoRegistrar2"));
    //  txtMarca.setText(propiedades.getResourceBundle().getString("textoMarca2"));
    //  txtPlaca.setText(propiedades.getResourceBundle().getString("textoPlaca2"));
    //  txtNombre.setText(propiedades.getResourceBundle().getString("textoNombre2"));
    //    txtModelo.setText(propiedades.getResourceBundle().getString("textoModelo2"));
    //    txtKm.setText(propiedades.getResourceBundle().getString("textoKm2"));
    //    txtPrecio.setText(propiedades.getResourceBundle().getString("textoPrecioDiario"));
    //    txtSillas.setText(propiedades.getResourceBundle().getString("txtSillas"));
    //    txtAutomatico.setText(propiedades.getResourceBundle().getString("textoAutomatico2"));
    //}
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Desde la ventana de registro, regreso al menu");
        }
    }
    public void listarSillas (Event event)
    {
        Metodo.llenarComboSillas(sillas, listaSillas);
        Metodo.llenarComboAuto(automatico,listaAutomatico);
    }
    public void verificarAccion (ActionEvent e){
        Object evt =  e.getSource();
        if (evt.equals(botonRegistro))
        {
            if (!nombre.getText().isEmpty() && !placa.getText().isEmpty() && !km.getText().isEmpty() && !foto.getText().isEmpty() && !precio.getText().isEmpty() && !marca.getText().isEmpty() && !modelo.getText().isEmpty() && automatico.getSelectionModel().getSelectedIndex() != -1 && sillas.getSelectionModel().getSelectedIndex() != -1 ) {
                Metodo.crearVehiculo(placa.getText(),marca.getText(),nombre.getText(),modelo.getText(),km.getText(),precio.getText(),sillas.getSelectionModel().getSelectedItem().toString(),automatico.getSelectionModel().getSelectedItem().toString(),foto.getText());
                placa.setText("");marca.setText("");nombre.setText("");modelo.setText("");km.setText("");precio.setText("");foto.setText("");
                LOGGER.log(Level.INFO, "Se registro un vehiculo correctamente");
                JOptionPane.showMessageDialog(null,"Datos Registrados Correctamente","Registro Realizado",1);
                Metodo.mostrarVehiculos();
            }
            else
            {
                if(Integer.parseInt(precio.getText()) < 0)
                {
                    LOGGER.log(Level.INFO, "Se intento el registro con un precio de alquiler menor a 0");
                    JOptionPane.showMessageDialog(null,"El precio del vehículo es menor o igual a 0","Registro Fallido",2);
                }
                else
                {
                    LOGGER.log(Level.INFO, "Se intento el registro un vehiculo con campos vacios");
                    JOptionPane.showMessageDialog(null,"No se llenaron los campos correspondientes","Registro Fallido",2);
                }
            }
        }
    }
}
