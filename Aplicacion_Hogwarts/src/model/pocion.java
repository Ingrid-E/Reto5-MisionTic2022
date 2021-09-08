
package model;

import java.util.ArrayList;

public class pocion {
    public int id;
    public String nombre;
    public double litros_realizados;
    public int veces_utilizada;
    public ArrayList<pocion> pociones;

    public pocion(int id, String nombre, double litros_realizados, int veces_utilizada, ArrayList<pocion> pociones) {
        this.id = id;
        this.nombre = nombre;
        this.litros_realizados = litros_realizados;
        this.veces_utilizada = veces_utilizada;
        this.pociones = pociones;
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

    public ArrayList<pocion> getPociones() {
        return pociones;
    }

    public void setPociones(ArrayList<pocion> pociones) {
        this.pociones = pociones;
    }

    
 
    
    
}
