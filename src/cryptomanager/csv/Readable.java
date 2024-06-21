package cryptomanager.csv;

public interface Readable {
	public <T> T leerArchivo(String clave);
	
	public <T> int agregarRegistro(T registro);
}
