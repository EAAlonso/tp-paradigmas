package cryptomanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVHandler<T,Q> {

	Map<T,Q> Data;
	String filepath;
	BuilderFromStringArray<T,Q> constructor;
	
	public CSVHandler(String filepath, BuilderFromStringArray<T,Q> constructor) {
		this.constructor = constructor;
		this.Data = new HashMap<T,Q>();
		this.obtenerMapaDesdeArchivo(filepath);
	}
	
	private Map<T,Q> obtenerMapaDesdeArchivo(String filePath) {
		String line;
		Boolean headerLeido = false;
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line = br.readLine()) != null) {
				if(!headerLeido) {
					headerLeido = true;
				} else {
					String[] values = line.split(",");
					Q item = this.constructor.NewFromStringArray(values);
					T key = this.constructor.NewKeyFromStringArray(values);
					
					this.Data.put(key, item);	
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Q obtenerRegistro(T key) {
		return this.Data.get(key);
	}
	
	// TODO private void close() { pisar CSV con el mapa}
}
