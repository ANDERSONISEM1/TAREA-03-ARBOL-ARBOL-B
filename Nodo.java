
package tarea.pkg03;
import java.util.ArrayList;
import java.util.List;

public class Nodo {
    static int MAX_CLAVES; // Número máximo de claves en un nodo

    List<Integer> claves; // Lista de claves almacenadas en el nodo
    List<Nodo> hijos; // Lista de nodos hijos
    boolean esHoja; // Indica si el nodo es una hoja o no

    public Nodo() {
        // Inicializa las listas de claves y de hijos con la capacidad máxima permitida
        claves = new ArrayList<>(MAX_CLAVES);
        hijos = new ArrayList<>(MAX_CLAVES + 1);
        esHoja = true; // Por defecto, un nuevo nodo se considera una hoja
    }
}
