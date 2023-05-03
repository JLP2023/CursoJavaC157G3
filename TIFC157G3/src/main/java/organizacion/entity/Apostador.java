package organizacion.entity;

import java.util.ArrayList;
import java.util.Collection;
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

	public void setBonusXFase(Integer bonusXFase) {
		this.bonusXFase = bonusXFase;
	}

	public void setBonusXRonda(Integer bonusXRonda) {
		this.bonusXRonda = bonusXRonda;
	}

	public List<Ronda> getRondas() {
		return rondas;
	}

	public void setRondas(List<Ronda> rondas) {
		this.rondas = rondas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Pronostico> getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(List<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	public void addPronostico(Pronostico pronostico){
		this.pronosticos.add(pronostico);
	}

	public void addRonda(Ronda ronda) { this.rondas.add(ronda); }

	public int puntosPorPronostico() {
		int puntos = 0;
		for (Pronostico pronostico : pronosticos) {
			puntos += pronostico.puntos();
		}
		return puntos;
	}

	public void incBonusRonda(int i) {
		bonusXRonda += i;
	}

	public void incBonusFase(int m) { bonusXFase += m; }

	public int puntosTotales() {
		return this.puntosPorPronostico() + this.bonusXRonda + this.bonusXFase;
	}

	public void calcularBonusXRondas(Apuesta apuestas, Resultado fixture, String apostador ) {
		//for (String nombreJugador : apuestas.getApostadores().keySet()) {
			for (Ronda ronda : fixture.rondas()) {
				int puntosDisponiblesRonda = ronda.getPartidos().size();
				List<Pronostico> varapuestas = apuestas.getApostadores().get(apostador).getPronosticos();
				boolean cumplioRonda = ronda.acertoTodos(varapuestas);
				if (cumplioRonda) {
					apuestas.getApostadores().get(apostador).incBonusRonda(2);
				}
			}
		//}
	}

	public void calcularBonusXFases(Apuesta apuestas, Resultado fixture,String apostador ) {
		//for (String nombreJugador : apuestas.getApostadores().keySet()) {
			for (Fase fase : fixture.fases()) {
				int puntosDisponiblesFase = fase.getRondas().size();
				int puntosObtenidosEnFase=0;
				for (Ronda ronda : fase.getRondas().values()) {
					List<Pronostico> varapuestas = apuestas.getApostadores().get(apostador).getPronosticos();
					boolean cumplioRonda = ronda.acertoTodos(varapuestas);
					if (cumplioRonda) {
						puntosObtenidosEnFase = puntosObtenidosEnFase + 1;
					}
				}
				if((puntosDisponiblesFase) == (puntosObtenidosEnFase)){
					apuestas.getApostadores().get(apostador).incBonusFase(3);
				}
			}
		//}
	}

}
