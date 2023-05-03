package organizacion.entity;

import java.util.*;

public class Fase {
    private Integer numero;
    private HashMap<Integer,Ronda> rondas;

    public Fase(Integer numero) {
        this.numero = numero;
        this.rondas = new HashMap<>();
    }

    public Integer getNumero() {
        return numero;
    }

    public HashMap<Integer, Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(HashMap<Integer, Ronda> rondas) {
        this.rondas = rondas;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }



    public void addRonda(Integer numero,Ronda rondas) {
        this.rondas.put(numero,rondas);
    }

    public boolean acertoTodos(List<Pronostico> apuestas) {
        int puntosDisponiblesRonda = this.rondas.size();
        int puntosObtenidos=0;
        for (Pronostico pronostico : apuestas) {

           //System.out.println(pronostico.getPartido().getRonda().getFase().getRondas().values());
           //System.out.println(this.rondas.values());
                //puntosObtenidosEnRonda += pronostico.puntos();
        }
        return true;
    }
}