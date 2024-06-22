package cryptomanager.user;

import cryptomanager.Menu;

public abstract class Usuario {
	
	private String nombre;
	protected static Menu menu;
	
	protected Usuario(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuario() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract void mostrarMenu();
	
	protected void consultarCriptomoneda() {
		// implementación
	}
	
	protected void verMercadoActual() {
		// implementación
	}
}
