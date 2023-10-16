package co.edu.uniquindio.controller;

import co.edu.uniquindio.exceptions.InformacionErronea;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Vehiculos;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaginaEstadisticasController implements Initializable, CambioIdiomaListener {
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label placaT,numAlquilerT, alquilerT, nombreT, marcaT, modeloT, kmT,sillasT,automaticoT;
    @FXML
    private Label placa,alquiler, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
    @FXML
    private Button botonRegreso, botonBuscar;
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        nombreT.setText(propiedades.getResourceBundle().getString("nombre"));
        modeloT.setText(propiedades.getResourceBundle().getString("modelo"));
        botonBuscar.setText(propiedades.getResourceBundle().getString("buscar"));
        numAlquilerT.setText(propiedades.getResourceBundle().getString("numeroAlquiler"));
        placaT.setText(propiedades.getResourceBundle().getString("placa"));
        marcaT.setText(propiedades.getResourceBundle().getString("marca"));
        kmT.setText(propiedades.getResourceBundle().getString("km"));
        alquilerT.setText(propiedades.getResourceBundle().getString("alquiler"));
        sillasT.setText(propiedades.getResourceBundle().getString("sillas"));
        automaticoT.setText(propiedades.getResourceBundle().getString("automatico"));
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }
        @FXML
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pagina de administrador");
            }
        }
        public void buscar(ActionEvent actionEvent) throws InformacionErronea {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                Vehiculos vehiculo;
                vehiculo = Metodo.buscarVehiculoAlquiler();
                System.out.println(vehiculo);
                nombre.setText(vehiculo.getNombre());
                modelo.setText(vehiculo.getModelo());
                placa.setText(vehiculo.getPlaca());
                marca.setText(vehiculo.getMarca());
                kilometraje.setText(vehiculo.getKilometraje());
                alquilerDia.setText(String.valueOf(vehiculo.getPrecioAlquilerDia()));
                sillas.setText(vehiculo.getNumeroSillas());
                automatico.setText(vehiculo.getAutomatico());
                alquiler.setText(vehiculo.getContPrestamos()+"");
                LOGGER.log(Level.INFO,"Se cargaron los datos correspondientes");
                } else {
                    LOGGER.log(Level.INFO,"");
                    throw new InformacionErronea("Se ingreso informacion Erronea");
                }
        }
}