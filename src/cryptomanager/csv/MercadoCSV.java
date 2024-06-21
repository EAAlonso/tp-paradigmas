package cryptomanager.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import cryptomanager.Constantes;
import cryptomanager.Criptomoneda;
import cryptomanager.Mercado;

public class MercadoCSV implements Readable {
	private String filePath;

	public MercadoCSV(String filePath) {
		this.filePath = filePath;
	}

	public <T> T leerArchivo(String simboloCriptomoneda) {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if ((values[0].toLowerCase().trim().equals(simboloCriptomoneda.toLowerCase().trim()))) {
					String capacidad = values[1].concat(",").concat(values[2]);
					
					String volumen = values[3].concat(",").concat(values[4]).replace("%", "");
					
					String variacion = values[5].concat(",").concat(values[6]).replace("%", "");

					Mercado mercado = new Mercado(values[0].toUpperCase().trim(), Double.parseDouble(capacidad),
							Double.parseDouble(volumen), Double.parseDouble(variacion));
					return (T) mercado;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public <T> int agregarRegistro(T registro) {
		Mercado mercado = (Mercado) registro;

		if ((leerArchivo(mercado.getSimbolo()) != null)) {
			return Constantes.CRIPTOMONEDA_EN_MERCADO;
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
			String volumen = " " + mercado.getVolumen().toString().concat("% ");
			
	        DecimalFormat df = new DecimalFormat("#.##");
	        Double porcentaje = mercado.getVariacion() * Constantes.VARIACION_CRIPTOMONEDA_INICIAL;
	        String formato = df.format(porcentaje);
			String variacion = " +" + formato.toString().concat("%");
			
			String linea = String.format("%s,%s,%s,%s", mercado.getSimbolo(), " " + mercado.getCapacidad().toString().replace(".", ",").concat(" "),
					volumen.replace(".", ","), variacion.replace(".", ","));
			bw.write(linea);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Constantes.CRIPTOMONEDA_EN_MERCADO_CREADA;
	}
}
