package cryptomanager.user;

public class Perfil {
	private String nombre;
	
	protected Perfil(String nombre) {
		this.nombre = nombre;
	}
	
	public Perfil() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
