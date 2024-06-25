package cryptomanager;


public class Historico {
	private String simbolo;
	private double cantidad;
	
	public Historico(String simbolo, double cantidad) {
		this.simbolo = simbolo;
		this.cantidad = cantidad;		
	}
	
	@Override
	public String toString() {
		return String.format("[SIM: %s | CANT: %.2f]", simbolo, cantidad);
	}
	
	public String getSimbolo() {
		return this.simbolo;
	}
	
	public double getCantidad() {
		return this.cantidad;
	}

}
