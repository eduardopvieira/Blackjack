package Classes;

import java.util.HashSet;

public class Baralho {

    //Essa classe serve basicamente como um hashset de todas as cartas tirando o coringa.

    private HashSet<Carta> baralho;

    public Baralho() {
        baralho = new HashSet<>();

        String[] naipes = {"Copas", "Espadas", "Paus", "Ouros"};
        int[] valores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}; //Achei melhor colocar o A, J, Q, K como 1, 11, 12 e 13 pra facilitar na hora da soma

        for (String naipe : naipes) {
            for (int valor : valores) {
                baralho.add(new Carta(valor, naipe));
            }
        }
    }

    public HashSet<Carta> getBaralho() {
        return baralho;
    }

    public void printBaralho() {
        for (Carta carta : baralho) {
            System.out.println(carta.getValor() + " " + carta.getNaipe());
        }
    }
}
