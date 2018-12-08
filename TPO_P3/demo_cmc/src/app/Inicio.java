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
package app;

import vista.CanvasFrame;

public class Inicio {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	            CanvasFrame frame = new CanvasFrame("Progra III");
	            frame.configurar();
	            frame.presentar();
	          };
		});
	}
}
