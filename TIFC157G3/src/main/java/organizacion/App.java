package organizacion;

import organizacion.datasource.Conexion;
import organizacion.entity.*;
import organizacion.exceptions.ArchResultadoException;

import java.util.*;

public class App{
    public static void main( String[] args ) {

//---------------------------------------- CARGA DE PARTIDOS , RONDAS Y FASES ------------------------------------------

        Resultado fixture = new Resultado(args[0]);
        try {
            fixture.cargarResultados();
        } catch (ArchResultadoException e) {
            System.out.println("No se pudo leer el archivo de resultados: "
                    + e.getArchivoCsv());
            System.out.println(e.getMessage());
           // System.exit(1);
        }

//---------------------------------------- CARGA DE PRONOSTICOS Y APOSTADORES ------------------------------------------

        Apuesta apuestas = new Apuesta(args[1],fixture.getPartidos());
        try {
            apuestas.cargarApuestas();
        } catch (ArchResultadoException e) {
            System.out.println("No se pudo leer el archivo de resultados: "
                    + e.getArchivoCsv());
            System.out.println(e.getMessage());
        }

//--------------------------------------- CALCULAR Y MOSTRAR PUNTOS X APOSTADOR ----------------------------------------

        fixture.mostrarResultados(apuestas,fixture);

    }

}
