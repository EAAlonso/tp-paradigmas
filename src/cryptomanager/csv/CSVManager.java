package cryptomanager.csv;

import cryptomanager.user.Trader;

// Adapter
public class CSVManager {
	public <T> T leerArchivoCSV(Reader archivo, String clave) {
		return archivo.leerArchivo(clave);
	}
	
	public <T> int agregarRegistroCSV(Reader archivo, T registro) {
		return archivo.agregarRegistro(registro);
	}
}
