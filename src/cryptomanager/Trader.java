package cryptomanager;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Trader extends Usuario {
	private static final int FACTOR_PORCENTAJE = 100;
	private static final double FACTOR_VARIACION_VENTA = 0.93;
	private static final double FACTOR_VOLUMEN_VENTA = 0.93;
	
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
		System.out.println("+----- COMPRA DE CRIPTOMONEDAS -----+");
		String nombre = Menu.pedirString("+ Ingrese el nombre de la criptomoneda: ");

		boolean criptoValida = false;
		
		Criptomoneda criptoBuscada = null;
		Mercado criptoEnMercado = null;

		while (!criptoValida) {

			if (this.criptomonedas.existe(nombre)) {
				criptoBuscada = this.criptomonedas.obtenerRegistro(nombre);
				criptoEnMercado = this.mercado.obtenerRegistro(criptoBuscada.getSimbolo());
				criptoValida = true;
			} else {
				nombre = Menu.pedirString("+ La criptomoneda ingresada no existe. Vuelva a ingresar el nombre: ");				
			}
		}
		
		BigDecimal monto = Menu.pedirBigDecimal("+ Saldo actual: U$D " + saldoActual + "\n+ Ingrese cantidad a comprar: ");
		if(!validarMontoSaldo(monto, criptoBuscada)) {
			System.out.println("+ Saldo insuficiente. Presione una tecla para continuar..."); 
			Menu.esperarTecla();
			return;
		}
		
		if(criptoEnMercado == null) {
			throw new RuntimeException("+ Error al buscar la criptomoneda en el mercado.");
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
			
			System.out.println("+ Compra exitosa.");
			
		} else {
			// Logica no puede comprar porque no hay suficiente
			System.out.println("+ Cantidad insuficiente para comprar.");
		}
		
		System.out.println("\n+ Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	public void venderCriptomonedas() {
		Menu.limpiarConsola();
		System.out.println("+----- VENTA DE CRIPTOMONEDAS -----+");
		String nombreCripto = Menu.pedirString("+ Ingrese el nombre de la criptomoneda: ");

		boolean criptoValida = false;
		
		Criptomoneda criptoBuscada = null;

		while (!criptoValida) {

			if (this.criptomonedas.existe(nombreCripto)) {
				criptoBuscada = this.criptomonedas.obtenerRegistro(nombreCripto);
				criptoValida = true;
			} else {
				nombreCripto = Menu.pedirString("+ La criptomoneda ingresada no existe. Vuelva a ingresar el nombre:");				
			}
		}
		
		//exhibir la cantidad maxima que podes vender (desde el historico)
		Historico historico = dataHistorico.obtenerRegistro(criptoBuscada.getSimbolo());
		System.out.println("+ Cantidad maxima para vender: " + historico.getCantidad());
		
		double montoAVender = Menu.pedirDouble("+ Ingrese la cantidad que desea vender: ");
		
		if(montoAVender > historico.getCantidad()) {
			System.out.println("\n+ Cantidad insuficiente para vender. Presione una tecla para continuar...");
			Menu.esperarTecla();
			return;
		} 
		
		//modificar el historico (si existe la cantidad que ingresas para vender)
		String nombreSimbolo = criptoBuscada.getSimbolo();
		Historico historicoActualizado = new Historico(nombreSimbolo, historico.getCantidad() - montoAVender);
		dataHistorico.actualizarRegistro(nombreSimbolo, historicoActualizado);
		
		//modificar el mercado (sumando el valor, disminuir un 7% el volumen y variacion)
		Mercado mercado = this.mercado.obtenerRegistro(nombreSimbolo);
		
		this.mercado.actualizarRegistro(nombreSimbolo, new Mercado(mercado.getSimbolo(),
				mercado.getCapacidad() + montoAVender,
				mercado.getVolumen() * FACTOR_VOLUMEN_VENTA,
				mercado.getVariacion() * FACTOR_VARIACION_VENTA));
		
		//actualizar saldo usuario 
		Criptomoneda criptomoneda = this.criptomonedas.obtenerRegistro(nombreCripto);
		BigDecimal montoVendido = criptomoneda.getValor().multiply(BigDecimal.valueOf(montoAVender));
		this.saldoActual = this.saldoActual.add(montoVendido);
		
		System.out.println("+ Vendiste " + montoAVender + " [" + criptomoneda.getSimbolo() + "] a U$D" + montoVendido 
							+ "\n\n+ Inventario de criptomonedas actual: ");
		
		consultarHistoricoSinMenu();
		
		System.out.println("\n+ Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	private void consultarHistoricoSinMenu() {
		for(String simboloCripto : dataHistorico.getContenido().keySet()) {
			System.out.println(dataHistorico.obtenerRegistro(simboloCripto));
		}
		
		/*
		 		for(String simboloCripto : this.mercado.getContenido().keySet()) {
			System.out.println(this.mercado.obtenerRegistro(simboloCripto));
		}
		 */
	}
	
	public void recomendarCriptomoneda() {
		try {
			Menu.limpiarConsola();
			System.out.println("+----- CRIPTOMONEDA RECOMENDADA -----+");
			Criptomoneda criptomoneda = obtenerMayorCriptomoneda();
			System.out.println("+ La criptomoneda recomendada es: " + criptomoneda.getNombre() 
																	+ "\n+ Simbolo: " + criptomoneda.getSimbolo()
																	+ "\n+ Valor: " + criptomoneda.getValor());
			
			System.out.println("\n+ Presione una tecla para continuar...");
			Menu.esperarTecla();
		}
		catch(Exception ex) {
			System.err.println(ex.getMessage());
		}		
	}
	
	private Criptomoneda obtenerMayorCriptomoneda() {
		Criptomoneda criptomonedaMayorValor = null;
		double calculoEstadisticoMayor = 0;
		for(Criptomoneda criptomoneda: this.criptomonedas.getContenido().values() ) {
			double calculoEstadisticoActual = getCalculoEstadistico(criptomoneda.getSimbolo(), criptomoneda.getValor());
			if(criptomonedaMayorValor == null || calculoEstadisticoMayor < calculoEstadisticoActual) {
				criptomonedaMayorValor = criptomoneda;
				calculoEstadisticoMayor = calculoEstadisticoActual;	
			}		
		}
		
		if(criptomonedaMayorValor == null) {
			throw new RuntimeException("+ No hay criptomonedas registradas.");
		}
		
		return criptomonedaMayorValor;
	}
	
	private double getCalculoEstadistico(String simboloCriptomoneda, BigDecimal valor) {
		Mercado mercadoMayor = this.mercado.obtenerRegistro(simboloCriptomoneda);
		return BigDecimal.valueOf(mercadoMayor.getCapacidad()).divide(valor, 5, RoundingMode.HALF_UP).doubleValue() * FACTOR_PORCENTAJE;
	}
	
	public void visualizarHistorico() {
		Menu.limpiarConsola();
		System.out.println("+----- HISTORICO -----+\n");
		for(Historico historico : this.dataHistorico.getContenido().values()) {
			System.out.println(historico);
		}
		
		System.out.println("\n+ Presione una tecla para continuar...");
		Menu.esperarTecla();
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
	
	private void efectuarCompra(BigDecimal monto, Criptomoneda criptoBuscada) {
		this.saldoActual = this.saldoActual.subtract(criptoBuscada.getValor().multiply(monto));
	}
	
	private boolean puedeComprarPorCapacidad(BigDecimal monto, Mercado criptoEnMercado) {
		return monto.doubleValue() < criptoEnMercado.getCapacidad();
	}
	
	private boolean validarMontoSaldo(BigDecimal monto, Criptomoneda criptomoneda) {
		return monto.multiply(criptomoneda.getValor()).compareTo(saldoActual) <= 0;	
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
	
	@Override
	public void cerrarSesion() {
		this.dataHistorico.close();
	}

	@Override
	public String getUserDataCSVRow() {
		return this.nroCuenta + "," + this.nombreBanco + "," + this.saldoActual;
	}
}
