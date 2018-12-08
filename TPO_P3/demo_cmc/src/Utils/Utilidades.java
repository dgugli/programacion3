/**
 * Universidad Argentina de la Empresa
 * 
 * Anio:		2018
 * Materia: 	Programacion III
 * Docente: 	Cancela, Julio Eduardo
 * 
 * Objetivo: 	Trabajo Practico Obligatorio
 * Descripcion: 
 * 				Desarrollo de un algoritmo heurístico para la determinación en tiempo real del camino de mínimo costo 
 * 				entre dos puntos cualesquiera definidos como origen – destino, dentro de un marco de referencia. 
 * 				Contemplando la representación visual en la implementación.
 * 
 * Integrantes:
 * Legajo	Apellido,Nombre
 * -------------------------
 * 1096060	Martino, Mariano
 * 1030762	Gomez Antonaz, Juan Pablo
 * 1093958	Sabatino, Noemi Nancy Analia
 * 1107524	Bedecarats Asselborn, Esteban Diego
 * 1093961	Guglielmi, Daniel
 */
package Utils;
import graficos.Punto;
import model.NodoPrio;

public class Utilidades {
// Funciones Auxiliares
	
	public double costoRecto(Punto origen, Punto fin)  {
		// Origen = Coordenadas del nodo desde donde se quiere calcular el costo de la distancia
		// Fin    = Coordenadas del nodo Destino (Ultimo nodo que hay que alcanzar)
		return ((Math.abs( (fin.getX() - origen.getX()))) + ( Math.abs( (fin.getX() - origen.getX()))))*10;
	}
	
	public double costoDesplazamiento(NodoPrio origen, Punto fin) {		
		return ( (origen.getPunto().x != fin.x) && (origen.getPunto().y != fin.y)) ? 
				origen.getCostoDesplazamiento() + 14 :
					origen.getCostoDesplazamiento() + 10;
	}
	
	public double costoDesplazamiento(NodoPrio origen, NodoPrio fin) {		
		return ( (origen.getPunto().x != fin.getPunto().x) && (origen.getPunto().y != fin.getPunto().y)) ? 
				origen.getCostoDesplazamiento() + 14 :
					origen.getCostoDesplazamiento() + 10;
	}
}
