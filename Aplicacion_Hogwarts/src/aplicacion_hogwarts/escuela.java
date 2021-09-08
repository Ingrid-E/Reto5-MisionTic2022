
package model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import controlador.conexion;
import static controlador.conexion.conectar;
import java.sql.SQLException;
public class escuela {
    public int id;
    public String nombre;
    public String creador;
    public int anios_servicio;
    public String fecha;
    public String habilidad;
    public ArrayList<pocion> pociones;

    public escuela(int id, String nombre, String creador, int anios_servicio, String fecha, String habilidad, ArrayList<pocion> pociones) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.anios_servicio = anios_servicio;
        this.fecha = fecha;
        this.habilidad = habilidad;
        this.pociones = pociones;
    }

    public escuela(int id, String nombre, String creador, int anios_servicio, String fecha, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.anios_servicio = anios_servicio;
        this.fecha = fecha;
        this.habilidad = habilidad;
    }
    
    public void crearEsc() throws SQLException{
        
        String sql = "INSERT INTO escuelas(codigo_escuela, nombre, habilidad, anios_servicio, creador, fecha_inicio) VALUES  (?,?,?,?,?,?)";
        PreparedStatement statement = conexion.conn.prepareStatement(sql);
        
        statement.setInt(1, id);
        statement.setString(2, nombre);
        statement.setString(3, habilidad);
        statement.setInt(4, anios_servicio);        
        statement.setString(5, creador);
        statement.setString(6, fecha);     
        int rowsInserted = statement . executeUpdate ();
        if ( rowsInserted > 0) {
          System.out.println ("Insercion exitosa !");
        }else{
            System.out.println("no sirveee");
        }
                  
    }

 

   
}
