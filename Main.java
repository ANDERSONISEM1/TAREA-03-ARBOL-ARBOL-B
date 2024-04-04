
package tarea.pkg03;



import java.util.Scanner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el grado del árbol B
        System.out.print("Ingrese el grado del árbol B: ");
        int grado = scanner.nextInt();

        // Establecer el número máximo de claves en un nodo basado en el grado
        Nodo.MAX_CLAVES = grado - 1;

        // Crear un nuevo árbol B
        ArbolB arbolB = new ArbolB();

        // Ejemplo de inserción de claves en el árbol B
        arbolB.insertar(8);
        arbolB.insertar(12);
        arbolB.insertar(3);
        arbolB.insertar(17);

        // Mostrar si una clave específica está presente en el árbol B
        System.out.println("Búsqueda de la Clave:");
        System.out.println("¿La clave 3 está en el árbol? " + arbolB.buscar(3));
        System.out.println("¿La clave 17 está en el árbol? " + arbolB.buscar(17));

        // Ejemplo de eliminación de una clave del árbol B
        arbolB.eliminar(10); // Suponiendo que 10 no está presente en el árbol
        System.out.println("Nodo 10 eliminado..");

        scanner.close();
    }
}
