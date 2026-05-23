package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

//Cambio de nombre
public class GestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {	// Wmc 2 + 1	 CCog 1 + 2
		for(Conductor c: cs) //Wmc +1 CCog +1
			if (c.dni().equals(DNI)) //Wmc +1 CCog +2
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, 
			String apellido1, String apellido2, String direccion) { //Wmc 1 + 1 CCog 1
		if (buscaConductor(dni) != null) //Wmc +1 CCog +1
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() { //Wmc 0 + 1 CCog 0
		return cs;
	}
	
}
