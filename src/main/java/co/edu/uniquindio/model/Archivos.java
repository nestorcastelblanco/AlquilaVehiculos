package co.edu.uniquindio.model;
import java.util.logging.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class Archivos {
    private static final Logger LOGGER = Logger.getLogger(Archivos.class.getName());
    public static void main(String[] args) throws SecurityException, IOException {
        LOGGER.log(Level.INFO, "Mensaje Informativo");
        LOGGER.log(Level.WARNING, "Mensaje Advertencia");
        LOGGER.log(Level.SEVERE, "Mensaje Error Grave");
    }
}
