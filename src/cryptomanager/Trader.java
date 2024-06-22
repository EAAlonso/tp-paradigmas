package cryptomanager;

import java.math.BigDecimal;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private BigDecimal saldoActual;
	
    protected static Menu menu = new MenuTrader();
    
	
	public Trader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoActual) {
		super(nombre, Trader.menu);
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
}
