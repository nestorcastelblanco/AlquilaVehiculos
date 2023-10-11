package co.edu.uniquindio.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Registros  implements Serializable {
    private Cliente cliente;
    private Vehiculos vehiculo;
    private LocalDate fechaInicio, fechafin;

}
