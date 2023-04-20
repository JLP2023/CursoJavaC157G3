
package tpc157g3;

import tpc157g3.Partido;

public class Rondas {
    String Numero;
    Partido[] partidos;

    public Rondas(String Numero) {
        this.Numero = Numero;
    }
    

    public Rondas(String Numero, Partido[] partidos) {
        this.Numero = Numero;
        this.partidos = partidos;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public Partido[] getPartidos() {
        return partidos;
    }

    public void setPartidos(Partido[] partidos) {
        this.partidos = partidos;
    }
    
    @Override
	public String toString()
    {
        return this.getNumero();
                           
    }
    
}
