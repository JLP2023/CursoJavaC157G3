package organizacion.entity;

import organizacion.exceptions.ArchResultadoException;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import static organizacion.datasource.ConectorMySQL.*;

public class Apuesta {


    private Map<String,Apostador> apostadores;
    private Collection<Pronostico> apuestas;
    private Collection<Partido> partidos;

    public Apuesta(Collection partidos) {
        this.apuestas = new ArrayList<>();
        this.apostadores = new HashMap<>();
        this.partidos = partidos;


    }

    public Collection<Partido> getPartidos() {
        return partidos;
    }


    public Map<String, Apostador> getApostadores() {
        return apostadores;
    }


    public void cargarApuestas() throws ArchResultadoException {
        Connection conn = null;
        Statement st = null;

        try {

            // Abrir la conexión
            System.out.println("Conectando a la Base de Datos...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Conexion Establecida con Exito");

            // Ejecutar una consulta
            System.out.println("Ejecutando  Consulta...\n");
            st = conn.createStatement();
            String sql;
            sql = "SELECT * FROM apuestas.pronosticos";

            //En la variable rs obtenemos las distintas filas que nos devolvió la base
            ResultSet rs = st.executeQuery(sql);
            Partido partido = null;
            // Obtener las distintas filas de la consulta
            while (rs.next()) {
                // Pbtener el valor de cada columna
                int id = rs.getInt("Id");
                String nombre = rs.getString("Apostador");
                String eqipo1 = rs.getString("Equipo1");
                String ganoEq1 = rs.getString("Gana1");
                String empate = rs.getString("Empate");
                String ganoEq2 = rs.getString("Gana2");
                String eqipo2 = rs.getString("Equipo2");

                Equipo equipo1 = new Equipo(eqipo1);
                Equipo equipo2 = new Equipo(eqipo2);
                partido = partidoDe(equipo1,equipo2);
                Equipo equipo = null;
                EnumResultado resultado = null;
                if("X".equals(ganoEq1)) {
                    equipo = equipo1;
                    resultado = EnumResultado.GANADOR;
                }
                if("X".equals(empate)) {
                    equipo = equipo1;
                    resultado = EnumResultado.EMPATE;
                }
                if("X".equals(ganoEq2)) {
                    equipo = equipo1;
                    resultado = EnumResultado.PERDEDOR;
                }

                Pronostico pronostico = new Pronostico(partido, equipo, resultado);
                apuestas.add(pronostico);

                String nombreApostador =nombre;
                Apostador apostador = null;
                if(apostadores.containsKey(nombreApostador)) {
                    apostador = apostadores.get(nombreApostador);

                } else {
                    apostador = new Apostador(nombreApostador);
                    apostadores.put(nombreApostador, apostador);
                }
                apostador.addPronostico(pronostico);
            }
            // Cerrar la conexión con la base de datos
            rs.close();
            st.close();
            st.close();
            System.out.println("La Conexion Cerro con Exito");
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
        // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
        try {
            if (st != null)
                st.close();
        } catch (SQLException se2) {

        }
    }

    }
    private Partido partidoDe(Equipo equipo1,Equipo equipo2){
        Partido partido = new Partido();
        for (Partido partidoCol : getPartidos()) {

            if (partidoCol.getEquipo1().getNombre(
            ).equals(equipo1.getNombre())
                    && partidoCol.getEquipo2().getNombre(
            ).equals(equipo2.getNombre())) {

                partido = partidoCol;
            }
        }
        return partido;
    }
}

