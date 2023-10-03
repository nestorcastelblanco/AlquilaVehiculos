package co.edu.uniquindio.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import co.edu.uniquindio.model.Metodo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresoController {
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button bttIngreso;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contrasena;
    @FXML
    public void ingresar (ActionEvent e){
        Object evt =  e.getSource();
        if (evt.equals(bttIngreso)) {
            if (usuario.getText().equals("admin") && contrasena.getText().equals("admin")) {
                Metodo.loadStage("/paginaPrincipalAdmin.fxml", e);
                try {
                    LOGGER.addHandler((new FileHandler("ingresoAdmin.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se ingreso con credenciales administrativas");
            } else {
                if (!usuario.getText().isEmpty() && !contrasena.getText().isEmpty()) {
                    if (Metodo.verificarDatos(usuario.getText(), contrasena.getText())) {
                        try {
                            LOGGER.addHandler((new FileHandler("ingresoCorrecto.xml",true)));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        LOGGER.log(Level.INFO, "Se ingreso con credenciales correctas y entra a la seccion de seleccion");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Datos ingresados con exito");
                        alert.setContentText("La validacion de credenciales es correcta");
                        alert.show();
                        Metodo.recibirClienteSesion(Metodo.retornarCliente(usuario.getText(),contrasena.getText()));
                        Metodo.loadStage("/paginaVehiculo.fxml", e);
                    } else {
                        try {
                            LOGGER.addHandler((new FileHandler("ingresoIncorrecto.xml",true)));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        LOGGER.log(Level.INFO, "Se ingresaron ccredenciales incorrectas");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Datos ingresados no validos");
                        alert.setContentText("La validacion de credenciales es incorrecta");
                        alert.show();
                    }
                }
                else
                {
                    try {
                        LOGGER.addHandler((new FileHandler("ingresoBlanco.xml",true)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    LOGGER.log(Level.INFO, "Se ingreso con credenciales vacias");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Datos necesarios no llenados");
                    alert.setContentText("La validacion de credenciales es incorrecta");
                    alert.show();
                }
            }
        }
    }
}