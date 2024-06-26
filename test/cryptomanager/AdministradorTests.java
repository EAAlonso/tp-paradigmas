package cryptomanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AdministradorTests {

	@Test
	void editarCriptomonedaDesdeNombreTest() throws IOException {
		System.in.close();
		
		String nombre = "AdminTest";
		Map<String, Criptomoneda> mapaCriptomoneda = new HashMap<String, Criptomoneda>();
		mapaCriptomoneda.put("Bitcoin", new Criptomoneda("Bitcoin", "BTC", BigDecimal.valueOf(1245.00)));
		BuilderCriptomoneda builderCriptomoneda = new BuilderCriptomoneda();
		CSVHandler<String, Criptomoneda> dataCriptomonedas = new CSVHandler<String, Criptomoneda>(mapaCriptomoneda, builderCriptomoneda);
		
		Map<String, Mercado> mapaMercado = new HashMap<String, Mercado>();
		mapaMercado.put("BTC", new Mercado("BTC", 1.38, 34.66, 13.84));
		BuilderMercado builderMercado = new BuilderMercado();
		CSVHandler<String, Mercado> dataMercado = new CSVHandler<String, Mercado>(mapaMercado, builderMercado);

		Administrador administrador = new Administrador(nombre, dataCriptomonedas, dataMercado);
		administrador.editarCriptomonedaDesdeNombre("Bitcoin", "Bitcoin2", "BTC", new BigDecimal(200));
		assertEquals(dataCriptomonedas.obtenerRegistro("Bitcoin2").getNombre(), "Bitcoin2");
		assertEquals(dataCriptomonedas.obtenerRegistro("Bitcoin2").getSimbolo(), "BTC");
		assertEquals(dataCriptomonedas.obtenerRegistro("Bitcoin2").getValor().toString(), "200");
	}
	
	@Test
	void eliminarCriptomonedaDesdeNombreTest() throws IOException {
		System.in.close();
		
		String nombre = "AdminTest";
		Map<String, Criptomoneda> mapaCriptomoneda = new HashMap<String, Criptomoneda>();
		mapaCriptomoneda.put("Bitcoin", new Criptomoneda("Bitcoin", "BTC", BigDecimal.valueOf(1245.00)));
		BuilderCriptomoneda builderCriptomoneda = new BuilderCriptomoneda();
		CSVHandler<String, Criptomoneda> dataCriptomonedas = new CSVHandler<String, Criptomoneda>(mapaCriptomoneda, builderCriptomoneda);
		
		Map<String, Mercado> mapaMercado = new HashMap<String, Mercado>();
		mapaMercado.put("BTC", new Mercado("BTC", 1.38, 34.66, 13.84));
		BuilderMercado builderMercado = new BuilderMercado();
		CSVHandler<String, Mercado> dataMercado = new CSVHandler<String, Mercado>(mapaMercado, builderMercado);

		Administrador administrador = new Administrador(nombre, dataCriptomonedas, dataMercado);
		administrador.eliminarCriptomonedaDesdeNombre("Bitcoin");
		assertFalse(dataCriptomonedas.existe("Bitcoin"));
	}

}
