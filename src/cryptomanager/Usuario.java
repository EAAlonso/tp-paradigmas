package cryptomanager;

public abstract class Usuario {
	
	private String nombre;
	protected Menu menu;
	
	protected Usuario(String nombre, Menu menu) {
		this.nombre = nombre;
		this.menu = menu;
	}
	
	public Usuario() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void mostrarMenu() {
		System.out.println(this.menu);
	};
	
	protected void consultarCriptomoneda() {
		// implementación
	}
	
	protected void verMercadoActual() {
		// implementación
	}
}
