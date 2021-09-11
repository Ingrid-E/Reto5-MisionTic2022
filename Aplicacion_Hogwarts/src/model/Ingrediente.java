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

public class Ingrediente {

    private int id;
    private int peso_promedio;
    private double nivel_reaccion;
    private boolean existe_bodega;
    private String nombre;

    public Ingrediente(int id, String nombre, int peso_promedio, double nivel_reaccion, boolean existe_bodega) {
        this.id = id;
        this.nombre = nombre;
        this.peso_promedio = peso_promedio;
        this.nivel_reaccion = nivel_reaccion;
        this.existe_bodega = existe_bodega;
    }

    public void actualizar() {

            String sql_actualizar = "UPDATE ingrediente"
                    + " SET nombre = ?, peso_promedio= ?, nivel_reaccion=?,existe_en_bodega=? "
                    + "WHERE codigo_ingrediente = ?;";
            PreparedStatement statement_actualizar;
        try {
            statement_actualizar = Conexion.conn.prepareStatement(sql_actualizar);
            System.out.println(nombre + " "+peso_promedio+ " "+nivel_reaccion+ " "+existe_bodega+ " "+id);
            statement_actualizar.setString(1, this.nombre);
            statement_actualizar.setInt(2, this.peso_promedio);
            statement_actualizar.setDouble(3, this.nivel_reaccion);
            statement_actualizar.setBoolean(4, this.existe_bodega);
            statement_actualizar.setInt(5, this.id);

            int rowsUpdated = statement_actualizar.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("El registro fue " + "actualizado exitosamente !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

    public static void eliminar(int id) {
        try {
            String sql_delete = "DELETE FROM ingrediente WHERE codigo_ingrediente= ?";
            PreparedStatement statement_delete = Conexion.conn.prepareStatement(sql_delete);
            statement_delete.setInt(1, id);
            int rowsDeleted = statement_delete.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(" Borrado exitoso !");
            }
        } catch (SQLException ex) {
        }
    }

    public static Object[][] consultar() throws SQLException {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        String sql = "SELECT * FROM ingrediente";
        PreparedStatement statement2 = Conexion.conn.prepareStatement(sql);
        Statement statement = conn.createStatement();
        ResultSet result = statement2.executeQuery(sql);
        int count = 0;
        //System.out.println(result.last());

        //Object[][] datos = new Object[result.getArray(sql)][6];
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);
            int peso_promedio = result.getInt(3);
            Double nivel_reaccion = result.getDouble(4);
            Boolean existe_en_bodega = result.getBoolean(5);
            filas.add(codigo);
            filas.add(nombre);
            filas.add(peso_promedio);
            filas.add(nivel_reaccion);
            filas.add(existe_en_bodega);
            columnas.add(filas);
            filas = new ArrayList<>();
        }

        Object[][] datos = new Object[columnas.size()][columnas.get(0).size()];
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[0].length; j++) {
                datos[i][j] = columnas.get(i).get(j);
            }
        }
        return datos;
    }

    public static Object[][] buscar(String habilidad) {
        ArrayList<ArrayList<Object>> columnas = new ArrayList<>();
        ArrayList<Object> filas = new ArrayList<>();
        System.out.println("LLego al metodo BuscarHabilidad");
        try {
            String sql = "SELECT * FROM ingrediente WHERE nombre LIKE ?";
            PreparedStatement preparedStatement = Conexion.conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + habilidad + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { //orden de la base de datos
                int codigo = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                int peso_promedio = resultSet.getInt(3);
                Double nivel_reaccion = resultSet.getDouble(4);
                Boolean existe_en_bodega = resultSet.getBoolean(5);
                filas.add(codigo);
                filas.add(nombre);
                filas.add(peso_promedio);
                filas.add(nivel_reaccion);
                filas.add(existe_en_bodega);
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

    public void agregarIngrediente() throws SQLException {

        String sql = "INSERT INTO ingrediente(codigo_ingrediente, nombre, peso_promedio, nivel_reaccion, existe_en_bodega) VALUES  (?,?,?,?,?)";
        PreparedStatement statement = Conexion.conn.prepareStatement(sql);

        statement.setInt(1, id);
        statement.setString(2, nombre);
        statement.setDouble(3, peso_promedio);
        statement.setDouble(4, nivel_reaccion);
        statement.setBoolean(5, existe_bodega);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insercion exitosa !");
        } else {
            System.out.println("no sirveee");
        }

    }

}
