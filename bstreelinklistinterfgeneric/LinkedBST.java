package bstreelinklistinterfgeneric; // este es el paquete donde esta la clase

import bstreeInterface.BinarySearchTree; // importo la interfaz que voy a implementar
import exceptions.ExceptionIsEmpty; // importo la excepcion si el arbol esta vacio
import exceptions.ItemDuplicated; // importo la excepcion si el dato ya existe
import exceptions.ItemNotFound; // importo la excepcion si el dato no se encuentra

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> { // esta es mi clase generica que implementa la interfaz del arbol

    class Node { // clase interna para los nodos del arbol
        E data; // este es el dato del nodo
        Node left, right; // hijos izquierdo y derecho

        Node(E data) { // constructor del nodo
            this.data = data; // le asigno el dato
            left = right = null; // al inicio los hijos son null
        }
    }

    private Node root; // este es el nodo raiz del arbol
    private boolean eliminado; // bandera para saber si se elimino un nodo

    public LinkedBST() { // constructor del arbol
        root = null; // al inicio el arbol esta vacio
    }

    @Override
    public void insert(E data) throws ItemDuplicated { // metodo para insertar
        root = insert(root, data); // llamo al metodo recursivo empezando desde la raiz
    }

    private Node insert(Node node, E data) throws ItemDuplicated { // metodo recursivo para insertar
        if (node == null) { // si el nodo es null, entonces ahi va el nuevo dato
            return new Node(data); // creo el nuevo nodo
        }
        int comp = data.compareTo(node.data); // comparo el nuevo dato con el actual
        if (comp < 0) { // si es menor, lo inserto a la izquierda
            node.left = insert(node.left, data);
        } else if (comp > 0) { // si es mayor, lo inserto a la derecha
            node.right = insert(node.right, data);
        } else { // si es igual, lanzo la excepcion porque ya existe
            throw new ItemDuplicated("El dato '" + data + "' ya existe en el arbol.");
        }
        return node; // devuelvo el nodo actualizado
    }

    @Override
    public E search(E data) throws ItemNotFound { // metodo para buscar un dato
        Node result = search(root, data); // empiezo desde la raiz
        if (result == null) { // si no lo encuentro, lanzo la excepcion
            throw new ItemNotFound("El dato '" + data + "' no fue encontrado.");
        }
        return result.data; // si lo encuentro, devuelvo el dato
    }

    private Node search(Node node, E data) { // metodo recursivo para buscar
        if (node == null) { // si llego a null, no esta el dato
            return null;
        }
        int comp = data.compareTo(node.data); // comparo el dato con el actual
        if (comp == 0) { // si son iguales, lo encontre
            return node;
        } else if (comp < 0) { // si es menor, sigo buscando a la izquierda
            return search(node.left, data);
        } else { // si es mayor, sigo buscando a la derecha
            return search(node.right, data);
        }
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty { // metodo para eliminar
        if (isEmpty()) { // si el arbol esta vacio, lanzo excepcion
            throw new ExceptionIsEmpty("el arbol esta vacio");
        }
        eliminado = false; // al inicio no se ha eliminado nada
        root = delete(root, data); // llamo al metodo recursivo empezando desde la raiz
    }

    private Node delete(Node node, E data) { // metodo recursivo para eliminar
        if (node == null) { // si no encuentro el nodo, retorno null
            return null;
        }
        int comp = data.compareTo(node.data); // comparo el dato con el nodo actual
        if (comp < 0) { // si es menor, busco a la izquierda
            node.left = delete(node.left, data);
        } else if (comp > 0) { // si es mayor, busco a la derecha
            node.right = delete(node.right, data);
        } else { // si son iguales, lo encontre y lo elimino
            eliminado = true; // marco que ya lo elimine
            if (node.left == null) return node.right; // si no tiene hijo izquierdo, lo reemplazo por el derecho
            if (node.right == null) return node.left; // si no tiene hijo derecho, lo reemplazo por el izquierdo

            Node min = min(node.right); // si tiene dos hijos, busco el menor del lado derecho
            node.data = min.data; // copio ese valor en el nodo actual
            node.right = delete(node.right, min.data); // y ahora elimino ese nodo menor
        }
        return node; // devuelvo el nodo actualizado
    }

    private Node min(Node node) { // metodo para encontrar el menor de un subarbol
        while (node.left != null) { // voy bajando por la izquierda
            node = node.left;
        }
        return node; // cuando ya no hay mas izquierda, ese es el menor
    }

    @Override
    public boolean isEmpty() { // metodo para saber si el arbol esta vacio
        return root == null; // si la raiz es null, esta vacio
    }

    @Override
    public String toString() { // metodo para mostrar los datos del arbol
        StringBuilder sb = new StringBuilder(); // creo un objeto para construir el texto
        inorder(root, sb); // llamo al recorrido inorden desde la raiz
        return sb.toString().trim(); // devuelvo el resultado sin espacios al final
    }

    private void inorder(Node node, StringBuilder sb) { // metodo recursivo para recorrer en inorden
        if (node != null) { // si el nodo no es null
            inorder(node.left, sb); // primero voy a la izquierda
            sb.append(node.data).append(" "); // luego agrego el dato actual
            inorder(node.right, sb); // y luego voy a la derecha
        }
    }
}
