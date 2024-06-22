package cryptomanager;

import java.math.BigDecimal;

public class BuilderUsuario implements BuilderFromStringArray<String, Usuario> {
	private static final String ROL_ADMINISTRADOR = "administrador";
	
	@Override
	public Usuario NewFromStringArray(String[] params) {
		if (params[1].trim().equals(ROL_ADMINISTRADOR)) {
			return new Administrador(params[0]);
		}
		
		return new Trader(params[0].trim(), Long.parseLong(params[1].trim()), params[2].trim(), new BigDecimal(params[3].trim()));
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
