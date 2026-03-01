package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetalleRepuestoTest {

    @Test
    void calcularSubtotalYPropiedades() {
        Taller taller = new Taller("Taller", "123", "Dir", "T1",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Repuesto r = new Repuesto("Cadena", "R2", 5, taller, new ArrayList<>());

        DetalleRepuesto d = new DetalleRepuesto(3, 12.5, 0.0, r, null);
        assertEquals(37.5, d.calcularSubtotal(), 1e-6);

        d.setSubtotal(d.calcularSubtotal());
        assertEquals(37.5, d.getSubtotal(), 1e-6);

        d.setCantidad(4);
        d.setPrecioUnitario(10.0);
        assertEquals(4, d.getCantidad());
        assertEquals(10.0, d.getPrecioUnitario(), 1e-6);

        d.setOwnedByRepuesto(r);
        assertEquals(r, d.getOwnedByRepuesto());
    }
}
