package co.edu.uniquindio.model;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String  cedula, nombre, telefono, email, ciudad,direccionResidencia, usuario, contrasena;
    private ArrayList<Vehiculos> vehiculoAdquirido = new ArrayList<Vehiculos>();
    public ArrayList<Vehiculos> getVehiculoAdquirido() {
        return vehiculoAdquirido;
    }

    public String getFactura() {
        return nombre + " " + cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVehiculoAdquirido(Vehiculos vehiculoAdquirido) {
        this.vehiculoAdquirido.add(vehiculoAdquirido);
    }
}
