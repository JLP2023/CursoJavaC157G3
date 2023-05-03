package organizacion.exceptions;

import organizacion.entity.Equipo;

public class PartidoNoEncontradoException extends RuntimeException {
	private Equipo equipo1;
	private Equipo equipo2;
	
	public PartidoNoEncontradoException(Equipo equipo1, Equipo equipo2) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}
	
	
	
}
