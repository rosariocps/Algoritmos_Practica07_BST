package bstreelinklistinterfgeneric;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol = new LinkedBST<>();

        try {
            // insertamos los nodos en orden para que el arbol tenga la forma descrita
            arbol.insert(400); // raiz
            arbol.insert(100); // hijo izq de 400
            arbol.insert(700); // hijo der de 400
            arbol.insert(50);  // hijo izq de 100
            arbol.insert(200); // hijo der de 100
            arbol.insert(75);  // hijo der de 50

            // imprimimos el recorrido in-orden
            System.out.println("Recorrido In-Orden:");
            System.out.println(arbol); // usa toString que llama a inorder

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
