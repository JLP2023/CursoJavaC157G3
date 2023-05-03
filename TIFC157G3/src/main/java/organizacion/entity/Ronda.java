package organizacion.entity;

import java.util.ArrayList;
import java.util.List;

public class Ronda {
	
	private Integer numero;
	private List<Partido> partidos;
	private Fase fase;
	
	public Ronda(Integer numero) {
		super();
		this.numero = numero;
		this.partidos = new ArrayList<Partido>();
	}	
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public void addPartido(Partido partido) {
		this.partidos.add(partido);
	}

	public boolean acertoTodos(List<Pronostico> apuestas) {
		int puntosDisponiblesRonda = this.partidos.size();
		int puntosObtenidosEnRonda = 0;
		
		for (Pronostico pronostico : apuestas) {
			if(pronostico.getPartido().getRonda().getNumero().equals(this.numero)) {
				puntosObtenidosEnRonda += pronostico.puntos();
			}
		}
		return puntosDisponiblesRonda == puntosObtenidosEnRonda;
	}

	@Override
	public String toString() {
		return this.getNumero()+this.getPartidos().toString();
	}
}
