package organizacion.entity;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private EnumResultado resultado;

	public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
	}

	public Partido getPartido() {
		return this.partido;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public int puntos(String valorPartido) {
		// this.resultado -> pred
		EnumResultado resultadoReal = this.partido.resultado(
				this.equipo);
		if (this.resultado.equals(resultadoReal)) {
			return Integer.parseInt(valorPartido);
		} else {
			return 0;
		}
	}
}
