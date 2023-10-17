package co.edu.uniquindio.model;

import co.edu.uniquindio.controller.IngresoController;
import co.edu.uniquindio.controller.RegistroPrestamosController;
import co.edu.uniquindio.controller.SeleccionVehiculoController;
import co.edu.uniquindio.utils.ArchivoUtils;
import com.sun.javafx.scene.shape.ArcHelper;
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
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static ArrayList<Registros> registros = new ArrayList<>();
    public static ArrayList<Facturas> facturas = new ArrayList<>();
    public static Cliente clienteSesion = new Cliente();
    public static String RUTA_CLIENTES = "src/main/resources/clientesRegistrados.txt";
    public static String RUTA_VEHICULOS = "src/main/resources/vehiculos.ser";
    public static String RUTA_ALQUILERES = "src/main/resources/vehiculosAlquilados.txt";
    public static String RUTA_FACTURAS = "src/main/resources/facturas.txt";
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
        leerClientes();
        leerAlquileres();
        leerVehiculos();
        leerFacturas();
        mostrarArrays();
        /**
        Vehiculos vehiculo = new Vehiculos();        Vehiculos vehiculo1 = new Vehiculos();        Vehiculos vehiculo2 = new Vehiculos();
        vehiculo.setPlaca("FQK884");        vehiculo1.setPlaca("KMP282");        vehiculo2.setPlaca("FQK898");
        vehiculo.setMarca("CHEVROLET");        vehiculo1.setMarca("RENAULT");        vehiculo2.setMarca("CHEVROLET");
        vehiculo.setNombre("TRACKER");        vehiculo1.setNombre("SANDERO");        vehiculo2.setNombre("SPARK GT");
        vehiculo.setModelo("2019");        vehiculo1.setModelo("2019");        vehiculo2.setModelo("2020");
        vehiculo.setKilometraje("45000");        vehiculo1.setKilometraje("45000");        vehiculo2.setKilometraje("45000");
        vehiculo.setPrecioAlquilerDia(115000);        vehiculo1.setPrecioAlquilerDia(120000);        vehiculo2.setPrecioAlquilerDia(120000);
        vehiculo.setNumeroSillas("5");        vehiculo1.setNumeroSillas("5");        vehiculo2.setNumeroSillas("5");
        vehiculo.setAutomatico("SI");        vehiculo1.setAutomatico("SI");        vehiculo2.setAutomatico("SI");
        vehiculo.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\tracker.png");        //vehiculo1.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\sandero.png");        //vehiculo2.setFoto("C:\\Users\\nesto\\Programacion III\\AlquilaVehiculos\\src\\main\\resources\\imagenes\\imagenesVehiculos\\spark.png");
        //vehiculos.add(vehiculo);        //vehiculos.add(vehiculo1);        //vehiculos.add(vehiculo2);
        *///
        try {
            escribirArchivoFormatterClientes("src/main/resources/clientesRegistrados.txt", clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escribirArchivoFormatterVehiculos("src/main/resources/vehiculos.ser",vehiculos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escribirArchivoFormatterAlquiler("src/main/resources/vehiculosAlquilados.txt", registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void mostrarArrays() {
        System.out.println("Clientes: ");
        for (int i = 0; i<clientes.size() ;i++)
        {
            System.out.println(clientes.get(i).toString());
        }
        System.out.println("Registros: ");
        for (int i = 0; i<registros.size() ;i++)
        {
            System.out.println(registros.get(i).toString());
        }
        System.out.println("Vehiculos: ");
        for (int i = 0; i<vehiculos.size() ;i++)
        {
            System.out.println(vehiculos.get(i).toString());
        }
        System.out.println("Facturas: ");
        for (int i = 0; i<facturas.size() ;i++)
        {
            System.out.println(facturas.get(i).toString());
        }
    }
    private static void leerVehiculos() {
        System.out.println("-- 1 --");
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(RUTA_VEHICULOS))) {
            System.out.println("-- 2 --");
            ArrayList<Vehiculos> vehiculo = (ArrayList<Vehiculos>) entrada.readObject();
            System.out.println("Vehículos deserializados correctamente.");
            // Iterar y trabajar con los objetos Vehiculo deserializados
            for (Vehiculos vehicul : vehiculo) {
                vehiculos.add(vehicul);
                System.out.println("Marca: " + vehicul.getMarca() + ", Nombre: " + vehicul.getNombre());
                // Trabajar con las fechas del vehículo si es necesario
                ArrayList<ArrayList<LocalDate>> fechas = vehicul.getFechasAlquiladas();
                // Haz lo que quieras con las fechas...
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void leerClientes() {
        try{
            ArrayList<String> lineas = leerArchivoScanner("src/main/resources/clientesRegistrados.txt");
            for(String linea : lineas){
                String[] valores = linea.split(";");
                clientes.add( Cliente.builder()
                        .cedula(valores[0])
                        .nombre(valores[1])
                        .telefono(valores[2])
                        .email(valores[3])
                        .ciudad(valores[4])
                        .direccionResidencia(valores[5])
                        .usuario(valores[6])
                        .contrasena(valores[7])
                        .build());
            }
        }catch (IOException e){
            LOGGER.severe(e.getMessage());
        }
    }
    private static void leerFacturas() {
        try{
            ArrayList<String> lineas = leerArchivoScanner("src/main/resources/facturas.txt");
            for(String linea : lineas){
                String[] valores = linea.split(";");
                facturas.add(Facturas.builder()
                        .cliente(Cliente.builder()
                        .cedula(valores[0])
                        .nombre(valores[1])
                        .telefono(valores[2])
                        .email(valores[3])
                        .ciudad(valores[4])
                        .direccionResidencia(valores[5])
                        .usuario(valores[6])
                        .contrasena(valores[7])
                        .build())
                        .fechaInicio(LocalDate.parse(valores[8]))
                        .fechaFin(LocalDate.parse(valores[9]))
                        .valorTotal(Float.parseFloat(valores[10]))
                        .build());
            }
        }catch (IOException e){
            LOGGER.severe(e.getMessage());
        }
    }
    public static void escribirArchivoFormatterFacturas(String ruta, ArrayList<Facturas> facturas) throws IOException{
        facturas.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(int i = 0 ; i<facturas.size() ; i++)
        {
            ft.format(facturas.get(i).getCliente().getCedula()+";" +facturas.get(i).getCliente().getNombre()+";"+facturas.get(i).getCliente().getTelefono()+";"+ facturas.get(i).getCliente().getEmail()+";"+facturas.get(i).getCliente().getCiudad()+";"+facturas.get(i).getCliente().getDireccionResidencia()+";"+facturas.get(i).getCliente().getUsuario()+";"+facturas.get(i).getCliente().getContrasena()+";"+ facturas.get(i).getFechaInicio()+";"+facturas.get(i).getFechaFin()+";"+facturas.get(i).getValorTotal()+"%n");
        }
        ft.close();
    }
    private static void leerAlquileres(){
        try{
            ArrayList<String> lineas = leerArchivoScanner("src/main/resources/vehiculosAlquilados.txt");
            for(String linea : lineas){
                String[] valores = linea.split(";");
                registros.add( Registros.builder()
                        .cliente(Cliente.builder()
                                .cedula(valores[0])
                                .nombre(valores[1])
                                .telefono(valores[2])
                                .email(valores[3])
                                .ciudad(valores[4])
                                .direccionResidencia(valores[5])
                                .usuario(valores[6])
                                .contrasena(valores[7])
                                .build())
                        .vehiculo(Vehiculos.builder()
                                .placa(valores[8])
                                .nombre(valores[9])
                                .marca(valores[10])
                                .modelo(valores[11])
                                .foto(valores[12])
                                .kilometraje(valores[13])
                                .numeroSillas(valores[14])
                                .automatico(valores[15])
                                .build())
                        .fechaInicio(LocalDate.parse(valores[16]))
                        .fechafin(LocalDate.parse(valores[17])).build());
            }
        }catch (IOException e){
            LOGGER.severe(e.getMessage());
        }
    }
    public static HashSet<Registros> listarVehiculosAlquilados(LocalDate fechaInicio, LocalDate fechaFinal) {
        HashSet<Registros> vehiculosRegistrados = new HashSet<>();
        for (int i = 0; i < registros.size(); i++)
        {
            if (registros.get(i).getFechaInicio().isAfter(fechaInicio) || registros.get(i).getFechaInicio().isEqual(fechaInicio) && registros.get(i).getFechafin().isBefore(fechaFinal) || registros.get(i).getFechafin().isEqual(fechaFinal))
                {
                    vehiculosRegistrados.add(registros.get(i));
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
                Metodo.recibirClienteSesion(clientes.get(i));
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
            escribirArchivoFormatterVehiculos(RUTA_VEHICULOS, vehiculos);
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
    public static void llenarVehiculosRegistros (ComboBox<Registros> vehiculos, ObservableList<Registros> vh)
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
    public static void cargarRegistro(Cliente clienteSistema, Vehiculos selectedItem,LocalDate fechaInicio, LocalDate fechaFinal,String msg,String msg1,String msg2,String msg3,String msg4,String msg5,String msg6) {
        Vehiculos vehiculo = new Vehiculos();
        vehiculo = selectedItem;
        long dias = fechaInicio.until(fechaFinal, ChronoUnit.DAYS);
        vehiculo.setValorTotal((float) dias * vehiculo.getPrecioAlquilerDia());
        vehiculo.setFechaInicio(fechaInicio);
        vehiculo.setFechaFin(fechaFinal);
        clienteSistema.setVehiculoAdquirido(selectedItem);
        Registros registro = Registros.builder()
                .cliente(clienteSistema)
                .vehiculo(selectedItem)
                .fechaInicio(fechaInicio)
                .fechafin(fechaFinal).build();
        registros.add(registro);
        ArrayList<LocalDate> arrayFechas = new ArrayList<>();
        arrayFechas.add(fechaInicio);
        arrayFechas.add(fechaFinal);
        selectedItem.setFechasAlquiladas(arrayFechas);
        try {
            Metodo.escribirArchivoFormatterAlquiler("src/main/resources/vehiculosAlquilados.txt",registros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mss = msg + "\n\n" + clienteSistema.getFactura() + "\n" +msg1 +"  " + selectedItem.toString() + "\n" + msg2+ "  " + vehiculo.getPrecioAlquilerDia() + "\n" + msg3 +" " + dias + "\n" + msg4 + " " + fechaInicio + "\n" + msg5 + " " + fechaFinal + "\n" + msg6 + " " + vehiculo.getValorTotal();
        JOptionPane.showMessageDialog(null, mss, msg, 1);
    }
    public static void cargarFactura(Cliente clienteSistema,LocalDate fechaInicio, LocalDate fechaFinal, float valor) throws IOException {
        long dias = fechaInicio.until(fechaFinal, ChronoUnit.DAYS);
        Facturas factura = Facturas.builder()
                .cliente(clienteSistema)
                .fechaFin(fechaFinal)
                .fechaInicio(fechaInicio)
                .valorTotal((float) dias * valor)
                .build();
        facturas.add(factura);
        escribirArchivoFormatterFacturas("src/main/resources/facturas.txt",facturas);
    }
    public static void imprimirRegistros()
    {
        System.out.println(registros);
    }
    public static void buscarVehiculo(Vehiculos item,LocalDate inicio, LocalDate fin) throws IOException {
        ArrayList<LocalDate> fechas = new ArrayList<>();
        fechas.add(inicio);
        fechas.add(fin);
        for(int i = 0; i<vehiculos.size();i++ )
        {
            if ( vehiculos.get(i).equals(item))
            {
                vehiculos.get(i).setContPrestamos(+1);
                vehiculos.get(i).getFechasAlquiladas().add(fechas);
                System.out.println(vehiculos.get(i).getFechasAlquiladas().toString());

            }
        }
        escribirArchivoFormatterVehiculos("src/main/resources/vehiculos.ser",vehiculos);
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
    public static void escribirArchivoFormatterClientes(String ruta, ArrayList<Cliente> cliente) throws IOException{
        cliente.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(Cliente s : cliente){
            ft.format(s.getCedula()+";" +s.getNombre()+";"+s.getTelefono()+";"+ s.getEmail()+";"+s.getCiudad()+";"+s.getDireccionResidencia()+";"+s.getUsuario()+";"+s.getContrasena()+";"+"%n");
        }
        ft.close();
    }
    public static void escribirArchivoFormatterVehiculos(String ruta, ArrayList<Vehiculos> vehiculos) throws IOException{
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
            salida.writeObject(vehiculos);
            System.out.println("Vehículos serializados correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void escribirArchivoFormatterAlquiler(String ruta, ArrayList<Registros> cliente) throws IOException{
        cliente.stream().toList();
        Formatter ft = new Formatter(ruta);
        for(Registros s : cliente)
        {
            ft.format(s.getCliente().getCedula()+";" +s.getCliente().getNombre()+";"+s.getCliente().getTelefono()+";"+ s.getCliente().getEmail()+";"+s.getCliente().getCiudad()+";"+s.getCliente().getDireccionResidencia()+";"+s.getCliente().getUsuario()+";"+s.getCliente().getContrasena()+";"+ s.getVehiculo().getPlaca()+";"+s.getVehiculo().getNombre()+";"+s.getVehiculo().getMarca()+";"+s.getVehiculo().getModelo()+";"+s.getVehiculo().getFoto()+";"+s.getVehiculo().getKilometraje()+";"+s.getVehiculo().getNumeroSillas()+";"+s.getVehiculo().getAutomatico()+";"+s.getFechaInicio()+";"+s.getFechafin()+"%n");
        }
        ft.close();
    }
    public static boolean verificarPlaca(String text) {
        boolean state = true;
        for (Vehiculos c : vehiculos )
        {
            if (c.getPlaca().equals(text))
            {
                state = false;
                LOGGER.log(Level.WARNING, "Se intento registrar un vehiculo ya registrado");
            }
        }
        return state;
    }
    public static boolean isImageValid(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            // Si el código de respuesta es 200, la imagen es válida
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            // Si hay alguna excepción, la URL de la imagen no es válida
            return false;
        }
    }
}
