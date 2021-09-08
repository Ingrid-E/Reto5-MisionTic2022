
package model;

public class ingrediente {
    public int id;
    public double peso_promedio;
    public double nivel_reaccion;
    public boolean existe_bodega;

    public ingrediente(int id, double peso_promedio, double nivel_reaccion, boolean existe_bodega) {
        this.id = id;
        this.peso_promedio = peso_promedio;
        this.nivel_reaccion = nivel_reaccion;
        this.existe_bodega = existe_bodega;
    }
    
}
