package cryptomanager;

public class App {

	private static final String FILE_PATH_CRIPTOMONEDAS = "archivos/criptomonedas.csv";
	private static final String FILE_PATH_MERCADO = "archivos/mercados.csv";
	private static final String FILE_PATH_USUARIO = "archivos/usuarios.csv";
	
	public static void main(String[] args) {
		
		BuilderCriptomoneda builderCriptomoneda = new BuilderCriptomoneda();
		CSVHandler<String, Criptomoneda> csvCriptomoneda = new CSVHandler<String, Criptomoneda>(FILE_PATH_CRIPTOMONEDAS, builderCriptomoneda);
		
		BuilderMercado builderMercado = new BuilderMercado();
		CSVHandler<String, Mercado> csvMercado = new CSVHandler<String, Mercado>(FILE_PATH_MERCADO, builderMercado);
		
		BuilderHistorico builderHistorico = new BuilderHistorico();
		
		BuilderUsuario builderUsuario = new BuilderUsuario(csvCriptomoneda, csvMercado, builderHistorico);
		CSVHandler<String, Usuario> csvUsuario = new CSVHandler<String, Usuario>(FILE_PATH_USUARIO, builderUsuario);
		
		Inicio inicio = new Inicio(csvUsuario, builderUsuario);
		Usuario usuarioSesion = inicio.iniciarSesion();
		usuarioSesion.newMenu().iniciar();
		Inicio.cerrarSesion(usuarioSesion, csvCriptomoneda, csvMercado, csvUsuario);
	}
}
