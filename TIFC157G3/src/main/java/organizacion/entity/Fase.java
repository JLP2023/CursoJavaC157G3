package organizacion.entity;

import java.util.*;

public class Fase {
    private Integer numero;
    private HashMap<Integer,Ronda> rondas;

    public Fase(Integer numero) {
        this.numero = numero;
        this.rondas = new HashMap<>();
    }


    public HashMap<Integer, Ronda> getRondas() {
        return rondas;
    }

    public void addRonda(Integer numero,Ronda rondas) {
        this.rondas.put(numero,rondas);
    }

}