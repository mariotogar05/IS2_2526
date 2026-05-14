package es.unican.is2;

public class TransporteMercancias extends Transporte {
    private int toneladas;
    
    protected static final double PAGO_POR_TONELADA = 2.0;

    public TransporteMercancias(double horas, int toneladas) {
        super(horas);
        if (toneladas <= 0) {
            throw new IllegalArgumentException("Las toneladas deben ser mayores a 0");
        }
        this.toneladas = toneladas;
    }

    public int getToneladas() {
        return toneladas;
    }

    @Override
    public double calcularSueldoExtra() {
        return toneladas * PAGO_POR_TONELADA;
    }
}