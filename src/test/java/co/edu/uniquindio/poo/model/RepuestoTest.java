package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepuestoTest {

    @Test
    void propiedadesBasicas() {
        Taller taller = new Taller("Taller", "123", "Dir", "T1",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Repuesto r = new Repuesto("Freno", "R1", 10, taller, new ArrayList<>());

        assertEquals("Freno", r.getNombre());
        assertEquals("R1", r.getCodigo());
        assertEquals(10, r.getStockDisponible());
        assertEquals(taller, r.getOwnedByTaller());

        r.setNombre("Disco Freno");
        r.setCodigo("R1-NEW");
        r.setStockDisponible(8);
        r.setOwnedByTaller(taller);
        assertEquals("Disco Freno", r.getNombre());
        assertEquals("R1-NEW", r.getCodigo());
        assertEquals(8, r.getStockDisponible());
    }
}
