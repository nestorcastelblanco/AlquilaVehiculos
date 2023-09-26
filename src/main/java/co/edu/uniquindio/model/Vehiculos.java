package co.edu.uniquindio.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculos {
    private String placa, nombre, marca, modelo, foto, kilometraje, precioAlquilerDia,numeroSillas;
    private boolean automático;
    //private  __________ fechaInicio, fechaFin;
}
