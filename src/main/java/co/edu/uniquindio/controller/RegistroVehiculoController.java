package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroVehiculoController implements Initializable, CambioIdiomaListener {
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label txtRegistro,txtMarca,txtRef,txtPlaca,txtNombre,txtModelo, txtKm,txtDia,txtSillas,txtAutomatico;
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private TextField placa, nombre, marca, modelo, foto, km, precio;
    @FXML
    private ComboBox automatico, sillas;
    ObservableList<String> listaSillas = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> listaAutomatico = FXCollections.observableArrayList("Si","No");
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        txtRegistro.setText(propiedades.getResourceBundle().getString("txtRegistro"));
        txtModelo.setText(propiedades.getResourceBundle().getString("modelo"));
        modelo.setPromptText(propiedades.getResourceBundle().getString("modelo"));
        txtMarca.setText(propiedades.getResourceBundle().getString("marca"));
        marca.setPromptText(propiedades.getResourceBundle().getString("marca"));
        txtPlaca.setText(propiedades.getResourceBundle().getString("placa"));
        placa.setPromptText(propiedades.getResourceBundle().getString("placa"));
        txtNombre.setText(propiedades.getResourceBundle().getString("nombre"));
        nombre.setPromptText(propiedades.getResourceBundle().getString("nombre"));        txtModelo.setText(propiedades.getResourceBundle().getString("modelo"));
        txtKm.setText(propiedades.getResourceBundle().getString("km"));
        km.setPromptText(propiedades.getResourceBundle().getString("km"));
        txtDia.setText(propiedades.getResourceBundle().getString("alquilerDia"));
        precio.setPromptText(propiedades.getResourceBundle().getString("alquilerDia"));
        txtSillas.setText(propiedades.getResourceBundle().getString("sillas"));
        txtAutomatico.setText(propiedades.getResourceBundle().getString("automatico"));
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        botonRegistro.setText(propiedades.getResourceBundle().getString("txtRegistro"));
        txtRef.setText(propiedades.getResourceBundle().getString("imagen"));
        foto.setPromptText(propiedades.getResourceBundle().getString("imagen"));
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }
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
            if (!nombre.getText().isEmpty() && !placa.getText().isEmpty() && (Metodo.verificarPlaca(placa.getText()) == true) && !km.getText().isEmpty() && !foto.getText().isEmpty() && !precio.getText().isEmpty() && (Integer.parseInt(precio.getText()) >= 0) && !marca.getText().isEmpty() && !modelo.getText().isEmpty() && (automatico.getSelectionModel().getSelectedIndex() != -1) && (sillas.getSelectionModel().getSelectedIndex() != -1)) {
                Metodo.crearVehiculo(placa.getText(),marca.getText(),nombre.getText(),modelo.getText(),km.getText(),String.valueOf(precio.getText()),sillas.getSelectionModel().getSelectedItem().toString(),automatico.getSelectionModel().getSelectedItem().toString(),foto.getText());
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
