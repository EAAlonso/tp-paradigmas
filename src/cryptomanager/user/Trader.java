package cryptomanager.user;

import java.util.Scanner;

import cryptomanager.Constantes;
import cryptomanager.csv.CSVManager;
import cryptomanager.csv.Reader;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private String saldoActual;
	
	public Trader(String nombre, Long nroCuenta, String nombreBanco, String saldoActual) {
		super(nombre);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}
	
	public static int crearTraderEnArchivo(CSVManager lector, Reader fileUsuarios) {
		Scanner userInput = new Scanner(System.in);
		String nombre;
		Long nroCuenta;
		String nombreBanco;
		Double saldo;

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
		if (lector.agregarRegistroCSV(fileUsuarios, nuevoTrader) == Constantes.USUARIO_EXISTENTE) {
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
