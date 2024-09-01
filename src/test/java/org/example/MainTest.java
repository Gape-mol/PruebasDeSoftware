package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private String[][] entradas;
    Main main = new Main();

    @BeforeEach
    void setupEntradas() {
        entradas = new String[10][5];
        entradas[0] = new String[]{"Antonio", "20", "VIP", "2", "False"};
        entradas[1] = new String[]{"Maria", "15", "General", "0", "False"};
        entradas[2] = new String[]{"Juan", "25", "VIP", "3", "True"};
        entradas[3] = new String[]{"Pedro", "17", "VIP", "1", "False"};
        entradas[4] = new String[]{"Ana", "30", "General", "0", "True"};
    }

    @Test
    void verificarEdad() {
        assertTrue(main.verificarEdad(entradas, 0));
        assertFalse(main.verificarEdad(entradas, 1));
        assertTrue(main.verificarEdad(entradas, 2));
        assertFalse(main.verificarEdad(entradas, 3));
        assertTrue(main.verificarEdad(entradas, 4));
    }

    @Test
    void verificarBoleto() {
        assertEquals("VIP", main.verificarBoleto(entradas, 0));
        assertEquals("General", main.verificarBoleto(entradas, 1));
        assertEquals("VIP", main.verificarBoleto(entradas, 2));
        assertEquals("VIP", main.verificarBoleto(entradas, 3));
        assertEquals("General", main.verificarBoleto(entradas, 4));
    }

    @Test
    void validarInvitados() {
        assertTrue(main.validarInvitados(entradas, 0));
        assertFalse(main.validarInvitados(entradas, 1));
        assertFalse(main.validarInvitados(entradas, 2));
        assertTrue(main.validarInvitados(entradas, 3));
        assertFalse(main.validarInvitados(entradas, 4));
    }

    @Test
    void aforoDisponible() {
        assertEquals(8, main.aforoDisponible(entradas, "VIP"));
        assertEquals(2, main.aforoDisponible(entradas, "General"));
    }

    @Test
    void aforoDisponibleSinEntradas() {
        assertEquals(20, main.aforoDisponible(entradas, "VIP"));
        assertEquals(40, main.aforoDisponible(entradas, "General"));
    }

    @Test
    void aforoDisponibleConEntradas() {
        entradas[0][4] = "True";
        entradas[1][4] = "True";
        entradas[2][4] = "True";
        entradas[3][4] = "True";
        entradas[4][4] = "True";
        assertEquals(3, main.aforoDisponible(entradas, "VIP"));
        assertEquals(1, main.aforoDisponible(entradas, "General"));
    }
    @Test
    void ingresarPersonas() {
        assertTrue(main.ingresarPersona(entradas, 0));
        assertTrue(main.ingresarPersona(entradas, 1));
        assertFalse(main.ingresarPersona(entradas, 2));
        assertTrue(main.ingresarPersona(entradas, 3));
        assertFalse(main.ingresarPersona(entradas, 4));
    }
    @Test
    void permitirEntrada(){
        assertTrue(main.permitirEntrada(entradas, 0, "VIP"));
        assertFalse(main.permitirEntrada(entradas, 1, "General"));
        assertFalse(main.permitirEntrada(entradas, 2, "VIP"));
        assertTrue(main.permitirEntrada(entradas, 3, "VIP"));
        assertFalse(main.permitirEntrada(entradas, 4, "General"));
    }
    @AfterEach
    void vaciarEntradas() {
        entradas = new String[10][5];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                entradas[i][j] = null;
            }
        }
    }
}
