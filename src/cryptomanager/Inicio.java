package cryptomanager;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressWarnings("resource")

public class Inicio {
	private CSVHandler<String,Usuario> usuarios;
	private BuilderUsuario constructorUsuario;
	
	public Inicio(CSVHandler<String,Usuario> dataUsuarios, BuilderUsuario constructorUsuario) {
		this.usuarios = dataUsuarios;
		this.constructorUsuario = constructorUsuario;
	}
	
	private boolean strValido(String str) {
		String regex = "^(?!\\d+$)[a-zA-Z0-9 ]+$";
        
	        // Crear un patron
	        Pattern pattern = Pattern.compile(regex);
	        
	        // Comparar el string con el patron
	        Matcher matcher = pattern.matcher(str);
	        
	        // True si coincide, false si no
	        return matcher.matches() && !str.isBlank();
	}
	
	public Usuario iniciarSesion() {
		Usuario usuario = null;
		
		System.out.println("+----- INICIO SESION -----+");

		while (usuario == null) {
			try {
				String nombreUsuario = this.pedirNombreUsuario();
				usuario = this.usuarios.obtenerRegistro(nombreUsuario);
	
				if (usuario == null) {
					String opcion = this.opcionesUsuarioNoEncontrado();
					switch (opcion.toUpperCase()) {
					case "Y": {
						usuario = registrarse(nombreUsuario);
						
						break;
					}
					case "E": {
						System.out.println("+----- Cerrando... -----+");
						System.exit(0);
						
						break;
					}
					default: // N - Reintenta
					}
				}
			} catch (Exception e){
				System.out.println("##### Dato incorrecto, vuelva a intentar. Presione enter para continuar... #####\n");
			}
		}

		return usuario;
	}
	
	private String pedirNombreUsuario() throws Exception {
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("+ Ingrese nombre de usuario: ");
		String userName = userInput.nextLine();
		if(!strValido(userName))
			throw new Exception();
		
		System.out.println();
		
		return userName;
	}
	
	private String opcionesUsuarioNoEncontrado() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Usuario no encontrado. ¿Desea registrarse?\n+ Y - (Si)\n+ N - (No)\n+ E - (Salir)\nOpcion: ");
		String opcion = userInput.nextLine();
		System.out.println();
		
		return opcion;
	}

	private Trader registrarse(String usuario) {
		Trader trader = obtenerDatosTraderRegistro(usuario);
		
		this.usuarios.insertarRegistro(trader);
		
		return trader;
	}
	
	private Trader obtenerDatosTraderRegistro(String usuario) {
		boolean registrado = false;
		Scanner userInput = new Scanner(System.in);
		Trader trader = null;

		while (!registrado) {
			try {
				System.out.println("+----- REGISTRANDO USUARIO -----+");
				
				System.out.print("+ Ingrese numero de cuenta: ");
				Long nroCuenta = userInput.nextLong();
				userInput.nextLine();
				if(nroCuenta < 0)
					throw new Exception();
				
				System.out.print("+ Ingrese nombre de banco: ");
				String nombreBanco = userInput.nextLine();
				if(!strValido(nombreBanco))
					throw new Exception();

				System.out.print("+ Ingrese saldo actual: ");
				BigDecimal saldoActual = userInput.nextBigDecimal();
				userInput.nextLine();
				if(saldoActual.compareTo(BigDecimal.ZERO) < 0)
					throw new Exception();

				trader = this.constructorUsuario.NewTrader(usuario, nroCuenta, nombreBanco, saldoActual);
				registrado = true;
			} catch(Exception e) {
				System.out.println("##### Dato incorrecto, vuelva a intentar. Presione enter para continuar... #####\n");
				userInput.nextLine();
			}
		}
		
		return trader;
	}
	
	public static void cerrarSesion(
			Usuario usuario, 
			CSVHandler<String, Criptomoneda> singletonHandlerCriptomoneda,
			CSVHandler<String, Mercado> singletonHandlerMercado,
			CSVHandler<String, Usuario> singletonHandlerUsuario) {
		actualizarUsuarioCSV(usuario, singletonHandlerUsuario); // Actualizo estado del usuario actual
		usuario.cerrarSesion(); // Actualizo historico del trader
		singletonHandlerCriptomoneda.close();
		singletonHandlerMercado.close();
		singletonHandlerUsuario.close();
		
	}
	
	private static void actualizarUsuarioCSV(Usuario usuario, CSVHandler<String, Usuario> singletonHandlerUsuario) {
		if (singletonHandlerUsuario.existe(usuario.getNombre())) {
			singletonHandlerUsuario.actualizarRegistro(usuario.getNombre(), usuario);
			return;
		}
		
		singletonHandlerUsuario.insertarRegistro(usuario); // Caso de que se registró en esta sesión
	}
}
