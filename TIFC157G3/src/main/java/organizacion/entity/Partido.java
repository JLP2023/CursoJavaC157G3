package organizacion.entity;

import organizacion.exceptions.EquipoNoValidoException;

public class Partido {
	
	private Equipo equipo1;
	private Equipo equipo2;
	private int golesEq1;
	private int golesEq2;
	
	private Ronda ronda;

		
	public Partido(Equipo equipo1, Equipo equipo2) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
	}

	public Partido() {
	}

	public Partido(Equipo equipo1, Equipo equipo2, int golesEq1, int golesEq2) {
		super();
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.golesEq1 = golesEq1;
		this.golesEq2 = golesEq2;
	}

	
	
	public Ronda getRonda() {
		return ronda;
	}

	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}
	public Equipo getEquipo2() {
		return equipo2;
	}
	public void setGolesEq1(int golesEq1) {
		this.golesEq1 = golesEq1;
	}
	public void setGolesEq2(int golesEq2) {
		this.golesEq2 = golesEq2;
	}
	
	public EnumResultado resultado(Equipo equipo) {
		if(golesEq1 == golesEq2) {
			return EnumResultado.EMPATE;
		}
		if(equipo.getNombre().equals(equipo1.getNombre())) {
			if(golesEq1>golesEq2) {
				return EnumResultado.GANADOR;	
			}	else {
				return EnumResultado.PERDEDOR;
			}			
		}
		if(equipo.getNombre().equals(equipo2.getNombre()))
		{
			// como equipo no es equipo1, entonces es equipo2
			if(golesEq2>golesEq1) {
				return EnumResultado.GANADOR;	
			}	else {
				return EnumResultado.PERDEDOR;
			}	
		}
		throw new EquipoNoValidoException();
	}

	@Override
	public String toString() {
		return this.getEquipo1().getNombre() + this.getEquipo2().getNombre();
	}
}
