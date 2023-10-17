package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Propiedades;
import co.edu.uniquindio.model.Vehiculos;
import co.edu.uniquindio.utils.CambioIdiomaEvent;
import co.edu.uniquindio.utils.CambioIdiomaListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionVehiculoController implements Initializable, CambioIdiomaListener {
    private final Metodo metodo = Metodo.getInstance();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    @FXML
    private Label txtImagen, placa, nombre, marca, modelo, kilometraje, alquilerDia, sillas, automatico;
    @FXML
    private Label txtNombre, txtModelo, txtFechaIn, txtFechaFin, txtPlaca, txtMarca, txtKm, txtAlquiler, txtSillas, txtAutomatico;
    @FXML
    private Button botonRegreso, botonCargar, botonAlquilar, botonBuscar;
    @FXML
    private ImageView imagenVehiculo;
    @FXML
    private ComboBox<Vehiculos> vehiculos;
    @FXML
    private TextField diaInicio, mesInicio, anioInicio, diaFin, mesFin, anioFin;
    private String msg,msg1,msg2,msg3,msg4,msg5,msg6;
    private boolean stateFecha = false;
    Cliente clienteSistema;
    private LocalDate inicio, fin;
    ObservableList<Vehiculos> listaVehiculos;
    ArrayList<Vehiculos> arrayVehiculos = new ArrayList<Vehiculos>();
    private int indiceCombo;
    private static final Propiedades propiedades = Propiedades.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Propiedades.getInstance().addCambioIdiomaListener(this);
        cambiarIdioma();
    }
    public void cambiarIdioma() {
        botonCargar.setText(propiedades.getResourceBundle().getString("bttCargar"));
        botonRegreso.setText(propiedades.getResourceBundle().getString("bttRegresar"));
        botonAlquilar.setText(propiedades.getResourceBundle().getString("bttAlquilar"));
        botonBuscar.setText(propiedades.getResourceBundle().getString("buscar"));
        txtNombre.setText(propiedades.getResourceBundle().getString("nombre"));
        txtModelo.setText(propiedades.getResourceBundle().getString("modelo"));
        txtPlaca.setText(propiedades.getResourceBundle().getString("placa"));
        txtMarca.setText(propiedades.getResourceBundle().getString("marca"));
        txtKm.setText(propiedades.getResourceBundle().getString("km"));
        txtAlquiler.setText(propiedades.getResourceBundle().getString("alquilerDia"));
        txtSillas.setText(propiedades.getResourceBundle().getString("sillas"));
        txtAutomatico.setText(propiedades.getResourceBundle().getString("automatico"));
        txtFechaIn.setText(propiedades.getResourceBundle().getString("txtFechaInicio"));
        txtFechaFin.setText(propiedades.getResourceBundle().getString("txtFechaFinal"));
        msg = propiedades.getResourceBundle().getString("msg");
        msg1 = propiedades.getResourceBundle().getString("msg1");
        msg2 = propiedades.getResourceBundle().getString("msg2");
        msg3 = propiedades.getResourceBundle().getString("msg3");
        msg4 = propiedades.getResourceBundle().getString("msg4");
        msg5 = propiedades.getResourceBundle().getString("msg5");
        msg6 = propiedades.getResourceBundle().getString("msg6");
    }
    @Override
    public void onCambioIdioma(CambioIdiomaEvent evento) {
        cambiarIdioma();
    }

    public void regresar(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            metodo.loadStage("/pngPrincipal.fxml", e, "Se ingresa a la pagina principal");
        }
    }
    public void listarVehiculos(Event event) {
        recibirClienteSesion();
    }

    private void recibirClienteSesion() {
        clienteSistema = Metodo.enviarCliente();
    }

    public void llenarListaVehiculos() {
        for (int i = 0; i < arrayVehiculos.size(); i++) {
            arrayVehiculos.stream().toList();
            listaVehiculos = FXCollections.observableArrayList(arrayVehiculos);
            System.out.print("Lista Collentions" + listaVehiculos);
        }
    }
    public void buscar(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonBuscar)) {
            if(Metodo.verificarCampos(diaInicio.getText(),mesInicio.getText(),anioInicio.getText()) != null && Metodo.verificarCampos(diaFin.getText(),mesFin.getText(),anioFin.getText()) != null)
            {
                inicio = Metodo.verificarCampos(diaInicio.getText(),mesInicio.getText(),anioInicio.getText());
                fin = Metodo.verificarCampos(diaFin.getText(),mesFin.getText(),anioFin.getText());
                if (inicio.isBefore(fin))
                {
                    stateFecha = true;
                    arrayVehiculos = Metodo.vehiculosDisponibles(inicio,fin);
                    llenarListaVehiculos();
                    Metodo.llenarVehiculos(vehiculos, listaVehiculos);
                }else
                {
                    LOGGER.log(Level.WARNING, "La fecha de inicio marcada se encuentra despues de la final");
                }
            }
            else {
                LOGGER.log(Level.INFO, "Se intentaron cargar fechas erroneas");
            }
        }

    }
    public void cargar(ActionEvent actionEvent) {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonCargar))
        {
            if (stateFecha)
            {
                if (vehiculos.getSelectionModel().getSelectedIndex() != -1)
                {
                    indiceCombo = vehiculos.getSelectionModel().getSelectedIndex();
                    Vehiculos vehiculoSeleccionado = vehiculos.getSelectionModel().getSelectedItem();
                    System.out.print(vehiculoSeleccionado.toString());
                    nombre.setText(vehiculoSeleccionado.getNombre());
                    modelo.setText(vehiculoSeleccionado.getModelo());
                    placa.setText(vehiculoSeleccionado.getPlaca());
                    marca.setText(vehiculoSeleccionado.getMarca());
                    kilometraje.setText(vehiculoSeleccionado.getKilometraje());
                    alquilerDia.setText(String.valueOf(vehiculoSeleccionado.getPrecioAlquilerDia()));
                    sillas.setText(vehiculoSeleccionado.getNumeroSillas());
                    automatico.setText(vehiculoSeleccionado.getAutomatico());
                    if (Metodo.isImageValid(vehiculoSeleccionado.getFoto()))
                    {
                        txtImagen.setText(vehiculoSeleccionado.toString());
                        Image imagen = new Image(vehiculoSeleccionado.getFoto());
                        imagenVehiculo.setImage(imagen);
                        LOGGER.log(Level.INFO, "Se añadio una imagen valida");
                    }else {
                        LOGGER.log(Level.INFO,"Se intento añadir una imagen no valida");
                    }
                    LOGGER.log(Level.INFO, "Se cargaron las propiedades de los vehiculos para mostrarlos");
                } else {
                    LOGGER.log(Level.INFO, "Se intentaron cargar atributos sin selccionar vehiculo");
                }
            }else {
                LOGGER.log(Level.INFO, "No se han ingresado fechas validas");
            }
        }
    }
    public void alquilar(ActionEvent actionEvent) throws IOException {
        Object evt = actionEvent.getSource();
        if (evt.equals(botonAlquilar))
        {
            if(stateFecha)
            {
                if (indiceCombo != -1)
                {
                    diaInicio.setText("");
                    diaFin.setText("");
                    mesInicio.setText("");
                    mesFin.setText("");
                    anioInicio.setText("");
                    anioFin.setText("");
                    Metodo.buscarVehiculo(vehiculos.getSelectionModel().getSelectedItem(),inicio,fin);
                    Metodo.cargarRegistro(clienteSistema,vehiculos.getSelectionModel().getSelectedItem(), inicio,fin,msg,msg1,msg2,msg3,msg4,msg5,msg6);
                    Metodo.cargarFactura(clienteSistema,inicio,fin,vehiculos.getSelectionModel().getSelectedItem().getPrecioAlquilerDia());
                    Metodo.imprimirRegistros();
                    LOGGER.log(Level.INFO, "Se genero un alquiler de vehiculo");
                }else {
                    LOGGER.log(Level.INFO, "Se intento alquilar un vehiculo sin seleccionarlo");
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Datos necesarios no llenados");
                    alert.setContentText("No ha seleccionado un item valido");
                    alert.show();
                }
            }else
            {
                LOGGER.log(Level.INFO, "No se han validado las fechas");
            }
        }
    }
}
