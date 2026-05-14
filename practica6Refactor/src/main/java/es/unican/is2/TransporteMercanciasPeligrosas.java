package es.unican.is2;

public class TransporteMercanciasPeligrosas extends TransporteMercancias {

    private static final double PLUS_PELIGROSIDAD = 50.0;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) {
        super(horas, toneladas);
    }

    @Override
    public double calcularSueldoExtra() {
        return super.calcularSueldoExtra() + PLUS_PELIGROSIDAD;
    }
}
