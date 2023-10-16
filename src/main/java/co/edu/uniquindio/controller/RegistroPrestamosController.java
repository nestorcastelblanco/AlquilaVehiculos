package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Registros;
import co.edu.uniquindio.model.Vehiculos;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroPrestamosController implements Initializable, CambioIdiomaListener {
        private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
        private final Metodo metodo = Metodo.getInstance();
        @FXML
        private Label labelFechaInicio,labelFechaFinal,labelNombre,labelModelo,labelNombreCliente,labelIdentificacion,labelMarca,labelKm,labelAlquilerDia,labelSillas,labelAutomatico,labelPlaca;
        @FXML
        private Label txtImagen, placa,nombreCliente,identificacion, nombre, marca, modelo, kilometraje, alquilerDia,sillas,automatico;
        @FXML
        private Button botonRegreso,botonCargar, botonBuscar;
        @FXML
        private ImageView imagenVehiculo;
        @FXML
        private ComboBox<Registros> vehiculos;
        @FXML
        private TextField diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
        private LocalDate inicio, fin;
        ObservableList<Registros> listaVehiculos;
        private boolean state = false;
        HashSet<Registros> arrayVehiculos = new HashSet<>();
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        botonBuscar.setText(propiedades.getResourceBundle().getString("buscar"));
        botonCargar.setText(propiedades.getResourceBundle().getString("bttCargar"));
        labelFechaInicio.setText(propiedades.getResourceBundle().getString("txtFechaInicio"));
        labelFechaFinal.setText(propiedades.getResourceBundle().getString("txtFechaFinal"));
        labelNombre.setText(propiedades.getResourceBundle().getString("nombre"));
        labelIdentificacion.setText(propiedades.getResourceBundle().getString("identificacion"));
        labelModelo.setText(propiedades.getResourceBundle().getString("modelo"));
        labelNombreCliente.setText(propiedades.getResourceBundle().getString("nombreCliente"));
        labelPlaca.setText(propiedades.getResourceBundle().getString("placa"));
        labelMarca.setText(propiedades.getResourceBundle().getString("marca"));
        labelKm.setText(propiedades.getResourceBundle().getString("km"));
        labelAlquilerDia.setText(propiedades.getResourceBundle().getString("alquilerDia"));
        labelSillas.setText(propiedades.getResourceBundle().getString("sillas"));
        labelAutomatico.setText(propiedades.getResourceBundle().getString("automatico"));
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }
        private int indiceCombo;
        public void regresar (ActionEvent e) {
            Object evt = e.getSource();
            if (evt.equals(botonRegreso)) {
                metodo.loadStage("/paginaPrincipalAdmin.fxml", e, "Se ingresa a la pagina de administrador");
            }
        }
        public void llenarListaVehiculos(HashSet<Registros> array) {
            for (int i = 0; i < array.size(); i++) {
                array.stream().toList();
                listaVehiculos = FXCollections.observableArrayList(array);
                System.out.print("Lista Collentions" + listaVehiculos);
            }
        }
        public void buscar(ActionEvent actionEvent) {
            Object evt = actionEvent.getSource();
            if (evt.equals(botonBuscar)) {
                if (Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText()) != null && Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText()) != null)
                {
                    inicio = Metodo.verificarCampos(diaInicio.getText(), mesInicio.getText(), anioInicio.getText());
                    fin = Metodo.verificarCampos(diaFin.getText(), mesFin.getText(), anioFin.getText());
                    arrayVehiculos = Metodo.listarVehiculosAlquilados(inicio, fin);
                    llenarListaVehiculos(arrayVehiculos);
                    Metodo.llenarVehiculosRegistros(vehiculos, listaVehiculos);
                    state = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de fechas erroneos", "Validacion Rechazada", 1);
                    LOGGER.log(Level.INFO, "Se intentaron cargar fechas erroneas");
                }
            }
        }
    public void cargar(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonCargar))
        {
            if(state)
            {
                if (vehiculos.getSelectionModel().getSelectedIndex() != -1)
                {
                    indiceCombo = vehiculos.getSelectionModel().getSelectedIndex();
                    Registros vehiculoSeleccionado = vehiculos.getSelectionModel().getSelectedItem();
                    System.out.print(vehiculoSeleccionado.toString());
                    nombre.setText(vehiculoSeleccionado.getVehiculo().getNombre());
                    modelo.setText(vehiculoSeleccionado.getVehiculo().getModelo());
                    placa.setText(vehiculoSeleccionado.getVehiculo().getPlaca());
                    marca.setText(vehiculoSeleccionado.getVehiculo().getMarca());
                    kilometraje.setText(vehiculoSeleccionado.getVehiculo().getKilometraje());
                    alquilerDia.setText(String.valueOf(vehiculoSeleccionado.getVehiculo().getPrecioAlquilerDia()));
                    sillas.setText(vehiculoSeleccionado.getVehiculo().getNumeroSillas());
                    automatico.setText(vehiculoSeleccionado.getVehiculo().getAutomatico());
                    txtImagen.setText(vehiculoSeleccionado.toString());
                    nombreCliente.setText(vehiculoSeleccionado.getCliente().getNombre());
                    identificacion.setText(vehiculoSeleccionado.getCliente().getCedula());
                    Image imagen = new Image(vehiculoSeleccionado.getVehiculo().getFoto());
                    imagenVehiculo.setImage(imagen);
                    LOGGER.log(Level.INFO, "Se cargaron las propiedades de los vehiculos para mostrarlos");
                } else {
                    LOGGER.log(Level.INFO, "Se intentaron cargar atributos sin selccionar vehiculo");
                }
            }else{
                LOGGER.log(Level.INFO,"Se intento ingresar al apartado sin tener fechas verificadas");
            }

        }
    }
}


