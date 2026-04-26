import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

class SeguroTest{

    @Test
    void testCasosValidos() {
        // Caso 1: Todo Riesgo, 40cv, hoy-3meses, sin conductor adicional
        Seguro s1 = new Seguro(1, "1234BBB", 40, Cobertura.TODO_RIESGO, LocalDate.now().minusMonths(3), null);
        // Cálculo esperado: 1000 (base) + 5% (potencia) - 20% (antigüedad < 1 año) = 840.0
        assertEquals(840.0, s1.precio(), 0.01);

        // Caso 2: Terceros Lunas, 90cv, hoy-1año, con conductor
        Seguro s2 = new Seguro(2, "5678CCC", 90, Cobertura.TERCEROS_LUNAS, LocalDate.now().minusYears(1), "Pepe");
        // Al ser justo 1 año, no aplica el descuento del 20% (isBefore)
        // 600 + 5% = 630.0
        assertEquals(630.0, s2.precio(), 0.01);

        // Caso 3: Terceros, 110cv, hoy, sin conductor
        Seguro s3 = new Seguro(3, "9012DDD", 110, Cobertura.TERCEROS, LocalDate.now(), null);
        // 400 + 5% - 20% (es hoy, < 1 año) = 336.0
        assertEquals(336.0, s3.precio(), 0.01);
        
        // Caso Extra: Potencia > 110 (Ej: 120cv)
        Seguro s4 = new Seguro(4, "3456EEE", 120, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(6), null);
        // 1000 + 20% (potencia > 110) = 1200.0 (No hay desc. antigüedad)
        assertEquals(1200.0, s4.precio(), 0.01);
    }

    @Test
    void testCasosNoValidosYNulls() {
        // Caso: Fecha inicio null (según tu tabla de valores nulos)
        Seguro sNull = new Seguro(5, "9999ZZZ", 100, Cobertura.TODO_RIESGO, null, null);
        assertEquals(0, sNull.precio(), "Si la fecha es null debe devolver 0");

        // Caso: Fecha en el futuro (> hoy)
        Seguro sFuturo = new Seguro(6, "1111AAA", 100, Cobertura.TODO_RIESGO, LocalDate.now().plusDays(1), null);
        assertEquals(0, sFuturo.precio(), "Si no ha entrado en vigor debe devolver 0");
    }
}
