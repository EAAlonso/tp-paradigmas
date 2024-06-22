package cryptomanager;

import java.util.InputMismatchException;
import java.util.Scanner;

import cryptomanager.Menu;

public class Administrador extends Usuario {
    static {
        String titulo = "\nMENU ADMINISTRADOR\n\n";
        String opciones =   "1) Crear Criptomoneda\n" 
                            + "2) Modificar Criptomoneda\n"
                            + "3) Eliminar Criptomoneda\n"
                            + "4) Consultar Criptomoneda\n"
                            + "5) Consultar Estado actual del mercado\n"
                            + "6) Salir\n"
                            + "\nIngresar opcion (1-6): ";
        
        menu = new Menu(titulo, opciones);
    }
	public Administrador(String nombre) {
		super(nombre);
	}

    @Override
    public void mostrarMenu() {
        System.out.println(menu);
    }
}
