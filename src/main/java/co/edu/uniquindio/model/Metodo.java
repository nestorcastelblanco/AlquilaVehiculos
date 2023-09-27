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

import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Metodo {
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    public static ArrayList<Vehiculos> vehiculos = new ArrayList<Vehiculos>();
    public static ArrayList<Cliente> registros = new ArrayList<Cliente>();
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
    }

    public static boolean verificarDatos (String usuario, String contraseña)
    {
        boolean state = false;
        for (Cliente c : clientes)
        {
            if(usuario.equals(c.getUsuario()) && contraseña.equals(c.getContrasena())) {
                state = true;
            }
        }
        return state;
    }
    public static boolean verificarCredenciales (String usuario, String contraseña)
    {
        boolean state = false;
        for (Cliente c : clientes)
        {
            if(usuario.equals(c.getUsuario()) && contraseña.equals(c.getContrasena()) || usuario.equals("admin") && contraseña.equals("123")) {
                state = true;
            }
        }
        return state;
    }
    public static void crearCliente ( String  cedula,String  nombre,String  teléfono, String email, String ciudad,String direccionResidencia, String usuario, String contraseña)
    {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setCiudad(ciudad);
        cliente.setEmail(email);
        cliente.setTelefono(teléfono);
        cliente.setDireccionResidencia(direccionResidencia);
        cliente.setUsuario(usuario);
        cliente.setContrasena(contraseña);
        clientes.add(cliente);
    }
    public static void llenarComboSillas (ComboBox<Integer> sillas, ObservableList<Integer> numeros)
    {
        sillas.setItems(numeros);
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
}
