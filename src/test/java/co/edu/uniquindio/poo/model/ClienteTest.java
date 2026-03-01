package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ClienteTest {

    @Test
    void herenciaYListas() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Cliente c = new Cliente("Ana", "555", "Calle 2", "C1", t, new ArrayList<>(), new ArrayList<>());

        assertEquals("Ana", c.getNombre());
        c.setNombre("Ana M.");
        assertEquals("Ana M.", c.getNombre());
        c.setTel("777");
        assertEquals("777", c.getTel());

        var bicis = new ArrayList<Bicicleta>();
        c.setListaBicicletas(bicis);
        assertSame(bicis, c.getListaBicicletas());

        var servicios = new ArrayList<Servicio>();
        c.setListaServicios(servicios);
        assertSame(servicios, c.getListaServicios());
    }
}
