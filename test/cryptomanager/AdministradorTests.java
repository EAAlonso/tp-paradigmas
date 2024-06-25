package cryptomanager;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AdministradorTests {

	@Test
	void editarCriptomonedaDesdeNombreTest() {
		String nombre = "AdminTest";
		Map<String, Criptomoneda> mapaCriptomoneda = new HashMap<String, Criptomoneda>();
		mapaCriptomoneda.put("Bitcoin", new Criptomoneda("Bitcoin", "BTC", BigDecimal.valueOf(1245.00)));
		
		Map<String, Mercado> mapaMercado = new HashMap<String, Mercado>();
		mapaMercado.put("BTC", new Mercado("BTC", 1.38, 34.66, 13.84));

		CSVHandler<String, Criptomoneda> dataCriptomonedas = new CSVHandler<String, Criptomoneda>(mapaCriptomoneda);
		CSVHandler<String, Mercado> dataMercado = new CSVHandler<String, Mercado>(mapaMercado);

		Administrador administrador = new Administrador(nombre, dataCriptomonedas, dataMercado);
	}

}
