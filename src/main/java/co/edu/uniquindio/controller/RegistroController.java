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
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private TextField usuario,nombre,cedula,celular,correo,ciudad,direccion;
    @FXML
    private PasswordField contrasena;
    @FXML
    public void verificarAccion (ActionEvent e){
        Object evt =  e.getSource();
        if (evt.equals(botonRegistro))
        {
            if (!nombre.getText().isEmpty() && !correo.getText().isEmpty() && !cedula.getText().isEmpty() && !usuario.getText().isEmpty() && !contrasena.getText().isEmpty() && !ciudad.getText().isEmpty() && !celular.getText().isEmpty() && !direccion.getText().isEmpty()) {
                if(Metodo.verificarCredenciales(usuario.getText(),contrasena.getText()))
                {
                    LOGGER.log(Level.INFO, "Credeciales no permitidas");
                }
                else
                {
                    LOGGER.log(Level.INFO, "Se crean nuevas credenciales");
                    cedula.setText("");nombre.setText("");celular.setText("");correo.setText("");ciudad.setText("");direccion.setText("");usuario.setText("");contrasena.setText("");
                }
            }
            else
            {
                LOGGER.log(Level.INFO, "Se intento crear credenciales con campos en blanco ");
            }
        }
    }
    public void regresar (ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            metodo.loadStage("/paginaPrincipalAdmin.fxml", e,"Se ingresa a la ventana de administrador");
        }else
        {
            LOGGER.log(Level.INFO, "Se regresó a la pagina de admin");
        }
    }

}
