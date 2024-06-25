package cryptomanager;

public class MenuAdministrador extends Menu {
    static String titulo = "MENU ADMINISTRADOR";
    static String[] opciones =  new String[] {
    		"Crear Criptomoneda",
    		"Modificar Criptomoneda",
    		"Eliminar Criptomoneda",
    		"Consultar criptomoneda",
    		"Consultar Estado actual del mercado",
    		"Salir"
    };
    
    public static final int OPCION_MINIMO = 1;
    public static final int OPCION_MAXIMO = 6;
    
    public static final int OPCION_CREAR_CRIPTOMONEDA = 1;
    public static final int OPCION_MODIFICAR_CRIPTOMONEDA = 2;
    public static final int OPCION_ELIMINAR_CRIPTOMONEDA = 3;
    public static final int OPCION_CONSULTAR_CRIPTOMONEDA = 4;
    public static final int OPCION_CONSULTAR_ESTADO_MERCADO = 5;
    public static final int OPCION_SALIR = 6;
    
    private Administrador usuario;
	public MenuAdministrador(Administrador usuario) {		
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
			case OPCION_CREAR_CRIPTOMONEDA:
				this.usuario.crearCriptomoneda();
				break;
			case OPCION_MODIFICAR_CRIPTOMONEDA:
				this.usuario.editarCriptomoneda();
				break;
			case OPCION_ELIMINAR_CRIPTOMONEDA:
				this.usuario.eliminarCriptomoneda();
				break;
			case OPCION_CONSULTAR_CRIPTOMONEDA:
				this.usuario.consultarCriptomoneda();
				break;
			case OPCION_CONSULTAR_ESTADO_MERCADO:
				this.usuario.consultarMercado();
				break;
			case OPCION_SALIR:
				finPrograma = true;
				System.out.println("\n+----- Cerrando... -----+");
				// TODO: Verificar que al salir, en el main se haga el cierre de archivos, pisado de la data del map, etc.
				break;
				default:
			}
			
			mostrarOpcionInvalida = opcion < OPCION_MINIMO || opcion > OPCION_MAXIMO;
		}
	}
}
