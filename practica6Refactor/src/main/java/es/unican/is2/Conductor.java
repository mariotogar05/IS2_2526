package es.unican.is2;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dire;

	private static final double SUELDO_BASE = 700.0;
    private static final double PAGO_POR_HORA = 5.0;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { //Wmc 4 + 1 CCog 1 + 1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { // Wmc +4 CCog +2
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dire = direccion;
	}

	public String dni() { // Wmc 0 +1 CCog 0
		return dni;
	}

	public String getDni() { // Wmc 0 +1 CCog 0
		return dni;
	}

	public String getNombre() { // Wmc 0 +1 CCog 0
		return nombre;
	}

	public String getApellido1() { // Wmc 0 +1 CCog 0
		return apellido1;
	}

	public String apellido2() { // Wmc 0 +1 CCog 0
		return apellido2;
	}

	public String getDire() { // Wmc 0 +1 CCog 0
		return dire;
	}

	public double sueldo() {
        double sueldoTransportes = 0;
        for (Transporte t : transportes) {
            //Sustituto del switch
            sueldoTransportes += (t.getHoras() * PAGO_POR_HORA) + t.calcularSueldoExtra();
        }
        return SUELDO_BASE + sueldoTransportes;
    }

	public void anhadeTransporte(Transporte t) { // Wmc 0 + 1 CCog 0
		transportes.add(t);
	}

}
