package co.edu.uniquindio.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class Registros  implements Serializable {
    private Cliente cliente;
    private Vehiculos vehiculo;
    private LocalDate fechaInicio, fechafin;

    public String toString()
    {
        return vehiculo.getNombre() + " " + vehiculo.getPlaca();
    }

}
