package cryptomanager.csv;

public interface Reader {
	public <T> T leerArchivo(String coincidencia);
	
	public <T> int agregarRegistro(T registro);
}
