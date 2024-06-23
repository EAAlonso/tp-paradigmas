package cryptomanager;

public class Administrador extends Usuario {
	
	public Administrador(String nombre) {
		super(nombre);
	}
	
	@Override
	public Menu newMenu() {
		return new MenuAdministrador(this);
	}
}
