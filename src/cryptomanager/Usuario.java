package cryptomanager;

public abstract class Usuario {
	
	private String nombre;
	CSVHandler<String, Criptomoneda> criptomonedas;
	CSVHandler<String, Mercado> mercado;
	
	protected Usuario(String nombre, CSVHandler<String, Criptomoneda> dataCriptomonedas, CSVHandler<String, Mercado> dataMercado) {
		this.nombre = nombre;
		this.criptomonedas = dataCriptomonedas;
		this.mercado = dataMercado;
	}
	
	public Usuario() {
		
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public abstract Menu newMenu();
	
	protected void consultarCriptomoneda() {
		Menu.limpiarConsola();
		System.out.println("===== Consulta de criptomoneda ===== ");
		
		String nombre = this.pedirNombreDeCriptomonedaExistente();
		if (nombre == null) { // Cancela la operacion
			return;
		}
		
		System.out.println(this.criptomonedas.obtenerRegistro(nombre));
		
		System.out.println("Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
	
	protected String pedirNombreDeCriptomonedaExistente() {
		String nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");

		while (!this.criptomonedas.existe(nombre)) {
			boolean reintentar = Menu.pedirYoN("Criptomoneda no encontrada, ¿volver a intentar? Y (Si) / N (No): ");
			if (!reintentar) {
				return null;
			}

			nombre = Menu.pedirString("Ingrese el nombre de la criptomoneda: ");
		}
		
		return nombre;
	}
	
	protected void consultarMercado() {
		Menu.limpiarConsola();
		System.out.println(" ======== ESTADO DEL MERCADO ========== ");
		for(String simboloCripto : this.mercado.getContenido().keySet()) {
			System.out.println(this.mercado.obtenerRegistro(simboloCripto));
		}
		
		System.out.println("Presione una tecla para continuar...");
		Menu.esperarTecla();
	}
}
