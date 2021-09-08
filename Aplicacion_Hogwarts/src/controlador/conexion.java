
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    public static Connection conn;
        
    public static void conectar(){
        String dbURL = "jdbc:mysql://localhost:3306/reto4";
        String username = "root";
        String password = "root";
        try{
            /*Connection*/ 
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("ESTAMOS CONECTADOOOOOOOOOOOOOOS");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
