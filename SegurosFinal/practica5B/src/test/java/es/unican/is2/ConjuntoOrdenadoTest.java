package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    public void setUp() {
        // Inicializamos un nuevo conjunto antes de cada test
        conjunto = new ConjuntoOrdenado<Integer>();
    }

    // ==========================================
    // TESTS PARA EL MÉTODO ADD
    // ==========================================

    @Test
    public void testAddValidos() {
        // Caso: Añadir a una lista vacía
        assertTrue(conjunto.add(10));
        assertEquals(10, conjunto.get(0));

        // Caso: Añadir un elemento que debe ir al final (menor que el actual)
        assertTrue(conjunto.add(5));
        assertEquals(5, conjunto.get(1));

        // Caso: Añadir un elemento que debe ir al principio (mayor que los actuales)
        assertTrue(conjunto.add(20));
        assertEquals(20, conjunto.get(0));
        
        // Verificamos que la lista mantenga el tamaño y orden correctos: [20, 10, 5]
        assertEquals(10, conjunto.get(1));
        assertEquals(5, conjunto.get(2));
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
        // Preparamos el entorno de prueba con una lista.length > 0
        conjunto.add(10);
        conjunto.add(20);
        conjunto.add(30); 
        // El orden interno tras los add será [30, 20, 10]

        // Caso válido: Índice 0 (primer elemento)
        assertEquals(30, conjunto.get(0));

        // Caso válido: Índice intermedio (ej. 1)
        assertEquals(20, conjunto.get(1));

        // Caso válido: Índice numElementos - 1 (último elemento)
        assertEquals(10, conjunto.get(2));
    }

    @Test
    public void testGetNoValidos() {
        // Preparamos una lista con elementos para probar límites
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
        conjunto.add(10);
        conjunto.add(20);
        conjunto.add(30); 
        // El orden interno será [30, 20, 10]

        // Caso: Eliminar índice intermedio
        conjunto.remove(1); // Elimina el 20
        // La lista debe quedar [30, 10]
        assertEquals(2, conjunto.size());
        assertEquals(30, conjunto.get(0));
        assertEquals(10, conjunto.get(1));

        // Caso: Eliminar el primer elemento (índice 0)
        conjunto.remove(0); // Elimina el 30
        // La lista debe quedar [10]
        assertEquals(1, conjunto.size());
        assertEquals(10, conjunto.get(0));
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
        conjunto.add(4);
        conjunto.add(5);
        assertEquals(5, conjunto.size());
    }

    // ==========================================
    // TESTS PARA EL MÉTODO CLEAR
    // ==========================================

    @Test
    public void testClear() {
        // Caso: Vaciar lista con elementos
        conjunto.add(1);
        conjunto.add(2);
        conjunto.add(3);
        conjunto.add(4);
        conjunto.add(5);
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