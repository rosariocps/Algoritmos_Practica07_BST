package actividad2;

import actividad1.ExceptionIsEmpty;

public interface Queue<E> { 
    void enqueue(E x);  // -> Inserta un elemento al final de la cola
    E dequeue() throws ExceptionIsEmpty;  // -> Elimina y retorna el elemento al frente de la cola
    E front() throws ExceptionIsEmpty; // -> Retorna el elemento al frente sin eliminarlo
    E back() throws ExceptionIsEmpty;  // -> Retorna el último elemento agregado (final de la cola)
    boolean isEmpty(); // -> Retorna true si la cola no tiene elementos
}
