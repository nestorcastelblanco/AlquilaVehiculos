package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroController {
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private TextField usuario,nombre,cedula,celular,correo,ciudad,direccion;
    @FXML
    private PasswordField contraseña;
    @FXML
    public void verificarAccion (ActionEvent e){
        Object evt =  e.getSource();
        if (evt.equals(botonRegistro))
        {
            if (!nombre.getText().isEmpty() && !correo.getText().isEmpty() && !cedula.getText().isEmpty() && !usuario.getText().isEmpty() && !contraseña.getText().isEmpty() && !ciudad.getText().isEmpty() && !celular.getText().isEmpty() && !direccion.getText().isEmpty()) {
                if(Metodo.verificarCredenciales(usuario.getText(),contraseña.getText()))
                {
                    try {
                        LOGGER.addHandler((new FileHandler("credencialesEnUso.xml",true)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    LOGGER.log(Level.INFO, "Se intento crear credenciales ya creadas");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error en las Credenciales");
                    alert.setContentText("Las credenciales proporcionadas se encuentran en uso");
                    alert.show();
                    usuario.setText("");contraseña.setText("");
                }
                else
                {
                    try {
                        LOGGER.addHandler((new FileHandler("credencialesLibres.xml",true)));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    LOGGER.log(Level.INFO, "Se crean nuevas credenciales");
                    Metodo.crearCliente(cedula.getText(),nombre.getText(), celular.getText(), correo.getText(), ciudad.getText(), direccion.getText(), usuario.getText(),contraseña.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Datos registrados con exito");
                    alert.setContentText("La validacion de credenciales es correcta");
                    alert.show();
                    cedula.setText("");nombre.setText("");celular.setText("");correo.setText("");ciudad.setText("");direccion.setText("");usuario.setText("");contraseña.setText("");
                }
            }
            else
            {
                try {
                    LOGGER.addHandler((new FileHandler("RegistroEmpty.xml",true)));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LOGGER.log(Level.INFO, "Se intento crear credenciales con campos en blanco ");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Datos necesarios no llenados");
                alert.setContentText("La validacion de credenciales es incorrecta");
                alert.show();
            }
        }
    }
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            Metodo.loadStage("/paginaPrincipalAdmin.fxml", e);
            try {
                LOGGER.addHandler((new FileHandler("botonRegresoRegistro.xml",true)));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            LOGGER.log(Level.INFO, "Se regreso a la pagina de admin");
        }
    }

}
