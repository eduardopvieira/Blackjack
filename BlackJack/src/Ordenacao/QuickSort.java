package Ordenacao;
import java.util.ArrayList;

public class QuickSort<T extends Comparable<T>> {

    public void sort(ArrayList<T> elementos) {
        if (elementos == null || elementos.size() == 0) {
            return;
        }
        quicksort(elementos, 0, elementos.size() - 1);
    }

    private void quicksort(ArrayList<T> elementos, int baixo, int alto) {
        if (baixo < alto) {
            int pivotIndex = particionar(elementos, baixo, alto);
            quicksort(elementos, baixo, pivotIndex - 1);
            quicksort(elementos, pivotIndex + 1, alto);
        }
    }

    private int particionar(ArrayList<T> elementos, int baixo, int alto) {
        T pivot = elementos.get(alto);
        int i = baixo - 1;
        for (int j = baixo; j < alto; j++) {
            if (elementos.get(j).compareTo(pivot) >= 0) {
                i++;
                trocar(elementos, i, j);
            }
        }
        trocar(elementos, i + 1, alto);
        return i + 1;
    }

    private void trocar(ArrayList<T> elementos, int i, int j) {
        T temp = elementos.get(i);
        elementos.set(i, elementos.get(j));
        elementos.set(j, temp);
    }

    //usado só pra fins de debug
    public void imprimir(ArrayList<T> elementos) {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    }
}
