package cryptomanager;

import java.math.BigDecimal;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private BigDecimal saldoActual;

	public Trader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoActual,
			CSVHandler<String, Criptomoneda> dataCriptomonedas, CSVHandler<String, Mercado> dataMercado) {
		super(nombre, dataCriptomonedas, dataMercado);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
	}

	public void comprarCriptomonedas() {
		Menu.limpiarConsola();
		System.out.println("===== Compra de criptomonedas ===== ");
		String nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");

		boolean criptoValida = false;

		while (!criptoValida) {

			if (this.criptomonedas.existe(nombre)) {
				criptoValida = true;
			} else {
				nombre = Menu.pedirString("La criptomoneda que ingresaste no existe! Vuelva a ingresar el nombre:");				
			}
		}
		
		BigDecimal monto = Menu.pedirBigDecimal("Ingrese cuanto desea comprar (Saldo actual: " + saldoActual + ")");

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
