package cryptomanager;

import java.math.BigDecimal;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private BigDecimal saldoActual;    
	
	public Trader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoActual) {
		super(nombre);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}
	
	@Override
	public Menu newMenu() {
		return new MenuTrader(this);
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
