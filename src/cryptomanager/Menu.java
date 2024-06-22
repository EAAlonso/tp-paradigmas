package cryptomanager;

public class Menu {
	
	private String titulo;
	private String opciones;
	
	public Menu(String titulo, String opciones) {
		
		this.titulo = titulo;
		this.opciones = opciones;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getOpciones() {
		return this.opciones;
	}
	
	@Override
	public String toString() {
		return this.titulo + this.opciones;
	}

}