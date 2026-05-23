package es.unican.is2;

public class TransportePersonas extends Transporte {
    private int personas;
    
    private static final int UMBRAL_PERSONAS = 10;
    private static final double PAGO_EXTRA_MENOS_10 = 0.5;
    private static final double PAGO_EXTRA_MAS_10 = 1.0;

    public TransportePersonas(double horas, int personas) {
        super(horas);
        if (personas <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser mayor a 0");
        }
        this.personas = personas;
    }

    public int getPersonas() {
        return personas;
    }

    @Override
    public double calcularSueldoExtra() {
        if (personas < UMBRAL_PERSONAS) {
            return getHoras() * PAGO_EXTRA_MENOS_10;
        } else {
            return getHoras() * PAGO_EXTRA_MAS_10;
        }
    }
}