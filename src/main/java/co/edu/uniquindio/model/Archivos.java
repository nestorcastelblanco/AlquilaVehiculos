package co.edu.uniquindio.model;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Archivos {
    private static final Logger LOGGER = Logger.getLogger(Archivos.class.getName());
    public static void main(String[] args) {
        try {
            FileHandler fh=new FileHandler("errores.xml", true);
            LOGGER.addHandler(fh);
            LOGGER.log(Level.INFO, "Mensaje Informativo");
            LOGGER.log(Level.WARNING, "Mensaje Advertencia");
            LOGGER.log(Level.SEVERE, "Mensaje Error Grave");
        } catch (SecurityException | IOException e) {
          LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}