package cryptomanager;

public class BuilderHistorico implements BuilderFromStringArray<String,Historico> {

	public BuilderHistorico() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Historico NewFromStringArray(String[] params) {
		return new Historico();
	}

	@Override
	public String NewKeyFromStringArray(String[] params) {
		return params[0];
	}

	@Override
	public String GetKey(Historico obj) {
		return "";
	}

}
