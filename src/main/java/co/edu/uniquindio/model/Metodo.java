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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    private Metodo() {
        try {
            LOGGER.addHandler((new FileHandler("logs.xml", true)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Metodo getInstance() {
        if (metodo == null) {
            metodo = new Metodo();
        }
        return metodo;
    }

    public static void cargarDatos() {
        Cliente cliente = new Cliente();
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        cliente.setNombre("Nestor Castelblanco");
        cliente1.setNombre("Sebastian Agudelo");
        cliente2.setNombre("Hulbert Ferney");
        cliente.setCedula("1104697206");
        cliente1.setCedula("11046972");
        cliente2.setCedula("110412306");
        cliente.setCiudad("Armenia");
        cliente1.setCiudad("Armenia");
        cliente2.setCiudad("Armenia");
        cliente.setEmail("nestorf.castelblancod@gmail.com");
        cliente1.setEmail("sebastianagudelom");
        cliente2.setEmail("hulberta@uqvirtual.edu.co");
        cliente.setTelefono("3054194624");
        cliente1.setTelefono("30234533");
        cliente2.setTelefono("305324124");
        cliente.setDireccionResidencia("Calle 9N");
        cliente1.setDireccionResidencia("Calle 10N");
        cliente2.setDireccionResidencia("Calle 9N");
        cliente.setUsuario("123");
        cliente1.setUsuario("1234");
        cliente2.setUsuario("12345");
        cliente.setContrasena("123");
        cliente1.setContrasena("1234");
        cliente2.setContrasena("12345");
        clientes.add(cliente);
        clientes.add(cliente1);
        clientes.add(cliente2);

        Vehiculos vehiculo = new Vehiculos();
        Vehiculos vehiculo1 = new Vehiculos();
        Vehiculos vehiculo2 = new Vehiculos();
        vehiculo.setPlaca("FQK884");
        vehiculo1.setPlaca("KMP282");
        vehiculo2.setPlaca("FQK884");
        vehiculo.setMarca("CHEVROLET");
        vehiculo1.setMarca("RENAULT");
        vehiculo2.setMarca("CHEVROLET");
        vehiculo.setNombre("TRACKER");
        vehiculo1.setNombre("SANDERO");
        vehiculo2.setNombre("SPARK GT");
        vehiculo.setModelo("2019");
        vehiculo1.setModelo("2019");
        vehiculo2.setModelo("2020");
        vehiculo.setKilometraje("45000");
        vehiculo1.setKilometraje("45000");
        vehiculo2.setKilometraje("45000");
        vehiculo.setPrecioAlquilerDia(115000);
        vehiculo1.setPrecioAlquilerDia(120000);
        vehiculo2.setPrecioAlquilerDia(120000);
        vehiculo.setNumeroSillas("5");
        vehiculo1.setNumeroSillas("5");
        vehiculo2.setNumeroSillas("5");
        vehiculo.setAutomatico("SI");
        vehiculo1.setAutomatico("SI");
        vehiculo2.setAutomatico("SI");
        vehiculo.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\tracker.png");
        vehiculo1.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\sandero.png");
        vehiculo2.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\spark.png");
        vehiculos.add(vehiculo);
        vehiculos.add(vehiculo1);
        vehiculos.add(vehiculo2);

        try {
            escribirArchivoFormatterClientes("src/main/resources/clientesRegistrados.txt", clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escribirArchivoFormatterVehiculos("src/main/resources/vehiculos.txt", vehiculos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escribirArchivoFormatterAlquiler("src/main/resources/vehiculosAlquilados.txt", registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashSet<Vehiculos> listarVehiculosAlquilados(LocalDate fechaInicio, LocalDate fechaFinal) {
        HashSet<Vehiculos> vehiculosRegistrados = new HashSet<>();
        for (int i = 0; i < registros.size(); i++) {
            for (int j = 0; j < registros.get(i).getVehiculoAdquirido().size(); j++) {
                if (registros.get(i).getVehiculoAdquirido().get(j).getFechaInicio().isAfter(fechaInicio) || registros.get(i).getVehiculoAdquirido().get(j).getFechaInicio().isEqual(fechaInicio)
                        && registros.get(i).getVehiculoAdquirido().get(j).getFechaFin().isBefore(fechaFinal) || registros.get(i).getVehiculoAdquirido().get(j).getFechaFin().isEqual(fechaFinal)) {
                    registros.get(i).getVehiculoAdquirido().get(j).setCliente(registros.get(i));
                    vehiculosRegistrados.add(registros.get(i).getVehiculoAdquirido().get(j));
                }
            }
        }
        return vehiculosRegistrados;
    }

    public static float adquirirUtilidades(LocalDate fechaInicio, LocalDate fechaFinal) {
        float contadorUtilidades = 0;
        for (int i = 0; i < facturas.size(); i++) {
            if (facturas.get(i).getFechaInicio().isAfter(fechaInicio) || facturas.get(i).getFechaInicio().isEqual(fechaInicio)
                    && facturas.get(i).getFechaFin().isBefore(fechaFinal) || facturas.get(i).getFechaFin().isEqual(fechaFinal)) {
                contadorUtilidades += facturas.get(i).getValorTotal();
            }
        }
        return contadorUtilidades;
    }

    public static LocalDate verificarCampos(String diaI, String mesI, String anioI) {
        LocalDate fecha = null;
        int d = Integer.parseInt(diaI);
        int m = Integer.parseInt(mesI);
        int yr = Integer.parseInt(anioI);
        if (d < 1 || d > 31 && m < 1 || m > 12) {
            fecha = null;
        } else {
            fecha = LocalDate.of(yr, m, d);
        }
        return fecha;
    }

    public static ArrayList<Vehiculos> vehiculosDisponibles(LocalDate inicio, LocalDate fin) {
        ArrayList<Vehiculos> vehiculosDisponibles = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (verificarFechasAlquileres(inicio, fin, vehiculos.get(i))) {
                vehiculosDisponibles.add(vehiculos.get(i));
            }
        }
        return vehiculosDisponibles;
    }

    public static boolean verificarFechasAlquileres(LocalDate inicio, LocalDate fin, Vehiculos vehiculo) {
        boolean state = true;
        for (int i = 0; i < vehiculo.getFechasAlquiladas().size(); i++) {
            for (int j = 0; j < vehiculo.getFechasAlquiladas().get(i).size(); j++) {
                if (inicio.isEqual(vehiculo.getFechasAlquiladas().get(i).get(j)) || inicio.isBefore(vehiculo.getFechasAlquiladas().get(i).get(j)) && inicio.isAfter(vehiculo.getFechasAlquiladas().get(i).get(j))) {
                    state = false;
                }
                if (fin.isEqual(vehiculo.getFechasAlquiladas().get(i).get(j)) || fin.isBefore(vehiculo.getFechasAlquiladas().get(i).get(j)) && fin.isAfter(vehiculo.getFechasAlquiladas().get(i).get(j))) {
                    state = false;
                }
            }
        }
        return state;
    }
    public static void recibirClienteSesion(Cliente cliente) {
        clienteSesion = cliente;
    }
    public static boolean verificarDatos(String usuario, String contrasena) {
        boolean state = false;
        for (int i = 0; i<clientes.size() ; i++)
        {
            if(clientes.get(i).getUsuario().equals(usuario) && clientes.get(i).getContrasena().equals(contrasena))
            {
                state = true;
            }
        }
        return state;
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
        try {
            escribirArchivoFormatterClientes("src/main/resources/clientesRegistrados.txt", clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            escribirArchivoFormatterVehiculos("src/main/resources/vehiculos.txt", vehiculos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void mostrarVehiculos()
    {
        System.out.println(vehiculos);
    }
    public static void llenarComboSillas (ComboBox<String> sillas, ObservableList<String> numeros)
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
    public static void
    cargarRegistro(Cliente clienteSistema, Vehiculos selectedItem,LocalDate fechaInicio, LocalDate fechaFinal) {
        Vehiculos vehiculo = new Vehiculos();
        vehiculo = selectedItem;
        long dias = fechaInicio.until(fechaFinal, ChronoUnit.DAYS);
        vehiculo.setValorTotal((float) dias * vehiculo.getPrecioAlquilerDia());
        vehiculo.setFechaInicio(fechaInicio);
        vehiculo.setFechaFin(fechaFinal);
        clienteSistema.setVehiculoAdquirido(selectedItem);
        registros.add(clienteSistema);
        ArrayList<LocalDate> arrayFechas = new ArrayList<>();
        arrayFechas.add(fechaInicio);
        arrayFechas.add(fechaFinal);
        selectedItem.setFechasAlquiladas(arrayFechas);
        try {
            Metodo.escribirArchivoFormatterAlquiler("src/main/resources/vehiculosAlquilados.txt",registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String msg = "Factura de prestacion de servicios y contratacion\n\n" + clienteSistema.getFactura() + "\n" +"Vehiculo Seleccionado  " + selectedItem.toString() + "\n" + "Valor alquiler Diario " + vehiculo.getPrecioAlquilerDia() + "\nCantidad de dias Reservados  " + dias + "\nFecha Inicio " + fechaInicio + "\nFecha Final " + fechaFinal + "\nValor Total: " + vehiculo.getValorTotal();
        JOptionPane.showMessageDialog(null, msg, "Factura Prestacion de Servicios", 1);
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

    /**
     * Permite leer un archivo desde una ruta específica mediante Scanner
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoScanner(String ruta) throws IOException{
        ArrayList<String> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File(ruta));
        while(sc.hasNextLine()) {
            lista.add(sc.nextLine());
        }
        sc.close();
        return lista;
    }
    /**
     * Permite leer un archivo desde una ruta específica mediante BufferedReader
     * @param ruta Ruta a leer
     * @return Lista de String por cada línea del archivo
     * @throws IOException
     */
    public static ArrayList<String> leerArchivoBufferedReader(String ruta) throws IOException{

        ArrayList<String> lista = new ArrayList<>();
        FileReader fr = new FileReader(ruta);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        while( ( linea = br.readLine() )!=null ) {
            lista.add(linea);
        }

        br.close();
        fr.close();

        return lista;
    }
    public static void escribirArchivoFormatterClientes(String ruta, ArrayList<Cliente> cliente) throws IOException{
        cliente.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(Cliente s : cliente){
            ft.format(s.getCedula()+";" +s.getNombre()+";"+s.getTelefono()+";"+ s.getEmail()+";"+s.getCiudad()+";"+s.getDireccionResidencia()+";"+s.getUsuario()+";"+s.getContrasena()+";"+"%n");
        }
        ft.close();
    }
    public static void escribirArchivoFormatterVehiculos(String ruta, ArrayList<Vehiculos> vehiculo) throws IOException{
        vehiculo.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(Vehiculos s : vehiculo){
            ft.format(s.getPlaca()+";"+s.getNombre()+";"+s.getMarca()+";"+s.getModelo()+";"+s.getFoto()+";"+s.getKilometraje()+";"+s.getNumeroSillas()+";"+s.getAutomatico()+";");
            for (int i = 0; i<s.getFechasAlquiladas().size();i++)
            {
                ft.format(s.getFechasAlquiladas().get(i) +",");
            }
            ft.format(";"+s.getValorTotal()+";"+s.getContPrestamos()+";"+"%n");
        }
        ft.close();
    }
    public static void escribirArchivoFormatterAlquiler(String ruta, ArrayList<Cliente> cliente) throws IOException{
        cliente.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(Cliente s : cliente){
            ft.format(s.getCedula()+";" +s.getNombre()+";"+s.getTelefono()+";"+ s.getEmail()+";"+s.getCiudad()+";"+s.getDireccionResidencia()+";"+s.getUsuario()+";"+s.getContrasena()+";");
            for (int i = 0; i<s.getVehiculoAdquirido().size();i++)
            {
                ft.format(s.getVehiculoAdquirido().get(i).getPlaca()+","+s.getVehiculoAdquirido().get(i).getNombre()+","+s.getVehiculoAdquirido().get(i).getMarca()+","+s.getVehiculoAdquirido().get(i).getModelo()+","+s.getVehiculoAdquirido().get(i).getFoto()+","+s.getVehiculoAdquirido().get(i).getKilometraje()+","+s.getVehiculoAdquirido().get(i).getNumeroSillas()+","+s.getVehiculoAdquirido().get(i).getAutomatico());
            }
            for (int i = 0; i<s.getVehiculoAdquirido().get(i).getFechasAlquiladas().size();i++)
            {
                ft.format(s.getVehiculoAdquirido().get(i).getFechasAlquiladas().get(i) +"'");
            }
        }
        ft.close();
    }

    /**
     * Escribe datos en un archivo de texto
     * @param ruta ruta Ruta donde se va a crear el archivo
     * @param lista Información a guardar en el archivo
     * @param concat True si se concatena los nuevos datos sin sobreescibir todo el archivo
     * @throws IOException
     */
    public static void escribirArchivoBufferedWriter(String ruta, ArrayList<String> lista, boolean concat) throws IOException{

        FileWriter fw = new FileWriter(ruta, concat);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String string : lista) {
            bw.write(string);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }

    /**
     * Serializa un objeto en disco
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws IOException
     */
    public static void serializarObjeto(String ruta, Object objeto, boolean concat) throws IOException{
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(ruta, concat));
        os.writeObject(objeto);
        os.close();
    }

    /**
     * Deserializa un objeto que está guardado en disco
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public static Object deserializarObjeto(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(ruta));
        Object objeto = is.readObject();
        is.close();

        return objeto;
    }

    /**
     * Serializa un objeto en un archivo en formato XML
     * @param ruta Ruta del archivo donde se va a serializar el objeto
     * @param objeto Objeto a serializar
     * @throws FileNotFoundException
     */
    public static void serializarObjetoXML(String ruta, Object objeto) throws FileNotFoundException {
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(ruta));
        encoder.writeObject(objeto);
        encoder.close();
    }

    /**
     * Deserializa un objeto desde un archivo XML
     * @param ruta Ruta del archivo a deserializar
     * @return Objeto deserializado
     * @throws IOException
     */
    public static Object deserializarObjetoXML(String ruta) throws IOException{
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(ruta));
        Object objeto = decoder.readObject();
        decoder.close();
        return objeto;
    }
}
