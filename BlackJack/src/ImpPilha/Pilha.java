package ImpPilha;

import Exception.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pilha<T> implements InterfacePilha<T> {

    private int topo;
    private int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public Pilha(int size) {
        this.topo = -1;
        this.size = size;
        this.array = ((T[]) new Object[size]);
    }

    @Override
    public void push(T valor) throws MyException {
        if (isFull()) {
            throw new MyException("Pilha já está lotada.");
        }
        topo++;
        array[topo] = valor;
    }

    @Override
    public T pop() throws MyException {
        if (isEmpty()) {
            throw new MyException("Pilha está vazia.");
        }
        T pivo = array[topo];
        topo--;
        return pivo;
    }

    @Override
    public T peek() throws MyException {
        if (isEmpty()) {
            throw new MyException("Pilha está vazia.");
        }
        return array[topo];
    }

    public void embaralharPilha() {
        List<T> listaProv = Arrays.asList(array);
        Collections.shuffle(listaProv); //Método do proprio java para embaralhar listas. não tem pra array, entao tive que transformar
        listaProv.toArray(array);
    }

    @Override
    public boolean isEmpty() {
        if (topo == -1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (topo == (this.size - 1)) {
            return true;
        }
        return false;
    }
}
