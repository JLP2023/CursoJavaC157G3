package org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import organizacion.entity.EnumResultado;
import organizacion.entity.Equipo;
import organizacion.entity.Partido;
import organizacion.entity.Pronostico;

public class PronosticoTest {

	private Equipo equipo1;
	private Equipo equipo2;

	@Before
	public void setUp() {
		this.equipo1 = new Equipo("Argentina");
		this.equipo2 = new Equipo("Brasil");
		
	}
	
	@Test
	public void testControlarAciertos() {
		
		// Escenario
		Partido partido = new Partido(this.equipo1,
				this.equipo2, 2, 3);
		Pronostico pronostico = new Pronostico(partido, this.equipo1, EnumResultado.PERDEDOR);
		
		// Procesar
		int puntos = pronostico.puntos();
		
		// Evaluar
		
		assertEquals(1, puntos);
		
		
	} 
	
	@Test
	public void testControlarDesaciertos() {
		
		// Escenario
		Partido partido = new Partido(this.equipo1, 
				this.equipo2, 2, 3);
		Pronostico pronostico = new Pronostico(partido, this.equipo1, EnumResultado.EMPATE);
		
		// Procesar
		int puntos = pronostico.puntos();
		
		// Evaluar
		
		assertEquals(0, puntos);
		
		
	} 
	
}
