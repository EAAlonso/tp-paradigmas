package cryptomanager;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class Menu {

	private String titulo;
	private String[] opciones;
	private static Scanner userInput = new Scanner(System.in);

	public Menu(String titulo, String[] opciones) {
		this.titulo = titulo;
		this.opciones = opciones;
	}

	public abstract void iniciar();

	protected int pedirIngresoOpcion(boolean opcionInvalida) {
		limpiarConsola();

		if (opcionInvalida) {
			this.mensajeOpcionInvalida();
		}

		this.listarOpciones();
		int opcion = pedirInt("\n+ Ingrese un numero de opcion y luego presione enter: ");
		return opcion;
	}

	public static void limpiarConsola() {
		for (int clear = 0; clear < 10; clear++) {
			System.out.println("\n");
		}
	}

	public static int pedirInt(String mensaje) {
		int valor = 0;
		boolean caracterValido = false;

		while (!caracterValido) {
			try {
				System.out.print(mensaje);
				String input = userInput.nextLine();
				valor = Integer.parseInt(input);

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("##### Valor invalido. Intente nuevamente. #####");
			}
		}

		return valor;
	}

	public static String pedirString(String mensaje) {
		String valor = "";
		boolean caracterValido = false;

		while (!caracterValido) {
			try {
				System.out.print(mensaje);
				valor = userInput.next();
				userInput.nextLine();

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("##### Valor invalido. Intente nuevamente. #####");
			}
		}

		return valor;
	}
	
	public static double pedirDouble(String mensaje) {
		double valor = 0;
		boolean caracterValido = false;

		while (!caracterValido) {
			try {
				System.out.print(mensaje);
				valor = userInput.nextDouble();
				userInput.nextLine();

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("##### Valor invalido. Intente nuevamente. #####");
			}
		}

		return valor;
	}

	public static BigDecimal pedirBigDecimal(String mensaje) {
		BigDecimal valor = null;
		boolean caracterValido = false;

		while (!caracterValido) {
			try {
				System.out.print(mensaje);
				valor = userInput.nextBigDecimal();
				userInput.nextLine(); // Limpieza de buffer

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("##### Valor invalido. Intente nuevamente. #####");
				userInput.nextLine(); // Limpieza de buffer
			}
		}

		return valor;
	}

	public static boolean pedirYoN(String mensaje) {
		String valor = "";
		boolean caracterValido = false;

		while (!caracterValido) {
			valor = Menu.pedirString(mensaje);
			
			if (valor.toUpperCase().equals("Y")|| valor.toUpperCase().equals("N")) {
				caracterValido = true;
			} else {
				System.out.println("##### Caracter invalido. #####");
			}

		}

		return valor.toUpperCase().equals("Y");
	}

	public static void esperarTecla() {
		try {
			System.in.read();
			userInput.nextLine();
		} catch (Exception e) {
		}
	}

	private void mensajeOpcionInvalida() {
		System.out.println("##### Opcion invalida. Intente nuevamente. #####\n");
	}

	private void listarOpciones() {
		System.out.println("+----- " + this.titulo + " -----+");

		for (int i = 0; i < this.opciones.length; i++) {
			System.out.println((i + 1) + ") " + this.opciones[i]);
		}
	}
}
