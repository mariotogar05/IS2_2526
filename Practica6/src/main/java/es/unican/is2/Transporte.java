package es.unican.is2;

/* Clase que representa un transporte realizado por un conductor */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) // WmC 4 + 1 CCog 1 + 1 + 1 + 1
			throws IllegalArgumentException {
		if (horas <= 0 || valor <= 0 || cat == null) { // WmC 4 CCog +2
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) { // Wmc +1 CCog +1
			this.personas = valor;
		} else  { // CCog +1
			this.ton = valor;
		}
	}
	
	public double horas() { // WmC 0 + 1 CCog 0 
		return horas;
	}

	public CategoriaTransporte categoria() { // WmC 0 + 1 CCog 0
		return cat;
	}

	public int ton() { // WmC 0 + 1 CCog 0
		return ton;
	}

	public int getPersonas() { // WmC 0 + 1 CCog 0
		return personas;
	}
	
}
