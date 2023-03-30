package c157g3;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author notebook
 */
public class C157G3 
{

    public static void main(String[] args) throws
                                            FileNotFoundException,IOException 
    {              
     FileInputStream fis;          
     DataInputStream DatosEntrada; 
     fis = new FileInputStream( "partidos.csv" ); 
     DatosEntrada = new DataInputStream( fis );

     FileInputStream fis2;          
     DataInputStream DatosEntrada2; 
     fis2 = new FileInputStream( "pronostico.csv" ); 
     DatosEntrada2 = new DataInputStream( fis2 );
    
     
    
     
     String renglonpartido = null;
     String renglonpronostico = null;
     String resultado[]= new String[2];
     int golesE1 = 0;
     int golesE2 = 0;
     int puntos = 0;
    
     renglonpartido = DatosEntrada.readLine();
     renglonpronostico = DatosEntrada2.readLine();
       
     while ( renglonpartido != null )
        {        
         String partido[] = renglonpartido.split( ";" );
         String pronos[] = renglonpronostico.split( ";" );

         System.out.println( renglonpartido ); 
         System.out.println( renglonpronostico ); 
         
         for( int i=0 ; i<partido.length ; i++ )
         {   
                if(i==1)
                {
                    resultado[i] = partido[i];
                }
                else
                    if(i==3)
                    {
                    golesE1 = Integer.parseInt( partido[i] );    
                    }
                    else
                        if(i==4)
                        {
                            golesE2 = Integer.parseInt( partido[i] );
                        }
         }
         if(golesE1 == golesE2)
                {
                    resultado[1] = "E";
                }
                else
                    if(golesE1 < golesE2)
                    {
                    resultado[1] = "G2";
                    }
                    else
                    {
                        resultado[1] = "G1";
                    }
         
         System.out.println( resultado[1] );
         System.out.println( pronos[2] );
         System.out.println( pronos[3] );
         System.out.println( pronos[4] );
         System.out.println( pronos[5] );
         
         if (resultado[1] == "E" && Integer.parseInt(pronos[4])== 1)
         {
             System.out.println( pronos[4] );
             System.out.println( "1IF" );
             puntos= puntos + 1;
         }
          else
             if (resultado[1] == "G1" && Integer.parseInt(pronos[3])== 1)
             { 
               System.out.println( pronos[3] );
                 System.out.println( "2IF" );
                 puntos= puntos + 1;
             }
             else
             if (resultado[1] == "G2" && Integer.parseInt(pronos[5])== 1)
             { 
               System.out.println( pronos[5] );
                 System.out.println( "3IF" );
                 puntos= puntos + 1;
             }
         System.out.println( puntos );
        
         renglonpartido = DatosEntrada.readLine();
         renglonpronostico = DatosEntrada2.readLine();
        }
        
     fis.close(); 
     fis2.close();
     JOptionPane.showMessageDialog(null,"EL PUNTAJE OBTENIDO ES: " + puntos );  
    }  
} 
