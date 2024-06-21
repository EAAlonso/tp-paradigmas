package cryptomanager.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cryptomanager.Constantes;
import cryptomanager.Criptomoneda;
import cryptomanager.user.Administrador;
import cryptomanager.user.Trader;

public class CriptomonedaCSV implements Reader {
		private String filePath;
		
		public CriptomonedaCSV(String filePath) {
			this.filePath = filePath;
		}

		public <T> T leerArchivo(String nombreCriptomoneda) {
			String line;
			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					if ((values[0].toLowerCase().trim().equals(nombreCriptomoneda.toLowerCase().trim()))) {
						Criptomoneda criptomoneda = new Criptomoneda(values[0].trim(),
								values[1].toUpperCase().trim(), values[2].trim());
						return (T) criptomoneda;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		public <T> int agregarRegistro(T registro) {
			Criptomoneda criptomoneda = (Criptomoneda) registro;
			
			if((leerArchivo(criptomoneda.getNombre()) != null)) {
				return Constantes.CRIPTOMONEDA_EXISTENTE;
			}	
					
		    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
		        String linea = String.format("%s,%s,%s", criptomoneda.getNombre() + " ",
		        		" " + criptomoneda.getSimbolo() + " ", " " + criptomoneda.getValor().replace(',', '.'));
		        bw.write(linea);
		        bw.newLine();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return Constantes.CRIPTOMONEDA_CREADA;
		}
}
