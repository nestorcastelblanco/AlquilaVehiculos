package co.edu.uniquindio.model;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculos {
    private String placa, nombre, marca, modelo, foto, kilometraje,numeroSillas, automatico;
    private LocalDate fechaInicio, fechaFin;
    private ArrayList<ArrayList<LocalDate>> fechasAlquiladas = new ArrayList<>();
    private float valorTotal, precioAlquilerDia;
    private Cliente cliente;
    private int contPrestamos;

    public void setFechasAlquiladas(ArrayList<LocalDate> fechas)
    {
        this.fechasAlquiladas.add(fechas);
    }
    public int getContPrestamos() {
        return contPrestamos;
    }

    public void setContPrestamos(int contPrestamos) {

        this.contPrestamos += contPrestamos;
    }

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
