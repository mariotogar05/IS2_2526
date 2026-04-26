import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VistaAgenteIT {

    private FrameFixture demo;
    private VistaAgente sut;


    @BeforeEach
    void setUp() {
        // 1. Creamos el Stub arreglado
        IInfoSeguros infoStub = new IInfoSeguros() {
        
        // Método 1: El que sí usamos
        @Override
        public Cliente cliente(String dni) throws DataAccessException {
            if ("11111111A".equals(dni)) {
                // ARREGLO DEL ERROR 2: Constructor vacío y setters
                Cliente c = new Cliente();
                // OJO: Si tu método setter se llama diferente, cámbialo aquí
                c.setNombre("Pepe"); 
                return c; 
            } else if ("12345678A".equals(dni)) {
                return null; 
            }
            if (dni == null) {
                throw new DataAccessException(); // O la excepción que use tu código
            }
            return null;
        }

        // ARREGLO DEL ERROR 1: Añadimos el método que faltaba para cumplir la interfaz
        @Override
        public Seguro seguro(String idSeguro) throws DataAccessException {
            return null; // No lo usamos en esta vista, así que null
        }
    };

    // 2. Instanciamos la Vista inyectando nuestro Stub
    sut = new VistaAgente(null, null, infoStub);
    
    // 3. Inicializamos AssertJ Swing
    demo = new FrameFixture(sut);
    sut.setVisible(true);
}

    @AfterEach
    void tearDown() {
        // Es obligatorio limpiar los recursos de AssertJ Swing al terminar [cite: 14]
        demo.cleanUp();
    }

    @Test
    void testBuscarClienteValido() {
        // CASO: 11111111A (DNI en BBDD)
        
        // TRAMPA PARA EL TEST: Escribimos el DNI en el campo del Nombre porque 
        // tu clase VistaAgente está leyendo de txtNombreCliente al pulsar buscar.
        demo.textBox("txtNombreCliente").enterText("11111111A");
        
        // Pulsamos el botón buscar
        demo.button("btnBuscar").click();
        
        // Comprobamos que el nombre se sobrescribe y ahora pone "Pepe"
        demo.textBox("txtNombreCliente").requireText("Pepe");
        
        // Comprobamos que el total a pagar no esté vacío
        assertNotEquals("", demo.textBox("txtTotalCliente").text());
    }

    @Test
    void testBuscarClienteNoValido() {
        // CASO: 12345678A (DNI NO en BBDD)
        
        // TRAMPA PARA EL TEST: Escribimos el DNI falso en el campo del Nombre
        demo.textBox("txtNombreCliente").enterText("12345678A");
        
        // Pulsamos el botón buscar
        demo.button("btnBuscar").click();
        
        // Comprobamos que el sistema devuelve el error esperado
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
        demo.textBox("txtTotalCliente").requireText("");
    }
}
