package cryptomanager;

public class BuilderCriptomoneda implements BuilderFromStringArray<String, Criptomoneda> {

	public BuilderCriptomoneda() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Criptomoneda NewFromStringArray(String[] params) {
		return new Criptomoneda(params[0].trim(), params[1].trim(), params[2].trim());
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0].trim();
	}

}