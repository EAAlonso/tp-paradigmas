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
		// implementación
	}
	
	protected void verMercadoActual() {
		// implementación
	}
}
