package co.edu.uniquindio.model;

import co.edu.uniquindio.controller.IngresoController;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import lombok.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
public class Metodo {
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Vehiculos> vehiculos = new ArrayList<>();
    public static ArrayList<Cliente> registros = new ArrayList<>();
    public static ArrayList<Facturas> facturas = new ArrayList<>();
    public static Cliente clienteSesion = new Cliente();
    private static final Logger LOGGER = Logger.getLogger(IngresoController.class.getName());
    private static Metodo metodo;
    private Metodo(){
        try {
            LOGGER.addHandler((new FileHandler("logs.xml",true)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Metodo getInstance()
    {
        if(metodo == null)
        {
            metodo = new Metodo();
        }
        return metodo;
    }
    public static void cargarDatos () {
        Cliente cliente = new Cliente();                         Cliente cliente1 = new Cliente();               Cliente cliente2 = new Cliente();
        cliente.setNombre("Nestor Castelblanco");                cliente1.setNombre("Sebastian Agudelo");        cliente2.setNombre("Hulbert Ferney");
        cliente.setCedula("1104697206");                         cliente1.setCedula("11046972");                 cliente2.setCedula("110412306");
        cliente.setCiudad("Armenia");                            cliente1.setCiudad("Armenia");                  cliente2.setCiudad("Armenia");
        cliente.setEmail("nestorf.castelblancod@gmail.com");     cliente1.setEmail("sebastianagudelom");         cliente2.setEmail("hulberta@uqvirtual.edu.co");
        cliente.setTelefono("3054194624");                       cliente1.setTelefono("30234533");               cliente2.setTelefono("305324124");
        cliente.setDireccionResidencia("Calle 9N");              cliente1.setDireccionResidencia("Calle 10N");   cliente2.setDireccionResidencia("Calle 9N");
        cliente.setUsuario("123");                               cliente1.setUsuario("1234");                    cliente2.setUsuario("12345");
        cliente.setContrasena("123");                            cliente1.setContrasena("1234");                 cliente2.setContrasena("12345");
        clientes.add(cliente);                                   clientes.add(cliente1);                         clientes.add(cliente2);

        Vehiculos vehiculo = new Vehiculos();                    Vehiculos vehiculo1 = new Vehiculos();          Vehiculos vehiculo2 = new Vehiculos();
        vehiculo.setPlaca("FQK884");                             vehiculo1.setPlaca("KMP282");                   vehiculo2.setPlaca("FQK884");
        vehiculo.setMarca("CHEVROLET");                          vehiculo1.setMarca("RENAULT");                  vehiculo2.setMarca("CHEVROLET");
        vehiculo.setNombre("TRACKER");                           vehiculo1.setNombre("SANDERO");                 vehiculo2.setNombre("SPARK GT");
        vehiculo.setModelo("2019");                              vehiculo1.setModelo("2019");                    vehiculo2.setModelo("2020");
        vehiculo.setKilometraje("45000");                        vehiculo1.setKilometraje("45000");              vehiculo2.setKilometraje("45000");
        vehiculo.setPrecioAlquilerDia(115000);                   vehiculo1.setPrecioAlquilerDia(120000);         vehiculo2.setPrecioAlquilerDia(120000);
        vehiculo.setNumeroSillas("5");                           vehiculo1.setNumeroSillas("5");                 vehiculo2.setNumeroSillas("5");
        vehiculo.setAutomatico("SI");                            vehiculo1.setAutomatico("SI");                  vehiculo2.setAutomatico("SI");
        vehiculo.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\tracker.png");
        vehiculo1.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\sandero.png");
        vehiculo2.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\spark.png");
        vehiculos.add(vehiculo);                                 vehiculos.add(vehiculo1);                       vehiculos.add(vehiculo2);
    }
    public static ArrayList<Vehiculos> listarVehiculosAlquilados(LocalDate fechaInicio, LocalDate fechaFinal)
    {
        ArrayList<Vehiculos> vehiculosRegistrados = new ArrayList<>();
        for (int i = 0 ; i<registros.size(); i++)
        {
            for (int j = 0; j<registros.get(i).getVehiculoAdquirido().size() ; j++)
            {
                if (registros.get(i).getVehiculoAdquirido().get(j).getFechaInicio().isAfter(fechaInicio) || registros.get(i).getVehiculoAdquirido().get(j).getFechaInicio().isEqual(fechaInicio)
                 && registros.get(i).getVehiculoAdquirido().get(j).getFechaFin().isBefore(fechaFinal) || registros.get(i).getVehiculoAdquirido().get(j).getFechaFin().isEqual(fechaFinal))
                {
                    registros.get(i).getVehiculoAdquirido().get(j).setCliente(registros.get(i));
                    vehiculosRegistrados.add(registros.get(i).getVehiculoAdquirido().get(j));
                }
            }
        }
        return vehiculosRegistrados;
    }
    public static float adquirirUtilidades (LocalDate fechaInicio, LocalDate fechaFinal)
    {
        float contadorUtilidades = 0;
        for (int i = 0 ; i<facturas.size(); i++)
        {
            if (facturas.get(i).getFechaInicio().isAfter(fechaInicio) || facturas.get(i).getFechaInicio().isEqual(fechaInicio)
                && facturas.get(i).getFechaFin().isBefore(fechaFinal) || facturas.get(i).getFechaFin().isEqual(fechaFinal))
            {
                contadorUtilidades+= facturas.get(i).getValorTotal();
            }
        }
        return contadorUtilidades;
    }
    public static LocalDate verificarCampos(String diaI, String mesI, String anioI)
    {
        LocalDate fecha = null;
        int d = Integer.parseInt(diaI);
        int m = Integer.parseInt(mesI);
        int yr = Integer.parseInt(anioI);
        if (d<1 || d>31 && m<1 || m>12)
        {
            fecha = null;
        }else
        {
            fecha = LocalDate.of(yr,m,d);
        }
        return fecha;
    }
    public static void recibirClienteSesion(Cliente cliente)
    {
        clienteSesion = cliente;
    }
    public static boolean verificarDatos (String usuario, String contrasena)
    {
        boolean state = false;
        for (Cliente c : clientes)
        {
            if(usuario.equals(c.getUsuario()) && contrasena.equals(c.getContrasena())) {
                clienteSesion = c;
                state = true;
            }
        }
        return state;
    }
    public static Cliente retornarCliente (String usuario, String contrasena)
    {
        Cliente c1 = new Cliente();
        for (Cliente c : clientes)
        {
            if(usuario.equals(c.getUsuario()) && contrasena.equals(c.getContrasena())) {
                c1 = c;
            }
        }
        return c1;
    }
    public static boolean verificarCredenciales (String usuario, String contrasena)
    {
        boolean state = false;
        for (Cliente c : clientes)
        {
            if(usuario.equals(c.getUsuario()) && contrasena.equals(c.getContrasena()) || usuario.equals("admin") && contrasena.equals("123")) {
                state = true;
                break;
            }
        }
        return state;
    }
    public static void crearCliente (String  cedula, String  nombre, String  telefono, String email, String ciudad, String direccion, String usuario, String contrasena)
    {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setCiudad(ciudad);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setDireccionResidencia(direccion);
        cliente.setUsuario(usuario);
        cliente.setContrasena(contrasena);
        clientes.add(cliente);
    }
    public static void crearVehiculo (String  placa, String marca, String nombre, String modelo, String km, String alquiler, String sillas, String automatico, String foto)
    {

        Vehiculos vehiculo = new Vehiculos();
        vehiculo.setPlaca(placa.toUpperCase());
        vehiculo.setMarca(marca.toUpperCase());
        vehiculo.setNombre(nombre.toUpperCase());
        vehiculo.setModelo(modelo.toUpperCase());
        vehiculo.setKilometraje(km.toUpperCase());
        vehiculo.setPrecioAlquilerDia(Float.parseFloat(alquiler));
        vehiculo.setNumeroSillas(sillas.toUpperCase());
        vehiculo.setAutomatico(automatico.toUpperCase());
        vehiculo.setFoto(foto);
        vehiculos.add(vehiculo);
    }
    public static void mostrarVehiculos()
    {
        System.out.println(vehiculos);
    }
    public static void llenarComboSillas (ComboBox<Integer> sillas, ObservableList<Integer> numeros)
    {
        sillas.setItems(numeros);
    }
    public static void llenarVehiculos (ComboBox<Vehiculos> vehiculos, ObservableList<Vehiculos> vh)
    {
        vehiculos.setItems(vh);
    }
    public static void llenarComboAuto (ComboBox<String> auto , ObservableList<String> pal)
    {
       auto.setItems(pal);
    }
    public static void loadStage(String url, Event evento, String mensaje) {
        try {
            LOGGER.log(Level.INFO, mensaje);
            ((Node) (evento.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(Metodo.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Travel Agency");
            newStage.show();
        } catch (Exception ignored) {
            LOGGER.log(Level.SEVERE, ignored.getMessage());
        }
    }
    public static ArrayList<Vehiculos> enviarVehiculos()
    {
        System.out.print(vehiculos);
        return vehiculos;
    }
    public static Cliente enviarCliente()
    {
        return clienteSesion;
    }
    public static void cargarRegistro(Cliente clienteSistema, Vehiculos selectedItem,LocalDate fechaInicio, LocalDate fechaFinal) {
        Vehiculos vehiculo = new Vehiculos();
        vehiculo = selectedItem;
        long dias = fechaInicio.until(fechaFinal, ChronoUnit.DAYS);
        vehiculo.setValorTotal((float) dias * vehiculo.getPrecioAlquilerDia());
        vehiculo.setFechaInicio(fechaInicio);
        vehiculo.setFechaFin(fechaFinal);
        clienteSistema.setVehiculoAdquirido(selectedItem);
        registros.add(clienteSistema);
        String msg = "Factura de prestacion de servicios y contratacion\n\n" + clienteSistema.getFactura() + "\n" +"Vehiculo Seleccionado  " + selectedItem.toString() + "\n" + "Valor alquiler Diario " + vehiculo.getPrecioAlquilerDia() + "\nCantidad de dias Reservados  " + dias + "\nFecha Inicio " + fechaInicio + "\nFecha Final " + fechaFinal + "\nValor Total: " + vehiculo.getValorTotal();
        JOptionPane.showMessageDialog(null, msg, "Factura Prestacion de Servicios", 1);
        System.out.println("cantidad de dias = " + dias);
        System.out.println("fecha inicio = " + fechaInicio);
        System.out.println("fecha final = " + fechaFinal);
        System.out.println("valor de dia =" +vehiculo.getPrecioAlquilerDia()+ "Total" + vehiculo.getValorTotal());
    }
    public static void cargarFactura(Cliente clienteSistema,LocalDate fechaInicio, LocalDate fechaFinal, float valor) {
        Facturas factura = new Facturas();
        long dias = fechaInicio.until(fechaFinal, ChronoUnit.DAYS);
        factura.setValorTotal((float) dias * valor);
        factura.setFechaInicio(fechaInicio);
        factura.setFechaFin(fechaFinal);
        factura.setCliente(clienteSistema);
        facturas.add(factura);
    }
    public static void imprimirRegistros()
    {
        System.out.println(registros);
    }
    public static void buscarVehiculo(Vehiculos item) {
        for(int i = 0; i<vehiculos.size();i++ )
        {
            if ( vehiculos.get(i).equals(item))
            {
                vehiculos.get(i).setContPrestamos(+1);
            }
        }
    }
    public static Vehiculos buscarVehiculoAlquiler() {
        Vehiculos vehiculo = new Vehiculos();
        int mayor  = 0;
        for (int i = 0; i<vehiculos.size() ; i++)
        {
            System.out.println("Contador de: " + vehiculos.get(i).toString() + "  " + vehiculos.get(i).getContPrestamos());
            if (vehiculos.get(i).getContPrestamos() > mayor)
            {
                vehiculo = vehiculos.get(i);
                mayor = vehiculo.getContPrestamos();
                System.out.println("Vehiculo entra " + vehiculo + "  Prestamos"+ mayor);
            }
        }
        return vehiculo;
    }
}
