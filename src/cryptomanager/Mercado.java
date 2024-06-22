package cryptomanager;

public class Mercado {
	private String simbolo;
	private double capacidad;
	private double volumen;
	private double variacion;
	
	public Mercado(String simbolo, double capacidad, double volumen, double variacion) {
		this.simbolo = simbolo;
		this.capacidad = capacidad;
		this.volumen = volumen;
		this.variacion = variacion;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public Double getCapacidad() {
		return capacidad;
	}
	
	public Double getVolumen() {
		return volumen;
	}
	
	public Double getVariacion() {
		return variacion;
	}
}