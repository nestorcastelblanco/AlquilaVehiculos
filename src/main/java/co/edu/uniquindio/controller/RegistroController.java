package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroController implements Initializable, CambioIdiomaListener {
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Button botonRegreso,botonRegistro;
    @FXML
    private Label txtRegistro,txtUsuario,txtNombre,txtCedula,txtCelular,txtCorreo,txtCiudad,txtDireccion, txtContrasena;
    @FXML
    private TextField usuario,nombre,cedula,celular,correo,ciudad,direccion;
    @FXML
    private PasswordField contrasena;
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        botonRegistro.setText(propiedades.getResourceBundle().getString("bttRegistrarse"));
        txtRegistro.setText(propiedades.getResourceBundle().getString("txtRegistro"));
        txtCedula.setText(propiedades.getResourceBundle().getString("cedula"));
        txtCelular.setText(propiedades.getResourceBundle().getString("celular"));
        txtCorreo.setText(propiedades.getResourceBundle().getString("correo"));
        txtCiudad.setText(propiedades.getResourceBundle().getString("ciudad"));
        txtDireccion.setText(propiedades.getResourceBundle().getString("direccion"));
        txtUsuario.setText(propiedades.getResourceBundle().getString("usuario"));
        txtContrasena.setText(propiedades.getResourceBundle().getString("contrasena"));
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }
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
                    Metodo.crearCliente(cedula.getText(),nombre.getText(),celular.getText(),correo.getText(),ciudad.getText(),direccion.getText(),usuario.getText(),contrasena.getText());
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
