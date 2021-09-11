
package model;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import controlador.Conexion;
import static controlador.Conexion.conectar;
import static controlador.Conexion.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escuela {
    public int id;
    public String nombre;
    public String creador;
    public int anios_servicio;
    public String fecha;
    public String habilidad;
    public ArrayList<Pocion> pociones;

    public Escuela(int id, String nombre, String creador, int anios_servicio, String fecha, String habilidad, ArrayList<Pocion> pociones) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.anios_servicio = anios_servicio;
        this.fecha = fecha;
        this.habilidad = habilidad;
        this.pociones = pociones;
    }

    public Escuela(int id, String nombre, String creador, int anios_servicio, String habilidad,String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.anios_servicio = anios_servicio;
        this.fecha = fecha;
        this.habilidad = habilidad;
    }
    
    public void actualizar() {

            String sql_actualizar = "UPDATE escuela"
                    + " SET nombre = ?, habilidad_principal= ?, anios_servicio=?,creador=?, fecha_inicio=? "
                    + "WHERE codigo_escuela = ?;";
            PreparedStatement statement_actualizar;
        try {
            statement_actualizar = Conexion.conn.prepareStatement(sql_actualizar);
            statement_actualizar.setString(1, this.nombre);
            statement_actualizar.setString(2, this.habilidad);
            statement_actualizar.setInt(3, this.anios_servicio);
            statement_actualizar.setString(4, this.creador);
            statement_actualizar.setString(5, this.fecha);
            statement_actualizar.setInt(6, this.id);

            int rowsUpdated = statement_actualizar.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue " + "actualizado exitosamente !");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    
    public static void eliminar(int id){
        System.out.println("Eliminar Escuela: " + id);
        
            String sql_delete = "DELETE FROM escuela WHERE codigo_escuela= ?;";
            PreparedStatement statement_delete;
        try {
            statement_delete = Conexion.conn.prepareStatement(sql_delete);
            statement_delete.setInt(1, id);
            int rowsDeleted = statement_delete.executeUpdate();
            if ( rowsDeleted > 0){
                System.out.println(" Borrado exitoso !");                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }
    
    
    public void agregarEscuela() throws SQLException{
        
        String sql = "INSERT INTO escuela(codigo_escuela, nombre, habilidad_principal, anios_servicio, creador, fecha_inicio) VALUES  (?,?,?,?,?,?)";
        PreparedStatement statement = Conexion.conn.prepareStatement(sql);

        statement.setInt(1, id);
        statement.setString(2, nombre);
        statement.setString(3, habilidad);
        statement.setInt(4, anios_servicio);        
        statement.setString(5, creador);
        statement.setString(6, fecha);     
        int rowsInserted = statement.executeUpdate();
        
        if ( rowsInserted > 0) {
          System.out.println ("Insercion exitosa !");
        }else{
            System.out.println("no sirveee");
        }
                  
    }

    public static Object[][] consultar() throws SQLException {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        String sql = "SELECT * FROM escuela";
        PreparedStatement statement2 = Conexion.conn.prepareStatement(sql);
        Statement statement = conn.createStatement();
        ResultSet result = statement2.executeQuery(sql);
        int count = 0;

        while (result.next()) {
            int id = result.getInt(1);
            String nombre = result.getString(2);
            String habilidad_principal = result.getString(3);
            int anios_creado = result.getInt(4);
            String creador = result.getString(5);
            String fecha = result.getString(6);
            filas.add(id);
            filas.add(nombre);
            filas.add(habilidad_principal);
            filas.add(anios_creado);
            filas.add(creador);
            filas.add(fecha);
            columnas.add(filas);
            filas = new ArrayList<>();
        }
        
        Object[][] datos = new Object[columnas.size()][6];
        for(int i = 0; i < columnas.size(); i++){
            for(int j=0; j < 6; j++){
               datos[i][j] = columnas.get(i).get(j);
            } 
        }
        return datos;
    }
    
    public static int getId(String texto){
        String sql = "SELECT codigo_escuela FROM escuela WHERE nombre = ?";
        int id = 0;
        try {
            PreparedStatement preparedStatement = Conexion.conn.prepareStatement(sql);
            preparedStatement.setString(1,texto);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Id Escuela: " + id);
        return id;
    }
    
        public static String getNombre(int codigo){
        String sql = "SELECT nombre FROM escuela WHERE codigo_escuela = ?";
        String nombre = "";
        try {
            PreparedStatement preparedStatement = Conexion.conn.prepareStatement(sql);
            preparedStatement.setInt(1,codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                nombre = resultSet.getString(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Escuela.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Nombre de Escuela "  + nombre);
        return nombre;
    }
    
    
    
    public static Object[][] buscar(String texto) {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        System.out.println("LLego al metodo BuscarHabilidad");
        try {
            String sql = "SELECT * FROM escuela WHERE nombre LIKE ?";
            PreparedStatement preparedStatement = Conexion.conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + texto + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //orden de la base de datos
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String habilidad_principal = resultSet.getString(3);
                int anios_creado = resultSet.getInt(4);
                String creador = resultSet.getString(5);
                String fecha = resultSet.getString(6);
                filas.add(id);
                filas.add(nombre);
                filas.add(habilidad_principal);
                filas.add(anios_creado);
                filas.add(creador);
                filas.add(fecha);
                columnas.add(filas);
                filas = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Ningun Resultado");
        }
        Object[][] datos = {{}};
        if(columnas.size() != 0){
            datos = new Object[columnas.size()][columnas.get(0).size()];
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < datos[0].length; j++) {
                    datos[i][j] = columnas.get(i).get(j);
                }
            }
        }

        return datos;
    }

 

   
}
