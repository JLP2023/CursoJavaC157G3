
package tpc157g3;

import tpc157g3.Partido;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Principal {

        public static void main(String[] args){

		// Leer resultados
		Collection<Partido> partidos = new ArrayList<>();
		Path pathResultados = Paths.get(args[0]);
                List<String> lineasResultados = null;
		try {
			lineasResultados = Files.readAllLines(pathResultados);
		} catch (IOException e) {
			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		}
                //Borro encabezado
                lineasResultados.remove(0);
                
		for (String lineaResultado : lineasResultados) {                             
			
                        String[] campos = lineaResultado.split(",");
                        Rondas numronda = new Rondas(campos[0]);
                        Equipo equipo1 = new Equipo(campos[1]);
                        Equipo equipo2 = new Equipo(campos[4]);
                        Partido partido = new Partido(equipo1, equipo2);
                        //control si goles son enteros
                        if (campos[2].matches(" ?\\d+") && campos[3].matches(" ?\\d+")){
                                partido.setGolesEq1(Integer.parseInt(campos[2]));
                                partido.setGolesEq2(Integer.parseInt(campos[3]));
                                partidos.add(partido);
                                
                        }else{
                                System.out.println("Los goles no son Nº enteros");
                        }
                }
		
             // Leer pronostico
               ArrayList<Persona> person = new ArrayList<Persona>();
               int puntos = 0; // total puntos persona
               Path pathPronostico = Paths.get(args[1]);
               List<String> lineasPronostico = null;
               try {
                   lineasPronostico = Files.readAllLines(pathPronostico);
               } catch (IOException e) {
                   System.out.println("No se pudo leer la linea de pronosticos...");
                   System.out.println(e.getMessage());
                   System.exit(1);
               }              
               //Borro encabezado
               lineasPronostico.remove(0);
               //Ordeno la lista 
               Collections.sort(lineasPronostico);
                
               int m = 0;
               int n = 0;
               Boolean x = true;
               String peri = null;
               for (String lineaPronostico : lineasPronostico) {
                   n=n+1;
		   String[] campos = lineaPronostico.split(","); 
                   if(x){
                       x=false;
                       peri = campos[0];
                   }
                   Equipo equipo1 = new Equipo(campos[1]);
		           Equipo equipo2 = new Equipo(campos[5]);
		           Partido partido = null;
                   for (Partido partidoCol : partidos) {
                       if ((partidoCol.getEquipo1().getNombre().equals(equipo1.getNombre())
                           || partidoCol.getEquipo1().getNombre().equals(equipo2.getNombre()))
                           &&
                           (partidoCol.getEquipo2().getNombre().equals(equipo2.getNombre())
                           || partidoCol.getEquipo2().getNombre().equals(equipo1.getNombre()))){
                           partido = partidoCol;
                       }                               
                   }
		   Equipo equipo = null;
		   EnumResultado resultado = null;
		   if("X".equals(campos[2])) {
		       equipo = equipo1;
		       resultado = EnumResultado.GANADOR;
		   }
		   if("X".equals(campos[3])) {
		       equipo = equipo1;
		       resultado = EnumResultado.EMPATE;
		   }
		   if("X".equals(campos[4])) {
		       equipo = equipo1;
		       resultado = EnumResultado.PERDEDOR;
		   }
                                
                   if(partido != null){
                       Pronostico pronostico = new Pronostico(partido, equipo, resultado);
                       if(peri.equals(campos[0])){
                           // sumar los puntos correspondientes
                           puntos += pronostico.puntos();                           
                           if (n == lineasPronostico.size()){
                                 
                               Persona perso = new Persona(peri,puntos);
                               person.add(perso);
                               peri = campos[0];                                 
                               puntos = 0;
                           }
                           
                       }else{       
                           if (n < lineasPronostico.size()){
                               
                               Persona perso = new Persona(peri,puntos);
                               person.add(perso);
                               peri = campos[0];                                                    
                               puntos = pronostico.puntos();
                           }    
                       }      
                   }else{
                       System.out.println("Nombre de Equipo incorrecto o el partido no existe");
                       // System.exit(1);
	           }                                          
               }
               // mostrar puntos   
               System.out.println("Los puntos obtenidos por los usuario fueron:");
               for (int i=0; i<person.size(); i++){
                   System.out.println(person.get(i).getNombre()+ "   "+person.get(i).getPuntaje());
               }   
	}
}

