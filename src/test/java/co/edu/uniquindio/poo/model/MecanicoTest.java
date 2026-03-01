package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MecanicoTest {

    @Test
    void propiedadesYHerencia() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Mecanico m = new Mecanico("Luis", "888", "Cra 1", "M1", t, new ArrayList<>(), null, 2000, "CERT-01");

        assertEquals("Luis", m.getNombre());
        m.setTel("999");
        assertEquals("999", m.getTel());
        m.setSueldo(2500);
        assertEquals(2500, m.getSueldo());
        m.setNoCertificado("CERT-02");
        assertEquals("CERT-02", m.getNoCertificado());
    }
}
