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
 * 1093961	Guglielmi, Danel
 */
package cmc;

import java.awt.Color;
/**
 * Obtiene la lista de los puntos marcados en la matriz (mapa)
 * Itera los mismos de la siguiente forma:
 * Obtiene los 2 primeros y expande los contiguos entre ambos.
 * Primero expande eje x, segundo expande el eje y.
 * Reitera la lista expandiendo el siguiente (siempre expandiendo de a pares)
 * El recorrido es secuencial (conforme al orden de marcado de los puntos en el mapa)
 * Invoca la método dibujar en cada iteración.
 * Al finalizar la iteración expande los contiguos entre el último y el primero de la lista.
 * Vuelve a Invocar la método dibujar para cerrar el ciclo.
 * No contempla las densidades definidas en la matriz (mapa)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import Utils.Utilidades;
import graficos.Punto;
import mapa.MapaInfo;
import model.Lista;
import model.NodoPrio;

public class TpoApp extends Utilidades {
	private MapaInfo mapa;
	private CmcImple cmc;
	private Punto puntoFinal;
	PriorityQueue<NodoPrio> openList = null;
	PriorityQueue<NodoPrio> closedList = null;

	public TpoApp(MapaInfo mapa, CmcImple cmc) {
		this.mapa = mapa;
		this.cmc = cmc;
		demoObtenerCamino();
	}

	// Dibuja el camino
	private void demoObtenerCamino() {
		Punto a = null, b = null;
		Iterator<Punto> iter = mapa.getPuntos().iterator();
		if (iter.hasNext()) {
			a = iter.next();
			while (iter.hasNext()) {
				b = iter.next();
			}
			this.puntoFinal = b;
			recorrer(a, b);
		}
	}

	public void recorrer(Punto puntoOrigen, Punto puntoFinal) {
		NodoPrio origen = new NodoPrio(puntoOrigen, puntoFinal, mapa.getDensidad(puntoOrigen), 0);
		NodoPrio actual = origen;
		NodoPrio aux = null;
		NodoPrio encontrado = null;
		boolean haycamino = true;
		Lista listas = new Lista();
		List<NodoPrio> adyacentes = null;

		actual.setPadre(null);
		while (encontrado == null && haycamino) {
			try {
				adyacentes = obtenerAdyacentes(actual, listas);

				for (NodoPrio ad : adyacentes) {
					if (ad.getPunto().getX() == this.puntoFinal.getX()
							&& ad.getPunto().getY() == this.puntoFinal.getY()) {
						ad.setPadre(actual);
						ad.setCostoDesplazamiento(actual);
						encontrado = ad;
						listas.addClosedList(encontrado);
					} else {
						// Si no esta en lista abierta
						if (!listas.isOpenList(ad.getPunto())) {
							ad.setPadre(actual);
							ad.setCostoDesplazamiento(actual);
							listas.addOpenList(ad);
						} else {
							aux = listas.getNodoAbierto(ad);
							if (aux.getCostoDesplazamiento() > costoDesplazamiento(actual, ad)) {
								listas.removeFromOpenList(aux);
								aux.setPadre(actual);
								aux.setCostoDesplazamiento(actual);
								listas.addOpenList(aux);
							}
						}
					}
				}

				if (encontrado == null) {
					listas.addClosedList(actual);
					listas.removeFromOpenList(actual);

					actual = listas.getMenorCosto();
				}
			} catch (Exception ex) {
				haycamino = false;
				JOptionPane.showMessageDialog(null,
						"No es posible encontrar un camino. - Error:" + ex.getMessage());
			}
		}

		// Armado del Camino utilizando los Padres de cada Nodo ingresado en la Lista
		// Cerrada
		List<Punto> camino = new ArrayList<Punto>();

		actual = encontrado;
		while (actual != null) {
			camino.add(actual.getPunto());
			actual = actual.getPadre();
		}
		// Pintar Camino
		cmc.dibujarCamino(camino, Color.red);

	}

	// Funcion que obtiene listado de Nodos Adyacentes a partir de Punto determinado
	public List<NodoPrio> obtenerAdyacentes(NodoPrio nodo, Lista listas) throws Exception {
		List<NodoPrio> listaAdy = new ArrayList<NodoPrio>();
		NodoPrio nuevo = null;
		Punto pNuevo = null;
		int xx = (int) mapa.LARGO;
		int yy = (int) mapa.ALTO;
		int ini_x = 0;
		int fin_x = 0;
		int ini_y = 0;
		int fin_y = 0;
		int px = (int) nodo.getPunto().getX();
		int py = (int) nodo.getPunto().getY();

		// Defino inicio para recorrido de Adyacentes en ejes X e Y
		ini_x = (px == 0) ? px : px - 1;
		ini_y = (py == 0) ? py : py - 1;

		// Defino fin de recorrido de Adyacentes en ejex X e Y
		fin_x = (px == xx) ? px - 1 : px + 1;
		fin_y = (py == yy) ? py - 1 : py + 1;

		// Recorremos los puntos adyacentes previamente identificados
		for (int x = ini_x; x <= fin_x && x < xx; x++) {
			for (int y = ini_y; y <= fin_y && y < yy; y++) {
				// Verificamos que el punto evaluado sea un punto adyacente y no el Punto
				// original de partida, si estamos sobre el punto de partida, incrementamos y
				// para saltearlo
				if ((y == py) && (x == px))
					y++;
				if (y < yy) { // Verificamos que por saltarnos 'y' no nos hayamos ido del mapa
					pNuevo = new Punto(x, y);
					if ((mapa.getDensidad(x, y) != 4) && (!listas.isClosedList(pNuevo))) { // Si no es infranqueable, lo
																							// añadimos al listado
						nuevo = new NodoPrio(pNuevo, this.puntoFinal, mapa.getDensidad(x, y),
								costoDesplazamiento(nodo, pNuevo));
						listaAdy.add(nuevo);
					}
				}
			}
		}
		return listaAdy;
	}
}