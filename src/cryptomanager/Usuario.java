package cryptomanager;

public abstract class Usuario {
	
	private String nombre;
	
	protected Usuario(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuario() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract Menu newMenu();
	
	protected void consultarCriptomoneda() {
		// implementación
	}
	
	protected void verMercadoActual() {
		// implementación
	}
}
