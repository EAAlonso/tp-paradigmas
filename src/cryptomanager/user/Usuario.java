package cryptomanager.user;

public class Usuario {
	private String nombre;
	
	protected Usuario(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuario() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
