package co.edu.uniquindio.controller;

import co.edu.uniquindio.model.Cliente;
import co.edu.uniquindio.model.Metodo;
import co.edu.uniquindio.model.Vehiculos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionVehiculoController {
    //private final Propiedades propiedades = Propiedades.getInstance();
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
    private boolean stateFecha = false;
    Cliente clienteSistema;
    private LocalDate inicio, fin;
    ObservableList<Vehiculos> listaVehiculos;
    ArrayList<Vehiculos> arrayVehiculos = new ArrayList<Vehiculos>();
    private int indiceCombo;

    public void regresar(ActionEvent e) {
        Object evt = e.getSource();
        if (evt.equals(botonRegreso)) {
            metodo.loadStage("/pngPrincipal.fxml", e, "Se ingresa a la pagina principal");
        }
    }

    //public void initialize(URL url, ResourceBundle resourceBundle) {
    //   botonCargar.setText(propiedades.getResourceBundle().getString("textoCargar"));
    //   txtNombre.setText(propiedades.getResourceBundle().getString("textoNombre"));
    //  txtModelo.setText(propiedades.getResourceBundle().getString("textoModelo"));
    //  txtFechaIn.setText(propiedades.getResourceBundle().getString("textoFechaInicio"));
    //   txtFechaFin.setText(propiedades.getResourceBundle().getString("textoFechaFinal"));
    //   txtPlaca.setText(propiedades.getResourceBundle().getString("textoPlaca"));
    //  txtMarca.setText(propiedades.getResourceBundle().getString("textoMarca"));
    //  txtAlquiler.setText(propiedades.getResourceBundle().getString("textoRentaDia"));
    //  txtSillas.setText(propiedades.getResourceBundle().getString("textoSillas"));
    //  txtAutomatico.setText(propiedades.getResourceBundle().getString("textoAutomatico"));
    //  botonRegreso.setText(propiedades.getResourceBundle().getString("textoRegresar"));
    // botonAlquilar.setText(propiedades.getResourceBundle().getString("textoRentar"));
    //}
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
                    txtImagen.setText(vehiculoSeleccionado.toString());
                    Image imagen = new Image(vehiculoSeleccionado.getFoto());
                    imagenVehiculo.setImage(imagen);
                    LOGGER.log(Level.INFO, "Se cargaron las propiedades de los vehiculos para mostrarlos");
                } else {
                    LOGGER.log(Level.INFO, "Se intentaron cargar atributos sin selccionar vehiculo");
                }
            }else {
                LOGGER.log(Level.INFO, "No se han ingresado fechas validas");
            }
        }
    }
    public void alquilar(ActionEvent actionEvent)
    {
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
                    Metodo.buscarVehiculo(vehiculos.getSelectionModel().getSelectedItem());
                    Metodo.cargarRegistro(clienteSistema,vehiculos.getSelectionModel().getSelectedItem(), inicio,fin);
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
