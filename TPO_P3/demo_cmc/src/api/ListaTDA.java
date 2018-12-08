package api;

import graficos.Punto;
import model.NodoPrio;

public interface ListaTDA {

	// Agrega Nodo a Lista Abierta
	public void addOpenList(NodoPrio np);

	// Agrega Nodo a Lista Cerrada
	public void addClosedList(NodoPrio np);
	
	// Verifica si un Punto(x,y) existe en la Lista Abierta
	public boolean isOpenList(Punto p);
	
	// Verifica si un Nodo existe en la Lista Abierta
	public boolean isOpenList(NodoPrio np);

	// Elimina un Nodo de la Lista Abierta y de la lista de Costos
	public void removeFromOpenList(NodoPrio np);

	// Elimina un Nodo de la Lista Cerrada
	public void removeFromClosedList(NodoPrio np);

	// Elimina un Nodo de la Lista Cerrada a partir de sus coordenadas definidas en Punto(x,y)
	public void removeFromClosedList(Punto p);

	// Verifica si un Punto(x,y) existe en la Lista Cerrada
	public boolean isClosedList(Punto p);

	// Verifica si un Nodo existe en la Lista Cerrada
	public boolean isClosedList(NodoPrio np);

	// Obtiene un Nodo de la Lista Abierta
	public NodoPrio getNodoAbierto(NodoPrio np);

	// Obtiene un Nodo de la Lista Abierta a partir de un Punto(x,y)
	public NodoPrio getNodoAbierto(Punto p);

	// Obtiene un Nodo de la Lista Cerrada
	public NodoPrio getNodoCerrado(NodoPrio np);

	// Obtiene un Nodo de la Lista Cerrada a partir de un Punto(x,y)
	public NodoPrio getNodoCerrado(Punto p);

	// Obtiene el Nodo de Menor Costo de la Lista de Costos y lo elimina de la Lista Abierta
	public NodoPrio getMenorCosto();

}
