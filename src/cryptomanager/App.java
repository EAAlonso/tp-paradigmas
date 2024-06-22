package cryptomanager;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {/*
		boolean opcionMenuElegida = false;

		Menu menu = new Menu(true);
		int nroOpcion = 0;

		Console console = System.console();

		Scanner userInput = new Scanner(System.in);
		Readable fileUsuarios = new UsuarioCSV("usuarios.csv");
		Readable fileCriptos = new CriptomonedaCSV("criptomonedas.csv");
		Readable fileMercados = new MercadoCSV("mercados.csv");
		
		Usuario user = new Usuario();
		
		user = Inicio.iniciarSesion(fileUsuarios);
		
		// System.out.println(((Trader) user).getNroCuenta());

		if (user.getClass().getName().contains("Administrador")) {
			while (menu.getMostrar()) {
				do {
					try {
						System.out.println("Entraste como administrador.");
						Menu.mostrarMenuAdministrador();
						nroOpcion = userInput.nextInt();
						opcionMenuElegida = true;

					} catch (InputMismatchException ex) {
						System.out.println("\u001B[31m" + "Ingrese un número por favor." + "\u001B[0m");
						userInput.next();
					}
				} while (!opcionMenuElegida);

				switch (nroOpcion) {
				case 1:
					System.out.println("Crear Criptomoneda:");
					if(((Administrador) user).crearCriptomoneda(fileCriptos, fileMercados) == Constantes.CRIPTOMONEDA_CREADA) {
						System.out.println("La criptomoneda fue creada exitosamente.");
					}
					break;
				case 2:
					System.out.println("Modificar Criptomoneda:");

					break;
				case 3:
					System.out.println("Elim cripto.");

					break;
				case 4:
					System.out.println("Consultar cripto.");

					break;
				case 5:
					System.out.println("Consultar estado actual del mercado.");

					break;
				case 6:
					System.out.println("Saliendo...");
					System.exit(0);
				}

				System.out.println("Presiona la tecla Enter para continuar...");
				console.readLine(); // Espera que el usuario presione Enter por consola
				opcionMenuElegida = false;
			}

		} else {
			while (menu.getMostrar()) {
				do {
					try {
						System.out.println("Entraste como usuario.");
						Menu.mostrarMenuTrader();
						nroOpcion = userInput.nextInt();
						opcionMenuElegida = true;

					} catch (InputMismatchException ex) {
						System.out.println("\u001B[31m" + "Ingrese un número por favor." + "\u001B[0m");
						userInput.next();
					}
				} while (!opcionMenuElegida);

				switch (nroOpcion) {
				case 1:
					System.out.println("Consultar cripto.");

					break;
				case 2:
					System.out.println("Ver mercado actual.");

					break;
				case 3:
					System.out.println("Obtener recomendaciones.");

					break;
				case 4:
					System.out.println("Comprar Criptomonedas.");

					break;
				case 5:
					System.out.println("Vender Criptomonedas.");

					break;
				case 6:
					System.out.println("Saliendo...");
					System.exit(0);
				}

				System.out.println("Presiona la tecla Enter para continuar...");
				console.readLine(); // Espera que el usuario presione Enter por consola
				opcionMenuElegida = false;
			}

		}
		userInput.close();*/
	}
}
