package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ServicioTest {

    @Test
    void calcularDescuento() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Servicio s1 = new Servicio(LocalDate.now(), "S1", "", "", 5, 0.0, t, null, null, new ArrayList<>());
        Servicio s2 = new Servicio(LocalDate.now(), "S2", "", "", 4, 0.0, t, null, null, new ArrayList<>());

        assertEquals(25.0, s1.calcularDescuento(100.0), 1e-6);
        assertEquals(0.0, s2.calcularDescuento(100.0), 1e-6);
    }

    @Test
    void calcularCostoTotal() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Repuesto r = new Repuesto("Llanta", "R1", 4, t, new ArrayList<>());

        DetalleRepuesto d1 = new DetalleRepuesto(2, 10.0, 0.0, r, null); // 20
        d1.setSubtotal(d1.calcularSubtotal());
        DetalleRepuesto d2 = new DetalleRepuesto(1, 5.0, 0.0, r, null);  // 5
        d2.setSubtotal(d2.calcularSubtotal());

        List<DetalleRepuesto> detalles = new ArrayList<>();
        detalles.add(d1);
        detalles.add(d2);

        Servicio s = new Servicio(LocalDate.now(), "S3", "", "", 5, 0.0, t, null, null, detalles);
        double total = s.calcularCostoTotal(100.0); // 100 + 25 = 125; desc 25% => 93.75
        assertEquals(93.75, total, 1e-6);
    }

    @Test
    void listaDetallesGetterSetter() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<DetalleRepuesto> detalles = new ArrayList<>();
        Servicio s = new Servicio(LocalDate.now(), "S4", "m", "d", 1, 0.0, t, null, null, detalles);
        assertSame(detalles, s.getListaDetalleRepuesto());

        List<DetalleRepuesto> detalles2 = new ArrayList<>();
        s.setListaDetalleRepuesto(detalles2);
        assertSame(detalles2, s.getListaDetalleRepuesto());
    }
}
