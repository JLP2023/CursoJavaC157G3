package organizacion.datasource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static organizacion.datasource.ConectorMySQL.DB_URL;
import static organizacion.datasource.ConectorMySQL.USER;
import static organizacion.datasource.ConectorMySQL.PASS;
public class Conexion {
    Connection conexion = null;
    Statement consulta = null;
    public Conexion() {
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getConsulta() {
        return consulta;
    }

    public void setConsulta(Statement consulta) {
        this.consulta = consulta;
    }

    public void conectar() {
        Connection conexion = null;


        try {

            // Abrir la conexión
            System.out.println("conectando a la base de datos...");

            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexion Establecida con Exito");
            consulta=conexion.createStatement();
        } catch (SQLException se) {
            System.out.println("No se Pudo Establecer Conexion con la Base de Datos");
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {

            }
        }
    }

    public void cerrarConexion(){
        try {
            if (conexion != null)
                conexion.close();
                System.out.println("La Base de Datos Cerro con Exito");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
