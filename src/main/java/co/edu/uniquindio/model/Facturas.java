package co.edu.uniquindio.model;

import java.util.ArrayList;

public class Facturas extends Vehiculos{
    private ArrayList<Facturas> facturasRegistradas = new ArrayList<>();
    public ArrayList<Facturas> getFacturasRegistradas() {
        return facturasRegistradas;
    }
    public void setFacturasRegistradas(Facturas facturasRegistradas) {
        this.facturasRegistradas.add(facturasRegistradas);;
    }
}
