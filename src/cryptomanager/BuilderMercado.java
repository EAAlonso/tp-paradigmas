package cryptomanager;

public class BuilderMercado implements BuilderCSVObject<String, Mercado> {

	private static final String CSV_HEADER = "simbolo,cantidad,volumen,variacion\n";

	@Override
	public Mercado NewFromStringArray(String[] params) {
		return new Mercado(params[0].trim(), Double.parseDouble(params[1]), Double.parseDouble(params[2]), Double.parseDouble(params[3]));
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0].trim();
	}

	@Override
	public String GetKey(Mercado obj) {
		return obj.getSimbolo();
	}

	@Override
	public String GetCSVHeader() {
		return CSV_HEADER;
	}

	@Override
	public String GetCSVRow(Mercado obj) {
		return obj.getSimbolo() + "," + obj.getCapacidad() + "," + obj.getVolumen() + "," + obj.getVariacion() + "\n";
	}
}
