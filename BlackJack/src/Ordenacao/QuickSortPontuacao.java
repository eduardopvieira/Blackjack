package Ordenacao;
import java.util.ArrayList;
import Classes.Pontuacao;

public class QuickSortPontuacao {

    public void sort(ArrayList<Pontuacao> pontuacoes) {
        if (pontuacoes == null || pontuacoes.size() == 0) {
            return;
        }
        quicksort(pontuacoes, 0, pontuacoes.size() - 1);
    }

    private void quicksort(ArrayList<Pontuacao> pontuacoes, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(pontuacoes, low, high);
            quicksort(pontuacoes, low, pivotIndex - 1);
            quicksort(pontuacoes, pivotIndex + 1, high);
        }
    }

    private int partition(ArrayList<Pontuacao> pontuacoes, int low, int high) {
        double pivot = pontuacoes.get(high).getPontos();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (pontuacoes.get(j).getPontos() >= pivot) {
                i++;
                swap(pontuacoes, i, j);
            }
        }
        swap(pontuacoes, i + 1, high);
        return i + 1;
    }

    private void swap(ArrayList<Pontuacao> pontuacoes, int i, int j) {
        Pontuacao temp = pontuacoes.get(i);
        pontuacoes.set(i, pontuacoes.get(j));
        pontuacoes.set(j, temp);
    }

    public void printPontuacoes(ArrayList<Pontuacao> pontuacoes) {
        for (Pontuacao pontuacao : pontuacoes) {
            System.out.println(pontuacao);
        }
    }
}
