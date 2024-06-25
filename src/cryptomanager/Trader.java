package cryptomanager;

import java.math.BigDecimal;

public class Trader extends Usuario {
	private Long nroCuenta;
	private String nombreBanco;
	private BigDecimal saldoActual;
	
	CSVHandler<String, Historico> dataHistorico;

	public Trader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoActual,
			CSVHandler<String, Criptomoneda> dataCriptomonedas, CSVHandler<String, Mercado> dataMercado,
			CSVHandler<String, Historico> dataHistorico) {
		super(nombre, dataCriptomonedas, dataMercado);
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
		this.saldoActual = saldoActual;
		
		this.dataHistorico = dataHistorico;
	}

	public void comprarCriptomonedas() {
		Menu.limpiarConsola();
		System.out.println("===== Compra de criptomonedas ===== ");
		String nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");

		boolean criptoValida = false;
		
		Criptomoneda criptoBuscada = null;
		Mercado criptoEnMercado = null;

		while (!criptoValida) {

			if (this.criptomonedas.existe(nombre)) {
				criptoBuscada = this.criptomonedas.obtenerRegistro(nombre);
				criptoEnMercado = this.mercado.obtenerRegistro(criptoBuscada.getSimbolo());
				criptoValida = true;
			} else {
				nombre = Menu.pedirString("La criptomoneda que ingresaste no existe! Vuelva a ingresar el nombre:");				
			}
		}
		
		BigDecimal monto = Menu.pedirBigDecimal("Ingrese cuanto desea comprar (Saldo actual: " + saldoActual + "): ");
		validarMontoSaldo(monto, criptoBuscada);
		
		if(criptoEnMercado == null) {
			throw new RuntimeException("Error al buscar la criptomoneda en el mercado.");
		}
		
		if(puedeComprarPorCapacidad(monto, criptoEnMercado)) {
			// Logica de puede comprar porque hay en mercado
					
				// Crea el historico si no existe
				crearRegistroEnHistorico(criptoBuscada, monto);
				efectuarCompra(monto, criptoBuscada); // Reduce su saldo

				mercado.actualizarRegistro(criptoEnMercado.getSimbolo(),
						new Mercado(criptoEnMercado.getSimbolo(),
								criptoEnMercado.getCapacidad() - monto.doubleValue(),
								criptoEnMercado.getVolumen(),
								criptoEnMercado.getVariacion()));
			
		} else {
			// Logica no puede comprar porque no hay suficiente
			System.out.println("No hay esa capacidad para comprar.");
		}
		
		Menu.esperarTecla();
	}
	
	public void venderCriptomonedas() {
		Menu.limpiarConsola();
		System.out.println("===== Venta de criptomonedas ===== ");
		String nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");

		boolean criptoValida = false;
		
		Criptomoneda criptoBuscada = null;
		Mercado criptoEnMercado = null;

		while (!criptoValida) {

			if (this.criptomonedas.existe(nombre)) {
				criptoBuscada = this.criptomonedas.obtenerRegistro(nombre);
				criptoEnMercado = this.mercado.obtenerRegistro(criptoBuscada.getSimbolo());
				criptoValida = true;
			} else {
				nombre = Menu.pedirString("La criptomoneda que ingresaste no existe! Vuelva a ingresar el nombre:");				
			}
		}
		
		consultarHistoricoSinMenu();
		
		Menu.esperarTecla();
	}
	
	private void consultarHistoricoSinMenu() {
		if(!archivoTraderExistente()) {
			System.out.println("No tienes ninguna criptomoneda.");
		}

		for(String simboloCripto : dataHistorico.getContenido().keySet()) {
			System.out.println(dataHistorico.obtenerRegistro(simboloCripto));
		}
		
		/*
		 		for(String simboloCripto : this.mercado.getContenido().keySet()) {
			System.out.println(this.mercado.obtenerRegistro(simboloCripto));
		}
		 */
	}
	
	private void crearRegistroEnHistorico(Criptomoneda criptoBuscada, BigDecimal monto) {
		Historico historico = null;
		
		if(dataHistorico.existe(criptoBuscada.getSimbolo())) {
			historico = dataHistorico.obtenerRegistro(criptoBuscada.getSimbolo());
			dataHistorico.actualizarRegistro(criptoBuscada.getSimbolo(), new Historico(criptoBuscada.getSimbolo(), monto.doubleValue() + historico.getCantidad()));
			return;
		}
		
		historico = new Historico(criptoBuscada.getSimbolo(), monto.doubleValue());
		
		dataHistorico.insertarRegistro(historico);
	}
	
	private boolean archivoTraderExistente() {
		return CSVHandler.archivoCSVExistente(getNombre());
	}
	
	private void crearArchivoTrader() {
		CSVHandler.crearArchivoCSV(getNombre());
	}
	
	private void efectuarCompra(BigDecimal monto, Criptomoneda criptoBuscada) {
		this.saldoActual = this.saldoActual.subtract(criptoBuscada.getValor().multiply(monto));
	}
	
	private boolean puedeComprarPorCapacidad(BigDecimal monto, Mercado criptoEnMercado) {
		return monto.doubleValue() < criptoEnMercado.getCapacidad();
	}
	
	private void validarMontoSaldo(BigDecimal monto, Criptomoneda criptomoneda) {
		boolean montoValido = false;
		
		while(!montoValido) {
			if(monto.multiply(criptomoneda.getValor()).compareTo(saldoActual) < 0) {
				return;
			}
			
			monto = Menu.pedirBigDecimal("El monto que ingresaste supera tu saldo actual ( " + saldoActual + " ). Vuelva a ingresarlo: ");
		}
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
