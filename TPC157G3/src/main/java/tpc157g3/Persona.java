
package tpc157g3;

public class Persona {
    private String nombre;
    private int puntaje;

    public Persona(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    
    
    
    @Override
	public String toString()
    {
        return this.getNombre() + this.getPuntaje();
                           
    }
    
    
}
