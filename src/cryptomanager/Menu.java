package cryptomanager;

public class Menu {
	private boolean mostrar;
	
	public Menu(boolean mostrar) {
		this.mostrar = mostrar;
	}
	
	public boolean getMostrar() {
		return mostrar;
	}
	
	public static void mostrarMenuAdministrador() {
		System.out.println("\nMENU ADMINISTRADOR\n\n");
		System.out.println("1) Crear Criptomoneda\n" +
					"2) Modificar Criptomoneda\n" +
					"3) Eliminar Criptomoneda\n" +
					"4) Consultar Criptomoneda\n" +
					"5) Consultar Estado actual del mercado\n" +
					"6) Salir\n" +
				"\nIngrese su opcion (1-6): ");
	}
	
	public static void mostrarMenuTrader() {
		System.out.println("1) Consultar Criptomoneda");
		System.out.println("2) Ver mercado actual");
		System.out.println("3) Obtener recomendaciones");
		System.out.println("4) Comprar Criptomonedas");
		System.out.println("5) Vender Criptomonedas");
		System.out.println("6) Salir");
		System.out.println("\nIngrese su opcion (1-6): ");
	}
}
