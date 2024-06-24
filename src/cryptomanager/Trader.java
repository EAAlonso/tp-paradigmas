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
				if(!archivoTraderExistente()) {
					crearArchivoTrader();
				}
				
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
		BuilderHistorico builderHistorico = new BuilderHistorico();
		CSVHandler<String, Historico> csvHistorico = new CSVHandler<String, Historico>(getNombre().concat(".csv"), builderHistorico);

		for(String simboloCripto : csvHistorico.getContenido().keySet()) {
			System.out.println(csvHistorico.obtenerRegistro(simboloCripto));
		}
		
		/*
		 		for(String simboloCripto : this.mercado.getContenido().keySet()) {
			System.out.println(this.mercado.obtenerRegistro(simboloCripto));
		}
		 */
	}
	
	private void crearRegistroEnHistorico(Criptomoneda criptoBuscada, BigDecimal monto) {
		BuilderHistorico builderHistorico = new BuilderHistorico();
		CSVHandler<String, Historico> csvHistorico = new CSVHandler<String, Historico>(getNombre().concat(".csv"), builderHistorico);
		
		Historico historico = new Historico(criptoBuscada.getSimbolo(), monto.doubleValue());
		csvHistorico.insertarRegistro(historico);
	}
	
	private boolean archivoTraderExistente() {
		return CSVHandler.archivoExistente(getNombre());
	}
	
	private void crearArchivoTrader() {
		CSVHandler.crearArchivo(getNombre());
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
