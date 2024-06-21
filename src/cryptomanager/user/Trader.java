package cryptomanager.user;

import java.util.Scanner;

import cryptomanager.Constantes;
import cryptomanager.csv.Readable;

public class Trader extends Perfil {
	private Long nroCuenta;
	private String nombreBanco;
	private String saldoActual;
	
	public Trader(String nombre, Long nroCuenta, String nombreBanco, String saldoActual) {
		super(nombre);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}
	/**
	 * 
	 * @param lector
	 * @param fileUsuarios
	 * @return
	 */
	public static int crearTraderEnArchivo(Readable fileUsuarios) {
		Scanner userInput = new Scanner(System.in); // Scanner para ingresar datos por teclado
		String nombre;
		Long nroCuenta;
		String nombreBanco;
		Double saldo;

		// Agregar bloque try catch con excepcion InputMismatch
		// Agregar dos metodos, uno que ingrese y otro que verifique ? 
		System.out.println("Ingrese su nombre de usuario a registrar:");
		nombre = userInput.nextLine();

		System.out.println("Ingrese número de cuenta:");
		nroCuenta = userInput.nextLong();
		userInput.nextLine();

		System.out.println("Ingrese el nombre del banco:");
		nombreBanco = userInput.nextLine();

		System.out.println("Ingrese su saldo:");
		saldo = userInput.nextDouble();
		userInput.nextLine();
		
		Trader nuevoTrader = new Trader(nombre, nroCuenta, nombreBanco, saldo.toString());
		if (fileUsuarios.agregarRegistro(nuevoTrader) == Constantes.USUARIO_EXISTENTE) {
			return Constantes.USUARIO_EXISTENTE;
		}
		
		return Constantes.USUARIO_CREADO;
	}
	
	public Long getNroCuenta() {
		return nroCuenta;
	}
	
	public String getNombreBanco() {
		return nombreBanco;
	}
	
	public String getSaldoActual() {
		return saldoActual;
	}
}
