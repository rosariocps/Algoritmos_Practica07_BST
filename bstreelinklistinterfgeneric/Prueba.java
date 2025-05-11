package bstreelinklistinterfgeneric;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {
            // insertamos los nodos segun el arbol descrito
            arbol.insert(400); // raiz
            arbol.insert(100); // hijo izq de 400
            arbol.insert(700); // hijo der de 400
            arbol.insert(50);  // hijo izq de 100
            arbol.insert(200); // hijo der de 100
            arbol.insert(75);  // hijo der de 50

            // in-orden
            System.out.println("Recorrido In-Orden:");
            System.out.println(arbol);

            // pre-orden
            System.out.println("Recorrido Pre-Orden:");
            System.out.println(arbol.recorridoPreOrden());

            // post-orden
            System.out.println("Recorrido Post-Orden:");
            System.out.println(arbol.recorridoPostOrden());

            //hallar min y max
            System.out.println("Valor minimo en el BST: " + arbol.getMin());
            System.out.println("Valor maximo en el BST: " + arbol.getMax());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
