package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;

public class RegistroVehiculoController {
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private TextField placa, nombre, marca, modelo, foto, km, precio;
    @FXML
    private ComboBox automatico, sillas;
    ObservableList<Integer> listaSillas = FXCollections.observableArrayList(1,2,3,4,5);
    ObservableList<String> listaAutomatico = FXCollections.observableArrayList("Si","No");
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/pngPrincipal.fxml", e);
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
                JOptionPane.showMessageDialog(null,"Datos Registrados Correctamente","Registro Realizado",1);
                Metodo.mostrarVehiculos();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se llenaron los campos correspondientes","Registro Fallido",2);
            }
        }
    }
}
