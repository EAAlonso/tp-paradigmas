package cryptomanager;

import java.math.BigDecimal;

public class Administrador extends Usuario {

	private static final Double CAPACIDAD_DEFAULT_MERCADO = Double.valueOf(500);
	private static final Double VOLUMEN_DEFAULT_MERCADO = Double.valueOf(1);
	private static final Double VARIACION_DEFAULT_MERCADO = Double.valueOf(1);

	public Administrador(String nombre, CSVHandler<String, Criptomoneda> dataCriptomonedas,
			CSVHandler<String, Mercado> dataMercado) {
		super(nombre, dataCriptomonedas, dataMercado);
	}

	public void crearCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("===== Alta/Edicion de criptomoneda ===== ");
		String nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");

		if (this.criptomonedas.existe(nombre)) {
			boolean quiereEditar = Menu.pedirYoN("La criptomoneda ya existe! ¿Quiere editarla? Y (Si) / N (No): ");
			if (!quiereEditar) {
				return;
			}

			this.editarCriptomonedaDesdeNombre(nombre);

			return;
		}

		this.nuevaCriptomonedaDesdeNombre(nombre);

		return;
	}

	public void editarCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("===== Edicion de criptomoneda ===== ");
		
		String nombre = this.pedirNombreDeCriptomonedaExistente();
		if (nombre == null) { // Cancela la operacion
			return;
		}

		this.editarCriptomonedaDesdeNombre(nombre);

	}
	
	public void eliminarCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("===== Eliminación de criptomoneda ===== ");
		
		String nombre = this.pedirNombreDeCriptomonedaExistente();
		if (nombre == null) { // Cancela la operacion
			return;
		}

		boolean confirma = Menu.pedirYoN("Criptomoneda encontrada, ¿esta seguro que desea eliminarla? Y (Si) / N (No): ");
		if (confirma) {
			this.eliminarCriptomonedaDesdeNombre(nombre);	
		}
	}

	private void nuevaCriptomonedaDesdeNombre(String nombre) {
		String simbolo = Menu.pedirString("Ingrese el simbolo: ");
		BigDecimal dolarBase = Menu.pedirBigDecimal("Ingrese el valor de dolar base: ");

		Criptomoneda criptomoneda = new Criptomoneda(nombre, simbolo, dolarBase);
		this.criptomonedas.insertarRegistro(criptomoneda);
		this.mercado.insertarRegistro(new Mercado(criptomoneda.getSimbolo(), CAPACIDAD_DEFAULT_MERCADO,
				VOLUMEN_DEFAULT_MERCADO, VARIACION_DEFAULT_MERCADO));

		System.out.println("Criptomoneda insertada con exito! Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	private void editarCriptomonedaDesdeNombre(String nombre) {
		Criptomoneda criptomonedaEditar = this.criptomonedas.obtenerRegistro(nombre);
		Mercado itemMercado = this.mercado.obtenerRegistro(criptomonedaEditar.getSimbolo());

		nombre = Menu.pedirString("Ingrese el nuevo nombre (Actual: " + nombre + "): ");
		String simbolo = Menu
				.pedirString("Ingrese el nuevo simbolo (Actual: " + criptomonedaEditar.getSimbolo() + "): ");
		BigDecimal dolarBase = Menu
				.pedirBigDecimal("Ingrese el nuevo dolar base (Actual: " + criptomonedaEditar.getValor() + "): ");

		this.criptomonedas.actualizarRegistro(criptomonedaEditar.getNombre(),
				new Criptomoneda(nombre, simbolo, dolarBase));
		this.mercado.actualizarRegistro(itemMercado.getSimbolo(),
				new Mercado(simbolo, itemMercado.getCapacidad(), itemMercado.getVolumen(), itemMercado.getVariacion()));

		System.out.println("Criptomoneda editada con exito! Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	private void eliminarCriptomonedaDesdeNombre(String nombre) {
		Criptomoneda criptomonedaEliminar = this.criptomonedas.obtenerRegistro(nombre);
		this.mercado.eliminarRegistro(criptomonedaEliminar.getSimbolo());
		this.criptomonedas.eliminarRegistro(nombre);
		
		System.out.println("Criptomoneda eliminada! Presione una tecla para continuar...");
		Menu.esperarTecla();
	}

	@Override
	public Menu newMenu() {
		return new MenuAdministrador(this);
	}
}
