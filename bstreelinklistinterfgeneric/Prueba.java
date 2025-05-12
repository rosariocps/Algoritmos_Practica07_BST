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
            arbolito2.insert(0.5);
            arbolito2.insert(0.9);
            arbolito2.insert(10.6);

            System.out.println("Elementos de arbolito2: ");
            System.out.println(arbolito2);
            // Número de nodos en total de un arbol
            System.out.println("Cantidad total de nodos: " + arbolito2.countAllNodes());
            // Número de nodos no-hoja en total de un arbol
            System.out.println("Cantidad total de nodos no-hoja: " + arbolito2.countNoHojas());
            // Número de nodos hoja en total de un arbol
            System.out.println("Cantidad total de nodos hoja: " + arbolito2.countHojas());
            // Altura de un arbol respecto a "x"            
            System.out.println("Altura del subarbol con raiz 7.7: " + arbolito2.height(7.7));
            System.out.println("Altura del arbol completo: " + arbolito2.height(2.2));
            // Amplitudes del arbol
            System.out.println("Amplitud del arbol en nivel 0: " + arbolito2.amplitude(0));
            System.out.println("Amplitud del arbol en nivel 1: " + arbolito2.amplitude(1));
            System.out.println("Amplitud del arbol en nivel 2: " + arbolito2.amplitude(2));
            System.out.println("Amplitud del arbol en nivel 3: " + arbolito2.amplitude(3));
            // Area del arbol
            System.out.println("El area del arbol es: " + arbolito2.areaBST());
            // Dibujar arbol
            System.out.println("Dibujo del arbol: ");
            System.out.println(arbolito2.drawBST());

            LinkedBST<String> arbolDeStrings = new LinkedBST<>();
            arbolDeStrings.insert("S");
            arbolDeStrings.insert("A");
            arbolDeStrings.insert("L");
            arbolDeStrings.insert("U");
            arbolDeStrings.insert("D");
            arbolDeStrings.insert("O");

            // Dibujar arbol
            System.out.println("Dibujo del arbolDeStrings: ");
            System.out.println(arbolDeStrings.drawBST());

            // Verificar si dos arboles tienen el mismo área
            System.out.println("¿Los arboles arbolito2 y arbolDeStrings tiene la misma area?");
            System.out.println(sameArea(arbolito2,arbolDeStrings));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //METODO QUE VERIFICA SI DOS ARBOLES TIENEN LA MISMA AREA
    public static boolean sameArea(LinkedBST<?> arbol1,LinkedBST<?> arbol2){
        try {
            return arbol1.areaBST() == arbol2.areaBST();
        } catch (Exception e) {
            System.out.println("Error al calcular área: " + e.getMessage());
            return false;
        }
    }
}
