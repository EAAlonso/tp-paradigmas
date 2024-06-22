package cryptomanager;

import java.util.InputMismatchException;
import java.util.Scanner;

import cryptomanager.csv.Readable;
import cryptomanager.user.Usuario;
import cryptomanager.user.Trader;

public class Inicio {
	/*public static Usuario iniciarSesion(Readable fileUsuarios) {
		boolean usuarioEncontrado = false;

		Scanner userInput = new Scanner(System.in);
		String userName;

		Usuario user = new Usuario();

		while (!usuarioEncontrado) {
			System.out.println("Ingrese nombre de usuario: ");
			userName = userInput.nextLine();

			if ((user = fileUsuarios.leerArchivo(userName)) == null) {
				System.out.println("Usuario no encontrado. ¿Desea registrarse? Y/N/E");
				registrarse(fileUsuarios);
			} else {
				usuarioEncontrado = true;
			}
		}

		return user;
	}*/

	public static void registrarse(Readable fileUsuarios) {
		boolean registro = false;
		String registrarse;

		Scanner userInput = new Scanner(System.in);

		while (!registro) {
			registrarse = userInput.nextLine();
			if (registrarse.equals("Y") || registrarse.equals("y")) {
				try {
					if ((Trader.crearTraderEnArchivo(fileUsuarios)) == Constantes.USUARIO_EXISTENTE) {
						System.out.println("Ya existe un usuario con ese nombre.");
					} else {
						System.out.println("Usuario registrado con exito.");
					}
				} catch (InputMismatchException ex) {
					System.out.println("Datos erroneos. Presione Enter para continuar..");
					userInput.nextLine();
				} finally {
					registro = true;
				}
			} else if (registrarse.equals("N") || registrarse.equals("n")) {
				registro = true;
			} else if (registrarse.equals("E") || registrarse.equals("e")) {
				System.out.println("Saliendo...");
				System.exit(0);
			} else {
				System.out.println("No se reconoce la opción elegida. Por favor, elija entre Y, N o E.");
			}
		}
	}
}
