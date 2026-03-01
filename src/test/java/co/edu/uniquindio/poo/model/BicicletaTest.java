package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BicicletaTest {

    @Test
    void propiedadesBasicas() {
        Taller t = new Taller("Taller", "1", "D", "T",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Bicicleta b = new Bicicleta("Roja", "GW", "SN1", "2020", null, null, t);

        assertEquals("Roja", b.getColor());
        assertEquals("GW", b.getMarca());
        assertEquals("SN1", b.getNoSerial());
        assertEquals("2020", b.getAnio());
        assertEquals(t, b.getOwnedByTaller());

        b.setColor("Azul");
        b.setMarca("Trek");
        b.setNoSerial("SN2");
        b.setAnio("2021");
        assertEquals("Azul", b.getColor());
        assertEquals("Trek", b.getMarca());
        assertEquals("SN2", b.getNoSerial());
        assertEquals("2021", b.getAnio());
    }
}
