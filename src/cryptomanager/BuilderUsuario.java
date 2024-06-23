package cryptomanager;

import java.math.BigDecimal;

public class BuilderUsuario implements BuilderFromStringArray<String, Usuario> {
	private static final String ROL_ADMINISTRADOR = "administrador";
	
	CSVHandler<String, Criptomoneda> criptomonedas;
	CSVHandler<String, Mercado> mercado;
	
	public BuilderUsuario(CSVHandler<String, Criptomoneda> dataCriptomonedas, CSVHandler<String, Mercado> dataMercado) {
		this.criptomonedas = dataCriptomonedas;
		this.mercado = dataMercado;
	}
	
	public Trader NewTrader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoCuenta) {
		return new Trader(nombre, nroCuenta, nombreBanco, saldoCuenta, this.criptomonedas, this.mercado);
	}
	
	@Override
	public Usuario NewFromStringArray(String[] params) {
		String nombre = params[0].trim();
		String rol = params[1].trim();
		
		if (rol.equals(ROL_ADMINISTRADOR)) {
			return new Administrador(nombre, this.criptomonedas, this.mercado);
		}
		
		Long nroCuenta = Long.parseLong(params[1].trim());
		String nombreBanco = params[2].trim();
		BigDecimal saldoCuenta = new BigDecimal(params[3].trim());
		
		return new Trader(nombre, nroCuenta, nombreBanco, saldoCuenta, this.criptomonedas, this.mercado);
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0].trim();
	}

	@Override
	public String GetKey(Usuario obj) {
		return obj.getNombre();
	}

}
