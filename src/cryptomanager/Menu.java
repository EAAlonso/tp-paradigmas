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
		System.out.println("1) Crear Criptomoneda");
		System.out.println("2) Modificar Criptomoneda");
		System.out.println("3) Eliminar Criptomoneda");
		System.out.println("4) Consultar Criptomoneda");
		System.out.println("5) Consultar Estado actual del mercado");
		System.out.println("6) Salir");
		System.out.println("\nIngrese su opcion (1-6): ");
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
