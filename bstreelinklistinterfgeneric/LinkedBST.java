package bstreelinklistinterfgeneric; // este es el paquete donde esta la clase

import actividad2.QueueLink;
import bstreeInterface.BinarySearchTree; // importo la interfaz que voy a implementar
import exceptions.ExceptionIsEmpty; // importo la excepcion si el arbol esta vacio
import exceptions.ItemDuplicated; // importo la excepcion si el dato ya existe
import exceptions.ItemNotFound; // importo la excepcion si el dato no se encuentra

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> { // esta es la clase generica que implementa la interfaz del arbol

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

    // METODO QUE CUENTA TODOS LOS NODOS DE UN ARBOL
    public int countAllNodes(){
        if(isEmpty()){ // Verifica si el arbol esta vacío, pues de estarlo, no tendría caso llamar al método recursivo
            return 0; // Devolvería cero y ahi queda
        }
        return countAllNodes(root); // Se llama al metodo recursivo y se inicializa con el primer nodo del árbol

    }

    private int countAllNodes(Node nodo){ // Método privado recursivo que cuenta todos los nodos
        if(nodo == null){ // Si el nodo es nulo significa que hemos llegado al final de una rama
            return 0;
        } 
        else{
            int contador = 1; // Si no es nulo contamos el nodo
            /* A contador le agregamos lo que retorna de aplicar este mismo método pero ahora el
            parametro es el hijo izquierdo. Y hace lo mismo con el derecho. */
            contador += countAllNodes(nodo.left);
            contador += countAllNodes(nodo.right);
            return contador; // Finalmente se retorna el valor que se ha acumulado
        }
    }

    // METODO QUE CUENTA TODOS LOS NODOS NO-HOJA DE UN ARBOL
    public int countNoHojas(){
        if(isEmpty()){
            return 0;
        }
        return countNoHojas(root);
    }

    private int countNoHojas(Node nodo){
        if(nodo == null){
            return 0;
        }

        if(nodo.left == null && nodo.right == null){
            return 0;
        }
        else{
            int contador = 1;
            contador += countNoHojas(nodo.left);
            contador += countNoHojas(nodo.right);
            return contador;
        }
    }
    
    // METODO QUE CUENTA LOS NODOS HOJA DE UN ARBOL
    public int countHojas(){
        if(isEmpty()){
            return 0;
        }
        return countHojas(root);
    }

    private int countHojas(Node nodo){
        if(nodo == null){
            return 0;
        }

        if(nodo.left == null && nodo.right == null){
            return 1;
        }
        
        return countHojas(nodo.left) + countHojas(nodo.right);
    }

    public int height(E x) throws ItemNotFound {
        Node nodoRaiz = getNode(x); // Obtenemos el nodo con la data x

        if (nodoRaiz == null) {
            return -1; // Si no existe el subárbol, devolvemos -1
        }

        QueueLink<Node> cola = new QueueLink<>(); // Creamos una cola para recorrido por niveles
        cola.enqueue(nodoRaiz); // Insertamos el nodo raíz del subárbol
        int altura = -1; // Inicializamos la altura

        while (!cola.isEmpty()) {
            int nivel = cola.numeroDeElementos(); // Número de nodos en el nivel actual
            altura++; // Aumentamos la altura por cada nivel recorrido

            for (int i = 0; i < nivel; i++) {
                try {
                    Node actual = cola.dequeue(); // Extraemos el nodo actual

                    if (actual.left != null) {
                        cola.enqueue(actual.left); // Encolamos hijo izquierdo si existe
                    }
                    if (actual.right != null) {
                        cola.enqueue(actual.right); // Encolamos hijo derecho si existe
                    }
                } catch (actividad1.ExceptionIsEmpty e) {
                    System.out.println("Error al intentar quitar de la cola: " + e.getMessage());
                }
                    
            }
        }
    return altura; // Devolvemos la altura final calculada
    }


    private Node getNode(E data) throws ItemNotFound {
        Node nodo = root;

        while(nodo != null){
            int comp = data.compareTo(nodo.data);

            if(comp == 0){
                return nodo;
            }
            else if(comp > 0){
                nodo = nodo.right;
            }
            else{
                nodo = nodo.left;
            }
        }
        throw new ItemNotFound("El nodo con data " + data + " no existe.");
    }

    
}
