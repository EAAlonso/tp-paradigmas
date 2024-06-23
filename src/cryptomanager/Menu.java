package cryptomanager;

import java.util.Scanner;

public abstract class Menu {

	private String titulo;
	private String[] opciones;

	public Menu(String titulo, String[] opciones) {
		this.titulo = titulo;
		this.opciones = opciones;
	}

	public abstract void iniciar();

	protected int pedirIngresoOpcion(boolean opcionInvalida) {
		this.limpiarConsola();
		
		if(opcionInvalida) {
			this.mensajeOpcionInvalida();
		}
		
		this.listarOpciones();
		
		boolean caracterValido = false;
		int opcion = 0;
		
		while(!caracterValido) {
			try {
				Scanner userInput = new Scanner(System.in);
				System.out.print("Ingrese un numero de opción y luego presione enter...: ");
				opcion = userInput.nextInt();
				System.out.println();
				
				caracterValido = true;
			} catch(Exception e) {
				this.mensajeCaracterInvalido();
			}
		}


		return opcion;
	}
	
	private void limpiarConsola() {
		for(int clear = 0; clear < 100; clear++) {
		    System.out.println("\b") ;
		}
	}
	
	private void mensajeOpcionInvalida() {
		System.out.println("*** Opción invalida! Vuelva a ingresar una opción *** ");
	}
	
	private void mensajeCaracterInvalido() {
		System.out.println("*** Caracter invalido! Ingrese un numero *** ");
	}

	private void listarOpciones() {
		System.out.println("---- " + this.titulo + " ----");
		
		for(int i = 0; i < this.opciones.length; i++) {
			System.out.println( (i+1) + ") "+ this.opciones[i]);
		}
	}

}