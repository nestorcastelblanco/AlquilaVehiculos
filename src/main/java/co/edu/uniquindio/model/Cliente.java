package co.edu.uniquindio.model;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private String  cedula, nombre, telefono, email, ciudad,direccionResidencia, usuario, contraseña;
    private ArrayList<Vehiculos> vehiculoAdquirido;
}
