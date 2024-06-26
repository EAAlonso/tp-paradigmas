package cryptomanager;

import java.math.BigDecimal;

public class Administrador extends Usuario {

	private static final String PERFIL_ADMINISTRADOR = "administrador";
	private static final Double CAPACIDAD_DEFAULT_MERCADO = Double.valueOf(500);
	private static final Double VOLUMEN_DEFAULT_MERCADO = Double.valueOf(1);
	private static final Double VARIACION_DEFAULT_MERCADO = Double.valueOf(1);

	public Administrador(String nombre, CSVHandler<String, Criptomoneda> dataCriptomonedas,
			CSVHandler<String, Mercado> dataMercado) {
		super(nombre, dataCriptomonedas, dataMercado);
	}

	public void crearCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("+----- ALTA DE CRIPTOMONEDA -----+");
		String nombre = Menu.pedirString("+ Ingrese el nombre de la criptomoneda: ");

		if (this.criptomonedas.existe(nombre)) {
			boolean quiereEditar = Menu.pedirYoN("+ Criptomoneda existente. ¿Quiere editarla?\n+ Y - (Si)\n+ N - (No)\n+ Ingrese una opcion: ");
			if (!quiereEditar) {
				return;
			}

			Criptomoneda criptomonedaEditar = this.criptomonedas.obtenerRegistro(nombre);
			String nuevoNombre = Menu.pedirString("+ Ingrese el nuevo nombre - [Actual: " + nombre + "] --> ");
			String simbolo = Menu
					.pedirString("+ Ingrese el nuevo simbolo - [Actual: " + criptomonedaEditar.getSimbolo() + "] --> ");
			BigDecimal dolarBase = Menu
					.pedirBigDecimal("+ Ingrese el nuevo dolar base - [Actual: " + criptomonedaEditar.getValor() + "] --> ");
			
			this.editarCriptomonedaDesdeNombre(nombre, nuevoNombre, simbolo, dolarBase);

			return;
		}

		this.nuevaCriptomonedaDesdeNombre(nombre);

		return;
	}

	public void editarCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("+----- EDITAR CRIPTOMONEDA -----+");
		
		String nombre = this.pedirNombreDeCriptomonedaExistente();
		if (nombre == null) { // Cancela la operacion
			return;
		}
		
		Criptomoneda criptomonedaEditar = this.criptomonedas.obtenerRegistro(nombre);
		String nuevoNombre = Menu.pedirString("+ Ingrese el nuevo nombre - [Actual: " + nombre + "] --> ");
		String simbolo = Menu
				.pedirString("+ Ingrese el nuevo simbolo - [Actual: " + criptomonedaEditar.getSimbolo() + "] --> ");
		BigDecimal dolarBase = Menu
				.pedirBigDecimal("+ Ingrese el nuevo dolar base - [Actual: " + criptomonedaEditar.getValor() + "] --> ");
		
		this.editarCriptomonedaDesdeNombre(nombre, nuevoNombre, simbolo, dolarBase);
	}
	
	public void eliminarCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("+----- ELIMINAR CRIPTOMONEDA -----+");
		
		String nombre = this.pedirNombreDeCriptomonedaExistente();
		if (nombre == null) { // Cancela la operacion
			return;
		}

		boolean confirma = Menu.pedirYoN("\n+ Criptomoneda encontrada. ¿Esta seguro que desea eliminarla?\n+ Y - (Si)\n+ N - (No)\n+ Ingrese una opcion: ");
		if (confirma) {
			this.eliminarCriptomonedaDesdeNombre(nombre);
		}
	}

	private void nuevaCriptomonedaDesdeNombre(String nombre) {
		String simbolo = Menu.pedirString("+ Ingrese el simbolo: ");
		BigDecimal dolarBase = Menu.pedirBigDecimal("+ Ingrese el valor de dolar base: ");

		Criptomoneda criptomoneda = new Criptomoneda(nombre, simbolo, dolarBase);
		this.criptomonedas.insertarRegistro(criptomoneda);
		this.mercado.insertarRegistro(new Mercado(criptomoneda.getSimbolo(), CAPACIDAD_DEFAULT_MERCADO,
				VOLUMEN_DEFAULT_MERCADO, VARIACION_DEFAULT_MERCADO));

		System.out.println("\n+ Criptomoneda creada con exito. Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	public void editarCriptomonedaDesdeNombre(String nombre, String nuevoNombre, String simbolo, BigDecimal dolarBase) {
		Criptomoneda criptomonedaEditar = this.criptomonedas.obtenerRegistro(nombre);
		Mercado itemMercado = this.mercado.obtenerRegistro(criptomonedaEditar.getSimbolo());


		this.criptomonedas.actualizarRegistro(criptomonedaEditar.getNombre(),
				new Criptomoneda(nuevoNombre, simbolo, dolarBase));
		this.mercado.actualizarRegistro(itemMercado.getSimbolo(),
				new Mercado(simbolo, itemMercado.getCapacidad(), itemMercado.getVolumen(), itemMercado.getVariacion()));

		System.out.println("+ Criptomoneda editada con exito. Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	public void eliminarCriptomonedaDesdeNombre(String nombre) {
		Criptomoneda criptomonedaEliminar = this.criptomonedas.obtenerRegistro(nombre);
		this.mercado.eliminarRegistro(criptomonedaEliminar.getSimbolo());
		this.criptomonedas.eliminarRegistro(nombre);
		
		System.out.println("\n+ Criptomoneda eliminada. Presione una tecla para continuar...");
		Menu.esperarTecla();
	}

	@Override
	public Menu newMenu() {
		return new MenuAdministrador(this);
	}

	@Override
	public void cerrarSesion() {}

	@Override
	public String getUserDataCSVRow() {
		return PERFIL_ADMINISTRADOR;
	}
}

