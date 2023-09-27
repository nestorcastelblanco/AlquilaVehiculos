package co.edu.uniquindio.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.model.Metodo;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class IngresoController {

    @FXML
    private Button bttIngreso,botonRegistro;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contraseña;

    @FXML
    public void ingresar (ActionEvent e){
        Object evt =  e.getSource();
        if (evt.equals(bttIngreso)) {
            if (usuario.getText().equals("admin") && contraseña.getText().equals("admin")) {
                Metodo.loadStage("/paginaPrincipalAdmin.fxml", e);
            } else {
                if (!usuario.getText().isEmpty() && !contraseña.getText().isEmpty()) {
                    if (Metodo.verificarDatos(usuario.getText(), contraseña.getText())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Datos ingresados con exito");
                        alert.setContentText("La validacion de credenciales es correcta");
                        alert.show();
                        Metodo.recibirClienteSesion(Metodo.retornarCliente(usuario.getText(),contraseña.getText()));
                        Metodo.loadStage("/paginaVehiculo.fxml", e);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Datos ingresados no validos");
                        alert.setContentText("La validacion de credenciales es incorrecta");
                        alert.show();
                    }
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos necesarios no llenados");
            alert.setContentText("La validacion de credenciales es incorrecta");
            alert.show();

        }
    }
}
