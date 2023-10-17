package co.edu.uniquindio.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Builder
@Getter
@Setter
public class Facturas {
    private Cliente cliente;
    private LocalDate fechaInicio, fechaFin;
    private float valorTotal;
}
