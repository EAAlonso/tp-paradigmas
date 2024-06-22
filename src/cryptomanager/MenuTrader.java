package cryptomanager;

public class MenuTrader extends Menu {

    private static String titulo = "\nMENU TRADER\n\n";
    private static String opciones =   "1) Comprar Criptomoneda\n"
                        + "2) Vender Criptomoneda\n"
                        + "3) Consultar Criptomoneda\n" 
                        + "4) Recomendar Criptomonedas\n" 
                        + "5) Consultar Estado actual del mercado\n"
                        + "6) Visualizar archivo de transacciones (histórico)\n" 
                        + "7) Salir\n" 
                        + "\nIngresar opcion (1-7): ";
    
	public MenuTrader() {
		super(titulo, opciones);
	}
}
