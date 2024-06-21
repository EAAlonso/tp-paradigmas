package cryptomanager.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cryptomanager.Constantes;
import cryptomanager.user.Administrador;
import cryptomanager.user.Trader;
import cryptomanager.user.Perfil;

public class UsuarioCSV implements Reader {
	private String filePath;

	public UsuarioCSV(String filePath) {
		this.filePath = filePath;
	}

	public <T> T leerArchivo(String nombreUsuario) {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			//System.out.println(nombreUsuario);
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				//System.out.println(values[0]); No me encuentra el primer registro...
				if ((values[0].toLowerCase().trim().equals(nombreUsuario.toLowerCase().trim()))) {
					
					if (values[1].toLowerCase().trim().equals("administrador")) {
						Administrador admin = new Administrador(values[0].trim());
						return (T) admin;
					} else {
						Long nroCuenta = Long.parseLong(values[1].trim());
						//Double saldo = Double.parseDouble(values[3].trim());
						Trader trader = new Trader(values[0].trim(), nroCuenta, values[2].trim(), values[3].trim());
						return (T) trader;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public <T> int agregarRegistro(T registro) {
		Trader usuario = (Trader) registro;
		
		if((leerArchivo(usuario.getNombre()) != null)) {
			return Constantes.USUARIO_EXISTENTE; // Usuario ya registrado previamente
		}	
				
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
	        String linea = String.format("%s,%d,%s,%s", usuario.getNombre(),
	        		usuario.getNroCuenta(), usuario.getNombreBanco(), usuario.getSaldoActual().toString().replace(',','.'));
	        bw.write(linea);
	        bw.newLine();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return Constantes.USUARIO_CREADO;
	}
}
