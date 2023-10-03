package co.edu.uniquindio.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculos {
    private String placa, nombre, marca, modelo, foto, kilometraje,numeroSillas, automatico;
    private LocalDate fechaInicio, fechaFin;
    private float valorTotal, precioAlquilerDia;
    private Cliente cliente;
    public String toString()
    {
        String s = this.nombre + " ---- " + this.marca;
        return s;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
