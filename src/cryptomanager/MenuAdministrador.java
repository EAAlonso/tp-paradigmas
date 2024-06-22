package cryptomanager;

public class MenuAdministrador extends Menu {
    static String titulo = "\nMENU ADMINISTRADOR\n\n";
    static String opciones =   "1) Crear Criptomoneda\n" 
                        + "2) Modificar Criptomoneda\n"
                        + "3) Eliminar Criptomoneda\n"
                        + "4) Consultar Criptomoneda\n"
                        + "5) Consultar Estado actual del mercado\n"
                        + "6) Salir\n"
                        + "\nIngresar opcion (1-6): ";
	public MenuAdministrador() {
		super(titulo, opciones);
	}
}
