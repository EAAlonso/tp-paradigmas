package cryptomanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	
	public boolean existe(T key) {
		return this.Data.containsKey(key);
	}
	
	public void insertarRegistro(Q value) {
		T key = this.constructor.GetKey(value);
		
		if (this.Data.containsKey(key)) {
			throw new RuntimeException("Metodo invalido, registro duplicado, se debe usar el metodo de actualizar");
		}
		
		this.Data.put(key, value);
	}
	
	public void actualizarRegistro(T oldKey, Q nuevoValue) {
		if (!this.Data.containsKey(oldKey)) {
			throw new RuntimeException("Metodo invalido, registro inexistente.");
		}
		
		T nuevaKey = this.constructor.GetKey(nuevoValue);
		this.Data.remove(oldKey);
		this.Data.put(nuevaKey, nuevoValue);
	}
	
	public void eliminarRegistro(T key) {
		if (!this.Data.containsKey(key)) {
			throw new RuntimeException("Metodo invalido, registro inexistente.");
		}
		
		this.Data.remove(key);
	}
	
	public Map<T,Q> getContenido() {
		return this.Data;
	}
	
	public static void crearArchivo(String fileName) {
		try {
			new FileWriter(fileName.concat(".csv"));
		} catch(IOException e) {
			System.err.println("Error al crear el archivo CSV: " + fileName);
		}	
	}
	
	public static boolean archivoExistente(String fileName) {
		File archivo = new File(fileName.concat(".csv"));
		return archivo.exists();		
	}
	
	// TODO private void close() { pisar CSV con el mapa}
}
