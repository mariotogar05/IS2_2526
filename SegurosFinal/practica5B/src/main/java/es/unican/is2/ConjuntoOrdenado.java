package es.unican.is2;

import java.util.ArrayList;
import java.util.List;

import es.unican.is2.adt.IConjuntoOrdenado;
/**
 * Clase que implementa el ADT ListaOrdenada
 */
public class ConjuntoOrdenado<E extends Comparable<E>> implements IConjuntoOrdenado<E> {

	private List<E> lista = new ArrayList<E>();

	public E get(int indice) {
		return lista.get(indice);
	}

	public boolean add(E elemento) {
        if (elemento == null) {
            throw new NullPointerException();
        }
        
        int indice = 0;
        while (indice < lista.size()) {
            int comparacion = elemento.compareTo(lista.get(indice));
            
            if (comparacion == 0) {
                return false;     
            } else if (comparacion > 0) {
                indice++;  
            } else {
                break;
            }
        }
        lista.add(indice, elemento);
        return true;
    }

	public E remove(int indice) {
		E borrado = lista.remove(indice);
		return borrado;
	}

	public int size() {
		return lista.size();
	}

	public void clear() {
		lista.clear();
	}

}
