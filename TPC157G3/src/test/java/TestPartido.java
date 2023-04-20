/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tpc157g3.Equipo;
import tpc157g3.Partido;

/**
 *
 * @author notebook
 */
public class TestPartido {
    
    public TestPartido() {
    }
    
    
    
    
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testpartidoresultado(){
        Equipo equipo1 = new Equipo("boca");
        Equipo equipo2 = new Equipo("Tigre");
        Partido partido = new Partido(equipo1,equipo2,2,2);

    
    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
