package cryptomanager.csv;

import cryptomanager.user.Trader;

public class CSVManager {
	public <T> T leerArchivoCSV(Reader archivo, String coincidencia) {
		return archivo.leerArchivo(coincidencia);
	}
	
	public <T> int agregarRegistroCSV(Reader archivo, T registro) {
		return archivo.agregarRegistro(registro);
	}
}
