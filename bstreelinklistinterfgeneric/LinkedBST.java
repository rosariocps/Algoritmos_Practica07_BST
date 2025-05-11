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
        root = delete(root, data); // llamo al metodo recursivo empezando desde la raiz
    }
    /**
     * Elimina un nodo del árbol considerando los tres casos:
     * 1. Nodo sin hijos (hoja)
     * 2. Nodo con un solo hijo
     * 3. Nodo con dos hijos (se reemplaza con el mínimo del subárbol derecho)
     */
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
            //Caso 1 o 2
            if (node.left == null) return node.right; // si no tiene hijo izquierdo, lo reemplazo por el derecho
            if (node.right == null) return node.left; // si no tiene hijo derecho, lo reemplazo por el izquierdo
            //Caso 3: tiene dos hijos
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

    public String recorridoPreOrden() {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString().trim();
    }
    // metodo que recorre el arbol en pre-orden y guarda los datos en el StringBuilder
    private void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");   // visita la raiz primero
            preOrder(node.left, sb);            // luego recorre subarbol izquierdo
            preOrder(node.right, sb);           // luego recorre subarbol derecho
        }
    }

    public String recorridoPostOrden() {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString().trim();
    }    
    // metodo que recorre el arbol en post-orden y guarda los datos en el StringBuilder
    private void postOrder(Node node, StringBuilder sb) {
        if (node != null) {
            postOrder(node.left, sb);   // primero recorre subárbol izquierdo
            postOrder(node.right, sb);  // luego recorre subárbol derecho
            sb.append(node.data).append(" "); // al final visita la raíz
        }
    }
    private E findMinNode(Node node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Subárbol vacío, no se puede encontrar el mínimo");
        }
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    private E findMaxNode(Node node) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Subárbol vacío, no se puede encontrar el máximo");
        }
        Node current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public E getMin() throws ItemNotFound {
        return findMinNode(root);
    }
    
    public E getMax() throws ItemNotFound {
        return findMaxNode(root);
    }

    //Método que elimina todos los nodos de un BST
    public void destroyNodes() throws ExceptionIsEmpty{
        if(isEmpty()){
            throw new ExceptionIsEmpty("No se puede eliminar todos los nodos porque el arbol ya esta vacío.");
        }
        root = null;
    }
    
}
