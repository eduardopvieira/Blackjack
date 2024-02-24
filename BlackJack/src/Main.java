import Classes.Baralho;
import Classes.Carta;
import Exception.MyException;
import ImpPilha.Pilha;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws MyException {

        int somaTotal = 0; //Soma das cartas que vc puxou até agora

        Baralho todasAsCartas = new Baralho(); //Inicializa todas as cartas
        HashSet<Carta> baralho = todasAsCartas.getBaralho(); //Cria um HashSet de todas as cartas
        Pilha<Carta> pilhaCartas = new Pilha<>(baralho.size()); //Cria a pilha do tamanho do baralho

        //Coloca todas as cartas do baralho na pilha. Porém, ela ainda não está embaralhada, entao precisamos embaralhar ela
        for (Carta carta : baralho) {
            pilhaCartas.push(carta);
        }

        pilhaCartas.embaralharPilha(); //Agora ela ta embaralhada


        //Eu queria colocar isso tudo ai em cima dentro de uma classe e a main ser só uma chamada de funçao pra essa
        //classe começar a rodar o jogo tlgd, mas por enquanto vai ficar ai


    }
}

/*
MÉTODO PARA VER TODAS AS CARTAS NA PILHA, usado so pra debugar

while (!pilhaCartas.isEmpty()) {
    Carta carta = pilhaCartas.pop();
    System.out.println("Valor: " + carta.getValor() + ", Naipe: " + carta.getNaipe());
}
*/
