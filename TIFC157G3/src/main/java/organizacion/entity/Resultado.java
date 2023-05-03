package organizacion.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import organizacion.exceptions.ArchResultadoException;
import organizacion.exceptions.GolesNoValidosException;

public class Resultado {

	private String archivoCsv;
	private Collection<Partido> partidos ;
	private Map<Integer,Ronda> rondas;
	private Map<Integer,Fase> fases;

	public Resultado(String path) {
		super();
		this.archivoCsv = path;
		this.partidos = new ArrayList<Partido>();
		this.rondas = new HashMap<>();
		this.fases = new HashMap<>();

	}


	public Collection<Partido> getPartidos() {
		return partidos;
	}

	//	CARGA DE RESULTADOS Y RONDAS
	public void cargarResultados() throws ArchResultadoException {
		Path pathResultados = Paths.get(this.archivoCsv);
		List<String> lineasResultados = null;
		try {
			lineasResultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			throw new ArchResultadoException(this.archivoCsv);
		}
		boolean primera = true;

		for (String lineaResultado : lineasResultados) {
			if (primera) {
				primera = false;
			} else {
				String[] campos = lineaResultado.split(",");
				if (campos[2].matches("[0-9]+") && campos[3].matches("[0-9+]")) {
					Partido partido = crearPartidoDeCampos(campos);
					int nroRonda = Integer.parseInt(campos[0]);
					int nroFase = Integer.parseInt(campos[5]);
					Fase fase = null;
					Ronda ronda = null;

					if (!fases.containsKey(nroFase)) {
						fase = new Fase(nroFase);
						fases.put(nroFase, fase);
					} else {
						fase = fases.get(nroFase);
					}

					if (!rondas.containsKey(nroRonda)) {
						ronda = new Ronda(nroRonda);
						rondas.put(nroRonda, ronda);
					} else {
						ronda = rondas.get(nroRonda);
					}
					ronda.addPartido(partido);
					fase.addRonda(nroRonda, ronda);
					partido.setRonda(ronda);
					partidos.add(partido);
				}else {
					throw new GolesNoValidosException();
				}
			}
		}
	}

	private Partido crearPartidoDeCampos(String[] campos) {
		Equipo equipo1 = new Equipo(campos[1]);
		Equipo equipo2 = new Equipo(campos[4]);
		Partido partido = new Partido(equipo1, equipo2);
		partido.setGolesEq1(Integer.parseInt(campos[2]));
		partido.setGolesEq2(Integer.parseInt(campos[3]));

		return partido;
	}


	public void mostrarResultados(Apuesta apuestas, Resultado fixture, String puntosPartidos,String puntosRonda,String puntosFase) {
		for (String jugador : apuestas.getApostadores().keySet()) {
			apuestas.getApostadores().get(jugador).calcularBonusXRondas(apuestas, fixture, jugador,puntosPartidos,puntosRonda);
			apuestas.getApostadores().get(jugador).calcularBonusXFases(apuestas, fixture, jugador,puntosPartidos,puntosFase);
			System.out.println("\nLos puntos obtenidos por el jugador  " + jugador + "  son:");
			System.out.println("Puntos por partidos.... " + apuestas.getApostadores().get(jugador).puntosPorPronostico(puntosPartidos));
			System.out.println("Bonus por ronda........ " + apuestas.getApostadores().get(jugador).getBonusXRonda());
			System.out.println("Bonus por fase......... " + apuestas.getApostadores().get(jugador).getBonusXFase());
			System.out.println("Total de puntos........ " + apuestas.getApostadores().get(jugador).puntosTotales(puntosPartidos));
		}
	}

	public Collection<Ronda> rondas() {
		return rondas.values();
	}

	public Collection<Fase> fases() {
		return fases.values();
	}

}
