package es.unican.is2;
/* Clase que representa un transporte realizado por un conductor */
public abstract class Transporte {
	
	private double horas;
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 */ 

    public Transporte(double horas) {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas deben ser mayores a 0");
        }
        this.horas = horas;
    }

    public double getHoras() {
        return horas;
    }

    // Move Method: El cálculo del extra se delega a las subclases
    public abstract double calcularSueldoExtra();	
}
