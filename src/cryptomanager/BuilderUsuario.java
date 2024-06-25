package cryptomanager;

import java.math.BigDecimal;

public class BuilderUsuario implements BuilderCSVObject<String, Usuario> {
	private static final String CSV_HEADER = "nombre,perfil_nrocuenta,banco,saldo\n";
	private static final String ROL_ADMINISTRADOR = "administrador";
	private static final String PATH_HISTORICO = "archivos/%s_historico.csv";
	
	CSVHandler<String, Criptomoneda> criptomonedas;
	CSVHandler<String, Mercado> mercado;
	
	BuilderHistorico builderHistorico; 
	
	public BuilderUsuario(CSVHandler<String, Criptomoneda> dataCriptomonedas,
			CSVHandler<String, Mercado> dataMercado,
			BuilderHistorico builderHistorico) {
		this.criptomonedas = dataCriptomonedas;
		this.mercado = dataMercado;
		this.builderHistorico = builderHistorico;		
	}
	
	public Trader NewTrader(String nombre, Long nroCuenta, String nombreBanco, BigDecimal saldoCuenta) {
		if(!archivoTraderExistente(String.format(PATH_HISTORICO, nombre))) {
			crearArchivoTrader(String.format(PATH_HISTORICO, nombre));
		}
		
		CSVHandler<String, Historico> csvHistorico = new CSVHandler<String, Historico>(String.format(PATH_HISTORICO, nombre), this.builderHistorico);
		
		return new Trader(nombre, nroCuenta, nombreBanco, saldoCuenta, this.criptomonedas, this.mercado, csvHistorico);
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

		return NewTrader(nombre, nroCuenta, nombreBanco, saldoCuenta);
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0].trim();
	}

	@Override
	public String GetKey(Usuario obj) {
		return obj.getNombre();
	}
	
	private boolean archivoTraderExistente(String nombre) {
		return CSVHandler.archivoCSVExistente(nombre);
	}
	
	private void crearArchivoTrader(String nombre) {
		CSVHandler.crearArchivoCSV(nombre);
	}

	@Override
	public String GetCSVHeader() {
		return CSV_HEADER;
	}

	@Override
	public String GetCSVRow(Usuario obj) {
		return obj.getNombre() + "," + obj.getUserDataCSVRow() + "\n";
	}

}
