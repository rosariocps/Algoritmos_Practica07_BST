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

            // Eliminar todos los nodos del arbol
            arbol.destroyNodes();
            System.out.println("Resultado despues de eliminar todos los nodos: ");
            System.out.println(arbol);

            LinkedBST<Double> arbolito2 = new LinkedBST<>();

            arbolito2.insert(2.2);
            arbolito2.insert(7.7);
            arbolito2.insert(5.5);
            arbolito2.insert(4.4);
            arbolito2.insert(1.1);
            arbolito2.insert(9.9);
            arbolito2.insert(6.6);

            System.out.println("Elementos de arbolito2: ");
            System.out.println(arbolito2);
            // Número de nodos en total de un arbol
            System.out.println("Cantidad total de nodos: " + arbolito2.countAllNodes());
            // Número de nodos no-hoja en total de un arbol
            System.out.println("Cantidad total de nodos no-hoja: " + arbolito2.countNoHojas());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
