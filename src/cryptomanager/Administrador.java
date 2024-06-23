package cryptomanager;

public class Administrador extends Usuario {
	
    protected static Menu menu = new MenuAdministrador();

	public Administrador(String nombre) {
		super(nombre);
	}
	
	@Override
	public Menu newMenu() {
		return menu;
	}
}
