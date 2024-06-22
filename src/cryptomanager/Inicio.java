package cryptomanager;

import java.math.BigDecimal;
import java.util.Scanner;

public class Inicio {
	private CSVHandler<String,Usuario> usuarios;
	
	public Inicio(CSVHandler<String,Usuario> dataUsuarios) {
		this.usuarios = dataUsuarios;
	}
	
	public Usuario iniciarSesion() {
		Usuario usuario = null;

		while (usuario == null) {
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
					System.out.println("Cerrando...");
					System.exit(0);
					
					break;
				}
				default: // N - Reintenta
				}
			}
		}

		return usuario;
	}
	
	private String pedirNombreUsuario() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Ingrese nombre de usuario: ");
		String userName = userInput.nextLine();
		System.out.println();
		
		return userName;
	}
	
	private String opcionesUsuarioNoEncontrado() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Usuario no encontrado. ¿Desea registrarse? Y(Si) / N(No - Reintentar) / E (Salir): ");
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
				System.out.print("Ingrese numero de cuenta:");
				Long nroCuenta = userInput.nextLong();
				userInput.nextLine();
				
				System.out.println();
				
				System.out.print("Ingrese nombre de banco: ");
				String nombreBanco = userInput.nextLine();
				
				System.out.print("Ingrese saldo actual: ");
				BigDecimal saldoActual = userInput.nextBigDecimal();
				userInput.nextLine();
				
				trader = new Trader(usuario, nroCuenta, nombreBanco, saldoActual);
				registrado = true;
			} catch(Exception e) {
				System.out.println("Dato incorrecto, vuelva a intentar. Presione enter para continuar...");
				userInput.nextLine();
			}
		}
		
		
		
		return trader;
	}
}
