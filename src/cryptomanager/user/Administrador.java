package cryptomanager.user;

import java.util.InputMismatchException;
import java.util.Scanner;

import cryptomanager.Constantes;
import cryptomanager.Criptomoneda;
import cryptomanager.Menu;
import cryptomanager.Mercado;
import cryptomanager.csv.Readable;

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
    
	public int crearCriptomoneda(Readable fileCriptos, Readable fileMercado) {
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
				if (fileCriptos.agregarRegistro(criptomoneda) == Constantes.CRIPTOMONEDA_EXISTENTE) {
					
					String opcionRegistro;
					boolean opcionElegida = false;
					
					System.out.println("La criptomoneda ya existe. ¿Desea modificarla? Y/N");
					while (!opcionElegida) {
						opcionRegistro = userInput.nextLine();
						
						if (opcionRegistro.toLowerCase().equals("y")) {
							
							modificarCriptomoneda(fileCriptos, fileMercado);
							return Constantes.CRIPTOMONEDA_MODIFICADA;
						} 
						else if (opcionRegistro.toLowerCase().equals("n")) {
							opcionElegida = true;
						} 
						else {
							System.out.println("Opción inválida. Elija nuevamente entre Y/N.");
						}
					}

					return Constantes.CRIPTOMONEDA_EXISTENTE;

				} else {
					
					Double valorVariacion = valor * Constantes.VARIACION_CRIPTOMONEDA_INICIAL;
					Mercado mercado = new Mercado(simbolo, Constantes.CAPACIDAD_CRIPTOMONEDA_INICIAL,
							Constantes.VOLUMEN_CRIPTOMONEDA_INICIAL, valorVariacion);
					
					// Verificación de si la moneda está en el mercado.
					if (fileMercado.agregarRegistro(mercado) 
							== Constantes.CRIPTOMONEDA_EN_MERCADO) {
						
						return Constantes.CRIPTOMONEDA_EN_MERCADO; 
					} else {
						creada = true; // Fue creada con éxito.
					}
				}
			} catch (InputMismatchException ex) {
				System.out.println("Datos erroneos.");
				return Constantes.CRIPTOMONEDA_CREADA_ERRONEA;
			}
		}

		return Constantes.CRIPTOMONEDA_CREADA;
	}

	public int modificarCriptomoneda(Readable fileCriptos, Readable fileMercado) {
		return Constantes.CRIPTOMONEDA_MODIFICADA;
	}
}
