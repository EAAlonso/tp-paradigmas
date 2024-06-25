package cryptomanager;

public class App {

	private static final String FILE_PATH_CRIPTOMONEDAS = "criptomonedas.csv";
	private static final String FILE_PATH_MERCADO = "mercados.csv";
	private static final String FILE_PATH_USUARIO = "usuarios.csv";
	
	public static void main(String[] args) {
		
		BuilderCriptomoneda builderCriptomoneda = new BuilderCriptomoneda();
		CSVHandler<String, Criptomoneda> csvCriptomoneda = new CSVHandler<String, Criptomoneda>(FILE_PATH_CRIPTOMONEDAS, builderCriptomoneda);
		
		BuilderMercado builderMercado = new BuilderMercado();
		CSVHandler<String, Mercado> csvMercado = new CSVHandler<String, Mercado>(FILE_PATH_MERCADO, builderMercado);
		
		BuilderUsuario builderUsuario = new BuilderUsuario(csvCriptomoneda, csvMercado);
		CSVHandler<String, Usuario> csvUsuario = new CSVHandler<String, Usuario>(FILE_PATH_USUARIO, builderUsuario);
		
		Inicio inicio = new Inicio(csvUsuario, builderUsuario);
		
		Usuario usuarioSesion = inicio.iniciarSesion();
		System.out.println("\n+----- SESION INICIADA -----+\nUsuario: " + usuarioSesion.getNombre());
		//System.out.println("Usuario iniciado sesion: " + usuarioSesion.getNombre());
		
		usuarioSesion.newMenu().iniciar();
		
		// TODO: Cerrar System.in.close();
		// TODO: New Usuario(csvCriptomoneda...);
		// TODO: csvCriptomoneda.close() al salir
	}
}
