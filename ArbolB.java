
package tarea.pkg03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArbolB {
    private Nodo raiz; // Nodo raíz del árbol

    public ArbolB() {
        raiz = new Nodo(); // Se inicializa la raíz como un nuevo nodo vacío
    }

    // Método para insertar una clave en el árbol
    public void insertar(int clave) {
        if (raiz.claves.size() == Nodo.MAX_CLAVES) { // Si la raíz está llena, se divide
            Nodo nuevaRaiz = new Nodo(); // Se crea una nueva raíz
            nuevaRaiz.hijos.add(raiz); // La raíz actual se convierte en hijo de la nueva raíz
            dividirHijo(nuevaRaiz, 0); // Se divide el hijo de la nueva raíz
            raiz = nuevaRaiz; // La nueva raíz se convierte en la raíz del árbol
        }
        insertarNoLleno(raiz, clave); // Se inserta la clave en un nodo no lleno
    }

    // Método recursivo para insertar en un nodo no lleno
    private void insertarNoLleno(Nodo nodo, int clave) {
        // Se busca la posición correcta para la clave
        int i = nodo.claves.size() - 1;
        if (nodo.esHoja) {
            while (i >= 0 && clave < nodo.claves.get(i)) {
                i--;
            }
            nodo.claves.add(i + 1, clave); // Se inserta la clave en la posición correcta
        } else {
            while (i >= 0 && clave < nodo.claves.get(i)) {
                i--;
            }
            i++;
            if (nodo.hijos.get(i).claves.size() == Nodo.MAX_CLAVES) { // Si el hijo está lleno, se divide
                dividirHijo(nodo, i);
                if (clave > nodo.claves.get(i)) {
                    i++;
                }
            }
            insertarNoLleno(nodo.hijos.get(i), clave); // Se inserta la clave en el hijo adecuado
        }
    }

    // Método para dividir un hijo de un nodo
    private void dividirHijo(Nodo padre, int indiceHijo) {
        Nodo hijoAdividir = padre.hijos.get(indiceHijo); // Se obtiene el hijo a dividir
        Nodo nuevoHijo = new Nodo(); // Se crea un nuevo hijo
        nuevoHijo.esHoja = hijoAdividir.esHoja; // Se copia el tipo de nodo

        // Se mueve la clave mediana al padre
        padre.claves.add(indiceHijo, hijoAdividir.claves.get(Nodo.MAX_CLAVES / 2));
        padre.hijos.add(indiceHijo + 1, nuevoHijo); // Se añade el nuevo hijo al padre

        // Se mueven las claves mayores a la clave mediana al nuevo hijo
        for (int i = 0; i < Nodo.MAX_CLAVES / 2; i++) {
            nuevoHijo.claves.add(hijoAdividir.claves.remove(Nodo.MAX_CLAVES / 2));
        }
        // Si no es hoja, se mueven los hijos correspondientes
        if (!hijoAdividir.esHoja) {
            for (int i = 0; i <= Nodo.MAX_CLAVES / 2; i++) {
                nuevoHijo.hijos.add(hijoAdividir.hijos.remove(Nodo.MAX_CLAVES / 2));
            }
        }
    }

    // Método para buscar una clave en el árbol
    public boolean buscar(int clave) {
        return buscar(raiz, clave);
    }

    // Método recursivo para buscar una clave en un nodo
    private boolean buscar(Nodo nodo, int clave) {
        int i = 0;
        while (i < nodo.claves.size() && clave > nodo.claves.get(i)) {
            i++;
        }
        if (i < nodo.claves.size() && clave == nodo.claves.get(i)) {
            return true; // La clave está en el nodo
        }
        if (nodo.esHoja) {
            return false; // La clave no está en el árbol
        }
        return buscar(nodo.hijos.get(i), clave); // Se busca en el hijo correspondiente
    }

    // Método para eliminar una clave del árbol
    public void eliminar(int clave) {
        eliminar(raiz, clave);
    }

    // Método recursivo para eliminar una clave de un nodo
    private void eliminar(Nodo nodo, int clave) {
        int indice = nodo.claves.indexOf(clave); // Se busca la clave en el nodo
        if (indice != -1) { // Si la clave está en el nodo
            if (nodo.esHoja) {
                nodo.claves.remove(indice); // Se elimina la clave del nodo hoja
            } else {
                // Se reemplaza la clave por la clave más a la izquierda del subárbol derecho
                Nodo sucesor = encontrarSucesor(nodo.hijos.get(indice + 1));
                nodo.claves.set(indice, sucesor.claves.get(0));
                eliminar(nodo.hijos.get(indice + 1), sucesor.claves.get(0)); // Se elimina la clave del subárbol derecho
            }
        } else { // Si la clave no está en el nodo
            int i = 0;
            while (i < nodo.claves.size() && clave > nodo.claves.get(i)) {
                i++;
            }
            if (!nodo.esHoja) {
                if (i < nodo.claves.size() && clave == nodo.claves.get(i)) {
                    eliminar(nodo.hijos.get(i + 1), clave); // Se elimina la clave del hijo derecho
                } else {
                    eliminar(nodo.hijos.get(i), clave); // Se elimina la clave del hijo correspondiente
                }
            }
        }
    }

    // Método para encontrar el sucesor de un nodo (el nodo más a la izquierda)
    private Nodo encontrarSucesor(Nodo nodo) {
        while (!nodo.esHoja) {
            nodo = nodo.hijos.get(0);
        }
        return nodo;
    }
}
