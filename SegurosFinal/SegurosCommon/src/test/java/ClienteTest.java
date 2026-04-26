import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    @Test
    void testTotalSegurosCasosValidos() {
        Cliente cliente = new Cliente();
        
        // ----------------------------------------------------
        // CASO: 0 seguros (Equivale a tu fila "0, false")
        // ----------------------------------------------------
        assertEquals(0.0, cliente.totalSeguros(), "Una lista vacía debe devolver coste 0");

        // ----------------------------------------------------
        // CASO: 1 seguro (Equivale a tu fila "1, true")
        // ----------------------------------------------------
        // Creamos un seguro de prueba válido usando tu clase Seguro
        Seguro s1 = new Seguro(1, "1234BBB", 40, Cobertura.TODO_RIESGO, LocalDate.now().minusMonths(3), null);
        cliente.setSeguro(s1);
        
        assertEquals(800, cliente.totalSeguros(), 0.01, "El total debe coincidir con el precio del único seguro");
    }

    @Test
    void testTotalSegurosCasosNoValidos() {
        Cliente cliente = new Cliente();
        
        // ----------------------------------------------------
        // CASO: Estado inválido (Equivale a tu fila "-1")
        // ----------------------------------------------------
        // Forzamos que la lista sea null. El bucle for-each de tu método 
        // fallará al intentar recorrer un null, lanzando un NullPointerException.
        cliente.setSeguros(null);
        
        assertThrows(NullPointerException.class, () -> {
            cliente.totalSeguros();
        }, "Debería lanzar NullPointerException si la lista de seguros no está inicializada");
    }
}
