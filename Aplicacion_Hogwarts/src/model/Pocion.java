
package model;

import controlador.Conexion;
import static controlador.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pocion {
    public String nombre;
    public double litros_realizados;
    public int veces_utilizada,id_escuela,id;

    public Pocion(int id, String nombre, double litros_realizados, int veces_utilizada, int id_escuela) {
        this.id = id;
        this.nombre = nombre;
        this.litros_realizados = litros_realizados;
        this.veces_utilizada = veces_utilizada;
        this.id_escuela = id_escuela;
    }
    
    public void actualizar() {

            String sql_actualizar = "UPDATE pocion"
                    + " SET nombre = ?, registro_litros= ?, uso=?,id_escuela=? "
                    + "WHERE codigo_pocion = ?;";
            System.out.println(this.nombre +" " + this.litros_realizados +" " + this.veces_utilizada +" " + this.id_escuela +" " + this.id);
            PreparedStatement statement_actualizar;
        try {
            statement_actualizar = Conexion.conn.prepareStatement(sql_actualizar);
            statement_actualizar.setString(1, this.nombre);
            statement_actualizar.setDouble(2, this.litros_realizados);
            statement_actualizar.setInt(3, this.veces_utilizada);
            statement_actualizar.setInt(4, this.id_escuela);
            statement_actualizar.setInt(5, this.id);

            int rowsUpdated = statement_actualizar.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue " + "actualizado exitosamente !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pocion.class.getName()).log(Level.SEVERE, null, ex);
        }
            

    }
    
    
        public static void eliminar(int id){
        try {
            String sql_delete = "DELETE FROM pocion WHERE codigo_pocion= ?";
            PreparedStatement statement_delete = Conexion.conn.prepareStatement(sql_delete);
            statement_delete.setInt(1, id);
            int rowsDeleted = statement_delete.executeUpdate();
            if ( rowsDeleted > 0){
                System.out.println(" Borrado exitoso !");                   
            }
        } catch (SQLException ex) {
        }
    }

    public static Object[][] buscar(String habilidad) {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        System.out.println("LLego al metodo BuscarHabilidad");
        try {
            String sql = "SELECT * FROM pocion WHERE nombre LIKE ?";
            PreparedStatement preparedStatement = Conexion.conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + habilidad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //orden de la base de datos
                int codigo = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                double registro_litros = resultSet.getDouble(3);
                int uso = resultSet.getInt(4);
                int id_escuela = resultSet.getInt(5);
                filas.add(codigo);
                filas.add(nombre);
                filas.add(registro_litros);
                filas.add(uso);
                filas.add(id_escuela);
                columnas.add(filas);
                filas = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Ningun Resultado");
        }
        Object[][] datos = {{}};
        if (columnas.size() != 0) {
            datos = new Object[columnas.size()][columnas.get(0).size()];
            for (int i = 0; i < datos.length; i++) {
                for (int j = 0; j < datos[0].length; j++) {
                    datos[i][j] = columnas.get(i).get(j);
                }
            }
        }

        return datos;
    }

     public static Object[][] consultar() throws SQLException {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        String sql = "SELECT * FROM pocion";
        PreparedStatement statement2 = Conexion.conn.prepareStatement(sql);
        Statement statement = conn.createStatement();
        ResultSet result = statement2.executeQuery(sql);
        int count = 0;
        //System.out.println(result.last());

        //Object[][] datos = new Object[result.getArray(sql)][6];
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);
            double registro_litros = result.getDouble(3);
            int uso = result.getInt(4);
            String nombre_Escuela = Escuela.getNombre(result.getInt(5));
            filas.add(codigo);
            filas.add(nombre);
            filas.add(registro_litros);
            filas.add(uso);
            filas.add(nombre_Escuela);
            columnas.add(filas);
            filas = new ArrayList<>();
        }
        
        Object[][] datos = new Object[columnas.size()][columnas.get(0).size()];
        for(int i = 0; i < datos.length; i++){
            for(int j=0; j < datos[0].length; j++){
               datos[i][j] = columnas.get(i).get(j);
            } 
        }
        return datos;
    }
    
    public void agregarPocion() throws SQLException {

        String sql = "INSERT INTO pocion(codigo_pocion, nombre, registro_litros, uso, id_escuela) VALUES  (?,?,?,?,?)";
        PreparedStatement statement = Conexion.conn.prepareStatement(sql);

        statement.setInt(1, id);
        statement.setString(2, nombre);
        statement.setDouble(3, litros_realizados);
        statement.setInt(4, veces_utilizada);
        statement.setInt(5, id_escuela);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insercion exitosa !");
        } else {
            System.out.println("no sirveee");
        }

    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLitros_realizados() {
        return litros_realizados;
    }

    public void setLitros_realizados(double litros_realizados) {
        this.litros_realizados = litros_realizados;
    }

    public int getVeces_utilizada() {
        return veces_utilizada;
    }

    public void setVeces_utilizada(int veces_utilizada) {
        this.veces_utilizada = veces_utilizada;
    }

  
}
