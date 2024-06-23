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
		int opcion = pedirInt("Ingrese un numero de opción y luego presione enter...: ");
		return opcion;
	}

	public static void limpiarConsola() {
		for (int clear = 0; clear < 100; clear++) {
			System.out.println("\b");
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
				System.out.println();

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("*** Valor entero invalido! Vuelva a ingresar *** ");
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
				System.out.println();

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("*** Valor invalido! Vuelva a ingresar *** ");
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
				System.out.println();

				caracterValido = true;
			} catch (Exception e) {
				System.out.println("*** Valor invalido! Vuelva a ingresar *** ");
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
				System.out.println("Caracter invalido!");
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
		System.out.println("*** Opción invalida! Vuelva a ingresar una opción *** ");
	}

	private void listarOpciones() {
		System.out.println("---- " + this.titulo + " ----");

		for (int i = 0; i < this.opciones.length; i++) {
			System.out.println((i + 1) + ") " + this.opciones[i]);
		}
	}

}