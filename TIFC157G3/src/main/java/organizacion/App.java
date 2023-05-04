package organizacion;

import organizacion.setting.SettingPuntos;
import organizacion.entity.*;
import organizacion.exceptions.ArchResultadoException;
import organizacion.exceptions.ArchSeteoException;

public class App{
    public static void main( String[] args ) {

//---------------------------------------CARGA VALOR DE LOS PUNTOS POR ACIERTOS-----------------------------------------
        SettingPuntos seteo =new SettingPuntos(args[2]);
        try {
            seteo.cargarSeteo();
        } catch (ArchSeteoException e) {
            System.out.println("No se pudo leer el archivo  Setting:  "
                    + e.getArchivoCsv());
            System.out.println(e.getMessage());
            // System.exit(1);
        }


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

        Apuesta apuestas = new Apuesta(fixture.getPartidos());
        try {
            apuestas.cargarApuestas();
        } catch (ArchResultadoException e) {
            System.out.println("No se pudo leer el archivo de pronosticos: "
                    + e.getArchivoCsv());
            System.out.println(e.getMessage());
        }

//--------------------------------------- CALCULAR Y MOSTRAR PUNTOS X APOSTADOR ----------------------------------------

        fixture.mostrarResultados(apuestas,fixture,seteo.getPuntosPartido(),seteo.getPuntosRonda(),seteo.getPuntosFase());

    }

}
