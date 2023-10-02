package co.edu.uniquindio.model;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Metodo {
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Vehiculos> vehiculos = new ArrayList<>();
    public static ArrayList<Cliente> registros = new ArrayList<>();
    public static Cliente clienteSesion = new Cliente();
    public static void cargarDatos ()
    {
        Cliente cliente = new Cliente();                     Cliente cliente1 = new Cliente();                   Cliente cliente2 = new Cliente();
        cliente.setNombre("Nestor Castelblanco");            cliente1.setNombre("Sebastian Agudelo");            cliente2.setNombre("Hulbert Ferney");
        cliente.setCedula("1104697206");                     cliente1.setCedula("11046972");                     cliente2.setCedula("110412306");
        cliente.setCiudad("Armenia");                        cliente1.setCiudad("Armenia");                      cliente2.setCiudad("Armenia");
        cliente.setEmail("nestorf.castelblancod@gmail.com"); cliente1.setEmail("sebastianagudelom");             cliente2.setEmail("hulberta@uqvirtual.edu.co");
        cliente.setTelefono("3054194624");                   cliente1.setTelefono("30234533");                   cliente2.setTelefono("305324124");
        cliente.setDireccionResidencia("Calle 9N");          cliente1.setDireccionResidencia("Calle 10N");       cliente2.setDireccionResidencia("Calle 9N");
        cliente.setUsuario("123");                           cliente1.setUsuario("1234");                        cliente2.setUsuario("12345");
        cliente.setContrasena("123");                        cliente1.setContrasena("1234");                     cliente2.setContrasena("12345");
        clientes.add(cliente);                               clientes.add(cliente1);                             clientes.add(cliente2);

        Vehiculos vehiculo = new Vehiculos();                Vehiculos vehiculo1 = new Vehiculos();               Vehiculos vehiculo2 = new Vehiculos();
        vehiculo.setPlaca("FQK884");                         vehiculo1.setPlaca("KMP282");                        vehiculo2.setPlaca("FQK884");
        vehiculo.setMarca("CHEVROLET");                      vehiculo1.setMarca("RENAULT");                       vehiculo2.setMarca("CHEVROLET");
        vehiculo.setNombre("TRACKER");                       vehiculo1.setNombre("SANDERO");                      vehiculo2.setNombre("SPARK GT");
        vehiculo.setModelo("2019");                          vehiculo1.setModelo("2019");                         vehiculo2.setModelo("2020");
        vehiculo.setKilometraje("45000");                    vehiculo1.setKilometraje("45000");                   vehiculo2.setKilometraje("45000");
        vehiculo.setPrecioAlquilerDia("115000");             vehiculo1.setPrecioAlquilerDia("120000");            vehiculo2.setPrecioAlquilerDia("110000");
        vehiculo.setNumeroSillas("5");                       vehiculo1.setNumeroSillas("5");                      vehiculo2.setNumeroSillas("5");
        vehiculo.setAutomatico("SI");                        vehiculo1.setAutomatico("SI");                       vehiculo2.setAutomatico("SI");
        vehiculo.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\tracker.png");vehiculo1.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\sandero.png");vehiculo2.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\spark.png");
        vehiculos.add(vehiculo);                             vehiculos.add(vehiculo1);                            vehiculos.add(vehiculo2);
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
                state = true;
                break;
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
        vehiculo.setPrecioAlquilerDia(alquiler.toUpperCase());
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
    public static void loadStage(String url, Event evento) {

        try {
            ((Node) (evento.getSource())).getScene().getWindow().hide();

            Parent root = FXMLLoader.load(Objects.requireNonNull(Metodo.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Travel Agency");
            newStage.show();

        } catch (Exception ignored) {

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
    public static void cargarRegistro(Cliente clienteSistema, Vehiculos selectedItem) {
        clienteSistema.setVehiculoAdquirido(selectedItem);
        registros.add(clienteSistema);
    }
    public static void imprimirRegistros()
    {
        System.out.println(registros);
    }

    public static void verificarFechas(LocalDate fechaFin, LocalDate fechaInicio) throws Exception {
        if(fechaInicio.isAfter(fechaFin))
        {
            throw new Exception("La fecha de inicio, no puedo ser después de la fecha final");
        }
        // long dias = fechaInicio.until(fechaFin.isAfter(fechaInicio) != 1);
    }
}
