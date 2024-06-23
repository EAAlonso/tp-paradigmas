package cryptomanager;

import java.math.BigDecimal;

public class Criptomoneda {
	private String nombre;
	private String simbolo;
	private BigDecimal valor;
	
	public Criptomoneda(String nombre, String simbolo, BigDecimal valor) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.valor = valor;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getSimbolo() {
		return simbolo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s - %.2f", simbolo, nombre, valor);
	}
	
	
}
