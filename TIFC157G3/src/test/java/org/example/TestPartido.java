package org.example;

import org.junit.Before;
import org.junit.Test;
import organizacion.entity.EnumResultado;
import organizacion.entity.Equipo;
import organizacion.entity.Partido;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author notebook
 */
public class TestPartido {
    private Equipo equipo1;
    
    public TestPartido() {
    }
    
    
    
    
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testpartidoresultado(){
        Equipo equipo1 = new Equipo("Argentina");
        Equipo equipo2 = new Equipo("Brasil");
        Partido partido = new Partido(equipo1,equipo2,2,2);
        EnumResultado resultadouno = partido.resultado(equipo1);
        assertEquals(EnumResultado.EMPATE, partido.resultado(equipo1));
    
    }


}
