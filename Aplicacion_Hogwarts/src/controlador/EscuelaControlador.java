
package controlador;
import model.Escuela;
import java.util.ArrayList;
public class EscuelaControlador {
    public static ArrayList<Escuela> escuelas = new ArrayList<>();
    public static Escuela Crear(int codigo, String nombre, String creador, int anios, String fecha, String habilidad){
        Escuela school = new Escuela(codigo,nombre,creador,anios,fecha,habilidad);
        escuelas.add(school);
        return school;
    }
}
