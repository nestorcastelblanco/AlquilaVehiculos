package co.edu.uniquindio.controller;

import co.edu.uniquindio.exceptions.InformacionErronea;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaginaFacturasController implements Initializable, CambioIdiomaListener {
        private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        private final Metodo metodo = Metodo.getInstance();
        @FXML
        private Label precio,fechaInicioLabel,fechaFinalLabel,txtLabel;
        @FXML
        private Button botonRegreso, botonBuscar;
        @FXML
        private TextField diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
        private LocalDate inicio, fin;
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        txtLabel.setText(propiedades.getResourceBundle().getString("txtTitulo"));
        fechaInicioLabel.setText(propiedades.getResourceBundle().getString("txtFechaInicio"));
        fechaFinalLabel.setText(propiedades.getResourceBundle().getString("txtFechaFinal"));
        botonBuscar.setText(propiedades.getResourceBundle().getString("buscar"));
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pestaña de administrador");
            }
        }
        public void buscar(ActionEvent actionEvent) throws InformacionErronea {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                if (Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText()) != null && Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText()) != null)
                {
                    inicio = Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText());
                    fin = Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText());
                    precio.setText(Metodo.adquirirUtilidades(inicio,fin) +"");
                } else {
                    LOGGER.log(Level.INFO, "Se intentaron cargar fechas erroneas");
                    throw new InformacionErronea("Se ingresaron datos erroneos");
                }
            }
        }
}