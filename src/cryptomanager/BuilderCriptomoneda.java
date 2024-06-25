package cryptomanager;

import java.math.BigDecimal;

public class BuilderCriptomoneda implements BuilderFromStringArray<String, Criptomoneda> {

	private static final String CSV_HEADER = "Nombre,simbolo,valor\n";

	public BuilderCriptomoneda() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Criptomoneda NewFromStringArray(String[] params) {
		
		return new Criptomoneda(params[0].trim(), params[1].trim(), new BigDecimal(params[2].trim()));
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0].trim();
	}

	@Override
	public String GetKey(Criptomoneda obj) {
		return obj.getNombre();
	}

	@Override
	public String GetCSVHeader() {
		return CSV_HEADER;
	}

	@Override
	public String GetCSVRow(Criptomoneda obj) {
		return obj.getNombre() + "," + obj.getSimbolo() + "," + obj.getValor() + "\n";
	}

}
