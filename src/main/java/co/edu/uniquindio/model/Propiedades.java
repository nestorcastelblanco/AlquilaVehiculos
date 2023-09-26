package co.edu.uniquindio.model;

import lombok.Getter;
import java.util.ResourceBundle;
/**
 * Clase Singleton para acceder a los textos que están guardados en los archivos de
 propiedades
 */
public class Propiedades {
    @Getter
    private final ResourceBundle resourceBundle;
    private static Propiedades instancia;
    private Propiedades(){
        this.resourceBundle = ResourceBundle.getBundle("textos");
    }
    public static Propiedades getInstance(){
        if(instancia == null){
            instancia = new Propiedades();
        }
        return instancia;
    }
}