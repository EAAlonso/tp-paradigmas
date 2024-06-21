package cryptomanager.csv;

public interface Reader {
	public <T> T leerArchivo(String clave);
	
	public <T> int agregarRegistro(T registro);
}
