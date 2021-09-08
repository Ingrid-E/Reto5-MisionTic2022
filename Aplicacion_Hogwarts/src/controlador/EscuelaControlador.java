
package controlador;
import model.escuela;
import java.util.ArrayList;
public class EscuelaControlador {
    public static ArrayList<escuela> escuelas = new ArrayList<>();
    public static escuela Crear(int codigo, String nombre, String creador, int anios, String fecha, String habilidad){
        escuela school = new escuela(codigo,nombre,creador,anios,fecha,habilidad);
        escuelas.add(school);
        return school;
    }
}
