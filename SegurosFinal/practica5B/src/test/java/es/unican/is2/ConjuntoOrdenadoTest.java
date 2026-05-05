package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    public void setUp() {
        conjunto = new ConjuntoOrdenado<Integer>();
    }

    // ==========================================
    // TESTS PARA EL MÉTODO ADD
    // ==========================================

    @Test
    public void testAddValidos() {
        // Caso: Añadir a una lista vacía
        assertTrue(conjunto.add(10));
        
        // Caso: Añadir un elemento que debe ir al principio
        assertTrue(conjunto.add(5));
        
        // Caso: Añadir un elemento que debe ir al final
        assertTrue(conjunto.add(20));
        
        // Verificamos que la lista mantenga el orden ascendente: [5, 10, 20]
        assertEquals(5, conjunto.get(0));
        assertEquals(10, conjunto.get(1));
        assertEquals(20, conjunto.get(2));
        
        // TEST DE DUPLICADOS: Intentamos añadir un elemento que ya existe
        assertFalse(conjunto.add(10)); 
        assertEquals(3, conjunto.size());
    }

    @Test
    public void testAddNoValidos() {
        // Casos prueba no válidos de tu tabla (Null)
        assertThrows(NullPointerException.class, () -> {
            conjunto.add(null);
        });
    }

    // ==========================================
    // TESTS PARA EL MÉTODO GET
    // ==========================================

    @Test
    public void testGetValidos() {
        // Preparamos el entorno añadiendo elementos desordenados
        conjunto.add(20);
        conjunto.add(10);
        conjunto.add(30); 
        // El orden interno tras los add será el correcto: [10, 20, 30]

        // Caso válido: Índice 0 (primer elemento, el menor)
        assertEquals(10, conjunto.get(0));

        // Caso válido: Índice intermedio (ej. 1)
        assertEquals(20, conjunto.get(1));

        // Caso válido: Índice numElementos - 1 (último elemento, el mayor)
        assertEquals(30, conjunto.get(2));
    }

    @Test
    public void testGetNoValidos() {
        conjunto.add(10);
        conjunto.add(20);
        conjunto.add(30); // numElementos = 3

        // Caso no válido: Índice negativo (-1)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjunto.get(-1);
        });

        // Caso no válido: Índice igual a numElementos (fuera de límite superior)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjunto.get(3);
        });

        // Caso no válido: Lista vacía (lista.length == 0)
        ConjuntoOrdenado<Integer> conjuntoVacio = new ConjuntoOrdenado<Integer>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoVacio.get(0);
        });
    }

    // ==========================================
    // TESTS PARA EL MÉTODO REMOVE
    // ==========================================

    @Test
    public void testRemoveValidos() {
        conjunto.add(30);
        conjunto.add(10);
        conjunto.add(20); 
        // El orden interno será [10, 20, 30]

        // Caso: Eliminar índice intermedio (índice 1 es el valor 20)
        conjunto.remove(1); 
        // La lista debe quedar [10, 30]
        assertEquals(2, conjunto.size());
        assertEquals(10, conjunto.get(0));
        assertEquals(30, conjunto.get(1));

        // Caso: Eliminar el primer elemento (índice 0 es el valor 10)
        conjunto.remove(0); 
        // La lista debe quedar solo con el [30]
        assertEquals(1, conjunto.size());
        assertEquals(30, conjunto.get(0));
    }

    @Test
    public void testRemoveNoValidos() {
        conjunto.add(10);
        conjunto.add(20);
        // numElementos = 2

        // Caso no válido: Índice negativo
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjunto.remove(-1);
        });

        // Caso no válido: Índice igual o mayor a numElementos
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjunto.remove(2);
        });

        // Caso no válido: Lista vacía
        ConjuntoOrdenado<Integer> conjuntoVacio = new ConjuntoOrdenado<Integer>();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjuntoVacio.remove(0);
        });
    }

    // ==========================================
    // TESTS PARA EL MÉTODO SIZE
    // ==========================================

    @Test
    public void testSize() {
        // Caso: Lista vacía devuelve 0
        assertEquals(0, conjunto.size());

        // Caso: Lista con elementos devuelve el número exacto
        conjunto.add(1);
        conjunto.add(2);
        conjunto.add(3);
        assertEquals(3, conjunto.size());
    }

    // ==========================================
    // TESTS PARA EL MÉTODO CLEAR
    // ==========================================

    @Test
    public void testClear() {
        // Caso: Vaciar lista con elementos
        conjunto.add(1);
        conjunto.add(2);
        conjunto.clear();
        
        assertEquals(0, conjunto.size());
        // Verificamos adicionalmente que intente acceder lance excepción al estar vacía
        assertThrows(IndexOutOfBoundsException.class, () -> {
            conjunto.get(0);
        });

        // Caso: Vaciar lista que ya está vacía
        ConjuntoOrdenado<Integer> conjuntoVacio = new ConjuntoOrdenado<Integer>();
        conjuntoVacio.clear();
        assertEquals(0, conjuntoVacio.size());
    }
}