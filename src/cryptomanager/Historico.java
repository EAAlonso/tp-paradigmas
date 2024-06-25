package cryptomanager;


public class Historico {
	// BTC , 35 USDT , 5 ... ETH , 23
	// Simbolo, Cantidad comprada
	private String simbolo;
	private double cantidad;
	
	public Historico(String simbolo, double cantidad) {
		this.simbolo = simbolo;
		this.cantidad = cantidad;		
	}
	
	@Override
	public String toString() {
		return String.format("%s, %.2f", simbolo, cantidad);
	}
	
	public String getSimbolo() {
		return this.simbolo;
	}
	
	public double getCantidad() {
		return this.cantidad;
	}

}
