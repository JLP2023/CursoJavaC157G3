package tpc157g3;

public class Equipo {

	private String nombre;
	private String descripcion;

	public Equipo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
        
    /*    @Override
	public String toString()
    {
        return this.getNombre();
                           
    }*/
}
