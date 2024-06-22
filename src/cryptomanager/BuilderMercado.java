package cryptomanager;

public class BuilderMercado implements BuilderFromStringArray<String, Mercado> {

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

}
