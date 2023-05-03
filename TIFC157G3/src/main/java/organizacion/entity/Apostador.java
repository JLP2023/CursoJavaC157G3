package organizacion.entity;

import java.util.ArrayList;
import java.util.List;

public class Apostador {

	private String nombre;
	private List<Pronostico> pronosticos;
	private List<Ronda> rondas;
	private Integer bonusXRonda;
	private Integer bonusXFase;

	public Apostador(String nombre) {
		super();
		this.nombre = nombre;
		this.pronosticos = new ArrayList<Pronostico>();
		this.bonusXRonda = 0;
		this.bonusXFase = 0;
	}

	public Integer getBonusXRonda() {
		return bonusXRonda;
	}

	public Integer getBonusXFase() {
		return bonusXFase;
	}


	public List<Pronostico> getPronosticos() {
		return pronosticos;
	}


	public void addPronostico(Pronostico pronostico){
		this.pronosticos.add(pronostico);
	}


	public int puntosPorPronostico(String valorPartido) {
		int puntos = 0;
		for (Pronostico pronostico : pronosticos) {
			puntos += pronostico.puntos(valorPartido);
		}
		return puntos;
	}

	public void incBonusRonda(int i) {
		bonusXRonda += i;
	}

	public void incBonusFase(int m) { bonusXFase += m; }

	public int puntosTotales(String valorPartido) {
		return this.puntosPorPronostico(valorPartido) + this.bonusXRonda + this.bonusXFase;
	}

	public void calcularBonusXRondas(Apuesta apuestas, Resultado fixture, String apostador,String valorPartido,String valorRonda ) {
			int bonusRonda = Integer.parseInt(valorRonda);
		   	//for (String nombreJugador : apuestas.getApostadores().keySet()) {
			for (Ronda ronda : fixture.rondas()) {
				int puntosDisponiblesRonda = ronda.getPartidos().size();
				List<Pronostico> varapuestas = apuestas.getApostadores().get(apostador).getPronosticos();
				boolean cumplioRonda = ronda.acertoTodos(varapuestas,valorPartido);
				if (cumplioRonda) {
					apuestas.getApostadores().get(apostador).incBonusRonda(bonusRonda);
				}
			}
	}

	public void calcularBonusXFases(Apuesta apuestas, Resultado fixture, String apostador,String puntosPartido, String puntosFase) {
		int valorFase = Integer.parseInt(puntosFase);
			for (Fase fase : fixture.fases()) {
				int puntosDisponiblesFase = fase.getRondas().size();
				int puntosObtenidosEnFase=0;
				for (Ronda ronda : fase.getRondas().values()) {
					List<Pronostico> varapuestas = apuestas.getApostadores().get(apostador).getPronosticos();
					boolean cumplioRonda = ronda.acertoTodos(varapuestas,puntosPartido);
					if (cumplioRonda) {
						puntosObtenidosEnFase = puntosObtenidosEnFase + 1;
					}
				}
				if((puntosDisponiblesFase) == (puntosObtenidosEnFase)){
					apuestas.getApostadores().get(apostador).incBonusFase(valorFase);
				}
			}
	}

}
