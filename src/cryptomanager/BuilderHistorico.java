package cryptomanager;

public class BuilderHistorico implements BuilderCSVObject<String,Historico> {

	private static final String CSV_HEADER = "simbolo,cantidad\n";

	public BuilderHistorico() {
		
	}

	@Override
	public Historico NewFromStringArray(String[] params) {
		return new Historico(params[0].trim(), Double.parseDouble(params[1].trim()));
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0];
	}

	@Override
	public String GetKey(Historico obj) {
		return obj.getSimbolo();
	}

	@Override
	public String GetCSVHeader() {
		return CSV_HEADER;
	}

	@Override
	public String GetCSVRow(Historico obj) {
		return obj.getSimbolo() + "," + obj.getCantidad() + "\n";
	}
}
