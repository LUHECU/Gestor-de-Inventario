
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {

    Connection con;

    public Conexion() {
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String usuario = "PROYECTOPROGRA";
        String contraseña = "Proyecto12345";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try {
                con = DriverManager.getConnection(url, usuario, contraseña);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Conexión exitosa a la base de datos Oracle");
    }

    public Connection getConnection() {
        return con;
    }
}