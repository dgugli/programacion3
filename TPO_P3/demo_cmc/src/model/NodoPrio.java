package model;

import graficos.Punto;

public class NodoPrio implements Comparable<NodoPrio> {
	private NodoPrio padre;
	private Punto punto;
	private Punto puntoOrigen;
	private Punto puntoDestino;
	private int densidad;
	private double costoRecto;
	private double costoDesplazamiento;

	public NodoPrio(Punto p, Punto pDestino, int densidad, double costoDesplazamiento) {
		this.punto = p;
		this.puntoDestino = pDestino;
		this.densidad = densidad;
		this.costoRecto = (((Math.abs((pDestino.getX() - p.getX()))) + (Math.abs((pDestino.getX() - p.getX())))) * 10);
		this.costoDesplazamiento = costoDesplazamiento;
	}

	public NodoPrio getPadre() {
		return padre;
	}

	public void setPadre(NodoPrio padre) {
		this.padre = padre;
	}

	public void setOrigen(Punto origen) {
		this.puntoOrigen = origen;
	}

	public void setDestino(Punto destino) {
		this.puntoDestino = destino;
	}

	public Punto getOrigen() {
		return this.puntoOrigen;
	}

	public Punto getDestino() {
		return this.puntoDestino;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public int getDensidad() {
		return densidad;
	}

	public void setDensidad(int densidad) {
		this.densidad = densidad;
	}

	public double getCostoRecto() {
		return costoRecto;
	}

	public void setCostoRecto(int costoRecto) {
		this.costoRecto = costoRecto;
	}

	public double getCostoDesplazamiento() {
		return costoDesplazamiento;
	}

	public Punto getPuntoOrigen() {
		return puntoOrigen;
	}

	public void setPuntoOrigen(Punto puntoOrigen) {
		this.puntoOrigen = puntoOrigen;
	}

	public Punto getPuntoDestino() {
		return puntoDestino;
	}

	public void setPuntoDestino(Punto puntoDestino) {
		this.puntoDestino = puntoDestino;
	}

	public void setCostoRecto(double costoRecto) {
		this.costoRecto = costoRecto;
	}

	public void setCostoDesplazamiento(double costoDesplazamiento) {
		this.costoDesplazamiento = costoDesplazamiento;
	}

	public double getCostoTotal() {
		return (densidad == 0) ? this.costoDesplazamiento + this.costoRecto
				: densidad * (this.costoDesplazamiento + this.costoRecto);
	}

	public void setCostoDesplazamiento(NodoPrio origen) {
		this.costoDesplazamiento = (origen.getPunto().x != this.getPunto().x)
				&& (origen.getPunto().y != this.getPunto().y) ? origen.getCostoDesplazamiento() + 14
						: origen.getCostoDesplazamiento() + 10;
	}

	/**
	 * Se utiliza el metodo compareTo para ordenar el Nodo por su costo,
	 * posicionando aquel Nodo cuyo costo sea el menor de los ingresados en la
	 * cola.
	 */
	@Override
	public int compareTo(NodoPrio nodo) {
		if (this.getCostoTotal() < nodo.getCostoTotal())
			return -1;
		else if (this.getCostoTotal() > nodo.getCostoTotal())
			return 1;
		else {

		}
		return 0;
	}

}
