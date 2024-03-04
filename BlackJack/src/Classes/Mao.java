package Classes;

import java.util.LinkedList;

public class Mao {
    LinkedList<Carta> listaCartas = new LinkedList<>();

    public Mao() {}

    public int getSomaTotal() {
        int somaTotal = 0;

        for (Carta carta : listaCartas) {
            somaTotal += carta.getValor();
        }

        return somaTotal;
    }

    public int getQtdCartas() {
        return listaCartas.size();
    }

    public LinkedList<Carta> getListaCartas() {
        return listaCartas;
    }

    public void adicionarCarta(Carta carta){
        this.listaCartas.add(carta);
    }

    public void mostraMao(){
        System.out.println("Sua mão atual é:");

        for (Carta carta : listaCartas) {
            System.out.println(carta.getValor() + " de " + carta.getNaipe());
        }
    }


    
}
