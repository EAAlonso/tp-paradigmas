package cryptomanager;

import java.math.BigDecimal;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private BigDecimal saldoActual;
	
    static {
        String titulo = "\nMENU TRADER\n\n";
        String opciones =   "1) Comprar Criptomoneda\n"
                            + "2) Vender Criptomoneda\n"
                            + "3) Consultar Criptomoneda\n" 
                            + "4) Recomendar Criptomonedas\n" 
                            + "5) Consultar Estado actual del mercado\n"
                            + "6) Visualizar archivo de transacciones (histórico)\n" 
                            + "7) Salir\n" 
                            + "\nIngresar opcion (1-7): ";
        
        menu = new Menu(titulo, opciones);
    }
	
	public Trader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoActual) {
		super(nombre);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}
	
	public Long getNroCuenta() {
		return nroCuenta;
	}
	
	public String getNombreBanco() {
		return nombreBanco;
	}
	
	public BigDecimal getSaldoActual() {
		return saldoActual;
	}
	
    @Override
    public void mostrarMenu() {
        System.out.println(menu);
    }
}
