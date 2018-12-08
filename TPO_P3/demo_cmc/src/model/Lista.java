package model;

/**
 * La clase Lista se encarga de gestionar las listas utilizadas para la busqueda del mejor camino.
 * 
 * Lista Abierta: Hashmap que almacena un punto(x,y) como clave y un Nodo con Prioridad como valor
 * Lista Cerrada: misma estructura que la lista Abierta
 * Lista Costos: Esta lista es una estructura de Cola con prioridad, que almacena el menor costo arriba de todo.
 * 
 * 
 */
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import api.ListaTDA;
import graficos.Punto;

public class Lista implements ListaTDA {

	// La Cola con prioridad almacena los Nodo con menor costo según la logica
	// aplicada en el compareTo de dicho objeto.
	private PriorityQueue<NodoPrio> listaCostos = null;
	// Los mapas son utilizados por cuestiones de rendimiento, se evita realizar
	// recorridos repetitivos y extensos en listas muy cargadas al momento de buscar
	// un Punto en particular, realizamos un get del Punto, si
	// nos devuelve null, el Punto no existe, caso contrario, obtenemos el punto
	// requerido.
	private Map<Punto, NodoPrio> listaAbierta = null;
	private Map<Punto, NodoPrio> listaCerrada = null;

	public Lista() {
		listaCostos = new PriorityQueue<>();
		listaAbierta = new HashMap<>();
		listaCerrada = new HashMap<>();
	}

	/*
	 * addOpenList(NodoPrio np): Esta funcion administra ambas listas, la de costos
	 * y la abierta, ingresa el nodo como valor y su coordenada como clave. Cuando
	 * se inserta el Nodo en la lista de Costos, el mismo es insertado en orden por
	 * menor costo, con el objetivo de ser recuperado con mayor eficiencia y evitar
	 * recorridos en listas extensas.
	 * 
	 */
	public void addOpenList(NodoPrio np) {
		listaCostos.add(np);
		listaAbierta.put(np.getPunto(), np);
	}

	/*
	 * addClosedList(NodoPrio np): Se inserta un Nodo en la lista cerrada
	 * (HashMap<Punto,Nodo)
	 */
	public void addClosedList(NodoPrio np) {
		listaCerrada.put(np.getPunto(), np);
	}

	/*
	 * removeFromOpenList(NodoPrio np): Si el Nodo va a ser eliminado de la lista
	 * abierta, tambien debe ser eliminado de la lista de Costos para mantener la
	 * consistencia de datos.
	 */
	public void removeFromOpenList(NodoPrio np) {
		listaAbierta.remove(np.getPunto());
		listaCostos.remove(np);
	}

	public void removeFromClosedList(NodoPrio np) {
		listaCerrada.remove(np.getPunto());
	}

	public void removeFromClosedList(Punto p) {
		listaCerrada.remove(p);
	}

	/*
	 * funciones IS Las funciones IS son utilizadas para chequear la existencia de
	 * un Nodo o Punto(x,y) dentro de una lista especifica. Puede ser solicitado
	 * chequear su existencia dentro de la lista abierta o cerrada.
	 */
	public boolean isOpenList(Punto p) {
		return (listaAbierta.get(p) == null) ? false : true;
	}

	public boolean isOpenList(NodoPrio np) {
		return (listaAbierta.get(np.getPunto()) == null) ? false : true;
	}

	public boolean isClosedList(Punto p) {
		return (listaCerrada.get(p) == null) ? false : true;
	}

	public boolean isClosedList(NodoPrio np) {
		return (listaCerrada.get(np.getPunto()) == null) ? false : true;
	}

	/**
	 * Funciones Get: Utilizadas para obtener de una lista un Nodo o Punto(x,y).
	 */
	public NodoPrio getNodoAbierto(NodoPrio np) {
		return listaAbierta.get(np.getPunto());
	}

	public NodoPrio getNodoAbierto(Punto p) {
		return listaAbierta.get(p);
	}

	public NodoPrio getNodoCerrado(NodoPrio np) {
		return listaCerrada.get(np.getPunto());
	}

	public NodoPrio getNodoCerrado(Punto p) {
		return listaCerrada.get(p);
	}

	/*
	 * getMenorCosto(): La funcion de menor costo, devuelve el Nodo ordenado en la
	 * Cola con Prioridad (PriorityQueue) que posea el menor costo. Al obtener el
	 * Nodo con menor costo, debe ser eliminado de la lista de costos y de la
	 * abierta, para que luego quien lo solicita lo agregue a la lista cerrada.
	 */
	public NodoPrio getMenorCosto() {
		NodoPrio resultado = listaCostos.poll();
		if (resultado != null)
			listaAbierta.remove(resultado.getPunto());
		return resultado;
	}

}
