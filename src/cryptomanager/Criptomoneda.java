package cryptomanager;

public class Criptomoneda {
	private String nombre;
	private String simbolo;
	private String valor;
	
	public Criptomoneda(String nombre, String simbolo, String valor) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
}
