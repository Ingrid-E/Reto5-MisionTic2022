
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    public static Connection conn;
        
    public static void conectar(){
        String dbURL = "jdbc:mysql://localhost:3306/hogwarts";
        String username = "root";
        String password = getPassword();
        try{
            /*Connection*/ 
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Conexion Exitosa");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        

	
    }
    
    private static String getPassword(){
        BufferedReader br; 
        String string = "";
        try {
            br = new BufferedReader(new FileReader(new File("D:\\Codes\\Reto5-MisionTic2022\\password.txt")));
            string = br.readLine(); 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex ) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return string;
    }
    
}
