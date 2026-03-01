package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TallerTest {

    @Test
    void constructorYGettersSettersBasicos() {
        Taller t = new Taller("MiTaller", "111", "Dir", "ID",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertEquals("MiTaller", t.getNombre());
        t.setNombre("Otro");
        assertEquals("Otro", t.getNombre());

        t.setTel("222");
        assertEquals("222", t.getTel());

        t.setDireccion("NuevaDir");
        assertEquals("NuevaDir", t.getDireccion());

        t.setId("ID2");
        assertEquals("ID2", t.getId());
    }
}
