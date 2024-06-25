package cryptomanager;

public class MenuTrader extends Menu {

    private static String titulo = "MENU TRADER";
    private static String[] opciones =   new String[]{
    		"Comprar Criptomoneda",
    		"Vender Criptomoneda",
    		"Consultar Criptomonedas",
    		"Recomendar Criptomonedas",
    		"Consultar Estado actual del mercado",
    		"Visualizar archivo de transacciones (histórico)",
    		"Salir"
    };
    
    public static final int OPCION_MINIMO = 1;
    public static final int OPCION_MAXIMO = 7;
    
    public static final int OPCION_COMPRAR_CRIPTOMONEDA = 1;
    public static final int OPCION_VENDER_CRIPTOMONEDA = 2;
    public static final int OPCION_CONSULTAR_CRIPTOMONEDAS = 3;
    public static final int OPCION_RECOMENDAR_CRIPTOMONEDA = 4;
    public static final int OPCION_CONSULTAR_MERCADO = 5;
    public static final int OPCION_VISUALIZAR_HISTORICO = 6;
    public static final int OPCION_SALIR = 7;
    
    
    private Trader usuario;
    
	public MenuTrader(Trader usuario) {
		super(titulo, opciones);
		this.usuario = usuario;
	}
	
	@Override
	public void iniciar() {
		boolean mostrarOpcionInvalida = false;
		boolean finPrograma  = false;
		
		while(!finPrograma) {
			int opcion = this.pedirIngresoOpcion(mostrarOpcionInvalida);
			
			switch(opcion) {
			case OPCION_COMPRAR_CRIPTOMONEDA:
				this.usuario.comprarCriptomonedas();
				break;
			case OPCION_VENDER_CRIPTOMONEDA:
				this.usuario.venderCriptomonedas();
				break;
			case OPCION_CONSULTAR_CRIPTOMONEDAS:
				this.usuario.consultarCriptomoneda();
				break;
			case OPCION_RECOMENDAR_CRIPTOMONEDA:
				this.usuario.recomendarCriptomoneda();
				break;
			case OPCION_CONSULTAR_MERCADO:
				this.usuario.consultarMercado();
				break;
			case OPCION_VISUALIZAR_HISTORICO:
				this.usuario.visualizarHistorico();
				break;
			case OPCION_SALIR:
				finPrograma = true;
				System.out.println("\n+----- Cerrando... -----+");
				
				break;
				default:
			}
			
			mostrarOpcionInvalida = opcion < OPCION_MINIMO || opcion > OPCION_MAXIMO;
		}
	}
}
