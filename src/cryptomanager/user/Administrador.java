package cryptomanager.user;

import java.util.InputMismatchException;
import java.util.Scanner;

import cryptomanager.Constantes;
import cryptomanager.Criptomoneda;
import cryptomanager.Mercado;
import cryptomanager.csv.CSVManager;
import cryptomanager.csv.Reader;

public class Administrador extends Usuario {
	public Administrador(String nombre) {
		super(nombre);
	}

	public int crearCriptomoneda(CSVManager lector, Reader fileCriptos, Reader fileMercado) {
		boolean creada = false;
		String nombre;
		String simbolo;
		Double valor;
		
		Scanner userInput = new Scanner(System.in);
		while (!creada) {
			try {
				System.out.println("Ingrese el nombre de la criptomoneda:");
				nombre = userInput.nextLine();

				System.out.println("Ingrese el simbolo de la criptomoneda:");
				simbolo = userInput.nextLine();

				System.out.println("Ingrese su valor inicial:");
				valor = userInput.nextDouble();
				userInput.nextLine();

				Criptomoneda criptomoneda = new Criptomoneda(nombre, simbolo, valor.toString());
				if (lector.agregarRegistroCSV(fileCriptos, criptomoneda) == Constantes.CRIPTOMONEDA_EXISTENTE) {
					return Constantes.CRIPTOMONEDA_EXISTENTE;
				} else {
					Double valorVariacion = valor * 1.01;
					Mercado mercado = new Mercado(simbolo, 500.0, 0.0, valorVariacion);
					if(lector.agregarRegistroCSV(fileMercado, mercado) == Constantes.CRIPTOMONEDA_EN_MERCADO) {
						return Constantes.CRIPTOMONEDA_EN_MERCADO;
					} else {
						creada = true;
					}
				}
			} catch (InputMismatchException ex) {
				System.out.println("Datos erroneos.");
				return Constantes.CRIPTOMONEDA_CREADA_ERRONEA;
			}
		}

		return Constantes.CRIPTOMONEDA_CREADA;
	}
}
