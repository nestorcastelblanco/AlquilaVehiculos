package co.edu.uniquindio.controller;

import co.edu.uniquindio.exceptions.CampoVacioException;
import co.edu.uniquindio.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresoController {
    private final Metodo metodo = Metodo.getInstance();
    private boolean esIngles = false;
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label tituloIngreso, textoUsuario, textoContrasena;
    @FXML
    private Button bttIngreso, cambiarIdioma;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contrasena;
    private final Propiedades propiedades = Propiedades.getInstance();
    private IngresoAdminController ingresoAdminController;

    public void setIngresoAdminController(IngresoAdminController ingresoAdminController) {
        this.ingresoAdminController = ingresoAdminController;
    }

    @FXML
    public void ingresar(ActionEvent e) throws InformacionRepetidaException, CampoVacioException {
        Object evt = e.getSource();
        if (evt.equals(bttIngreso)) {
            if (usuario.getText().equals("admin") && contrasena.getText().equals("admin")) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pestaña como administrador");
            } else {
                if (!usuario.getText().isEmpty() && !contrasena.getText().isEmpty()) {
                    if (Metodo.verificarDatos(usuario.getText(), contrasena.getText())) {
                        metodo.loadStage("/paginaVehiculo.fxml", e, "Se ingresa como usuario");
                    } else {
                        LOGGER.log(Level.WARNING, "Credenciales Invalidas");
                    }
                } else {
                    LOGGER.log(Level.WARNING, "Credenciales vacias");
                    throw new CampoVacioException("Se ingresaron credenciales invalidas");
                }
            }
        }
    }
    public void cambiarIdioma(ActionEvent actionEvent) {
        if (esIngles) {
            Propiedades.getInstance().setLocale(new Locale("es", "ES"));
        } else {
            Propiedades.getInstance().setLocale(Locale.ENGLISH);
        }
        // Invierte el valor de esIngles para la próxima vez
        esIngles = !esIngles;
        // Actualiza la interfaz de usuario
        bttIngreso.setText(propiedades.getResourceBundle().getString("textoIngreso"));
        tituloIngreso.setText(propiedades.getResourceBundle().getString("textoIngreso"));
        textoContrasena.setText(propiedades.getResourceBundle().getString("textoContrasena"));
        textoUsuario.setText(propiedades.getResourceBundle().getString("textoUsuario"));
        usuario.setPromptText(propiedades.getResourceBundle().getString("textoTxtUs"));
        cambiarIdioma.setText(propiedades.getResourceBundle().getString("bttCambiar"));
        contrasena.setPromptText(propiedades.getResourceBundle().getString("textoTxtCn"));

    }

}



