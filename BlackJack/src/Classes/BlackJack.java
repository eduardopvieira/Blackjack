package Classes;
import ImpPilha.Pilha;
import java.util.HashSet;
import java.util.Scanner;

import Exception.MyException;

public class BlackJack {

    public BlackJack() {}; //Construtor

    public void start() throws MyException {

        int somaTotal = 0;              //Soma das cartas que vc puxou até agora
        boolean continuar = true;       //vê se o player decidiu parar
        int decisao;                    //vê se o player decidiu puxar outra carta ou para

        Pilha<Carta> mesa = prepararMesa();
        Scanner sc = new Scanner(System.in);

        while (somaTotal < 21 && continuar) {
            System.out.println("=-=-=-=-=-=-=-= BLACKJACK =-=-=-=-=-=-=-=");
            System.out.println("VALOR ATUAL: " + somaTotal);
            System.out.println("Puxar carta?");
            System.out.println("1 - Puxar || 2 - Parar");
            decisao = sc.nextInt();

            switch(decisao) {
                case 1:
                    Carta c = mesa.pop();
                    somaTotal += c.getValor();
                    System.out.println("Você puxou um " + c.getValor() + " de " + c.getNaipe());
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    System.out.println("Numero inválido. Escolha 1 ou 2.");
            }
        }

        fimDeJogo(somaTotal,mesa);

    }

    public Pilha<Carta> prepararMesa() throws MyException {
        Baralho todasAsCartas = new Baralho(); //Inicializa todas as cartas
        HashSet<Carta> baralho = todasAsCartas.getBaralho(); //Cria um HashSet de todas as cartas
        Pilha<Carta> pilhaCartas = new Pilha<>(baralho.size()); //Cria a pilha do tamanho do baralho

        //Coloca todas as cartas do baralho na pilha, porém, ela ainda não está embaralhada
        for (Carta carta : baralho) {
            pilhaCartas.push(carta);
        }
        pilhaCartas.embaralharPilha(); //Agora ela ta embaralhada
        return pilhaCartas;
    }

    public void fimDeJogo(int somaTotal, Pilha<Carta> mesa) throws MyException {
        System.out.println("=-=-=-=-=-=-=-= FIM DE JOGO =-=-=-=-=-=-=-=");

        System.out.println("Você encerrou com uma soma de " + somaTotal);

        if (somaTotal < 21) {
            Carta prox = mesa.peek();
            int valorHipotetico = somaTotal + prox.getValor();

            if (valorHipotetico > 21) {
                System.out.println("A proxima carta era um " + prox.getValor() + " de " + prox.getNaipe());
                System.out.println("Você teria perdido se tivesse continuado. Ainda bem que parou.");
            } else {
                if (somaTotal + prox.getValor() < 21) {
                    System.out.println("A proxima carta era um " + prox.getValor() + " de " + prox.getNaipe());
                    System.out.println("Você não teria perdido se tivesse continuado.");

                } else {
                    System.out.println("A proxima carta era um " + prox.getValor() + " de " + prox.getNaipe());
                    System.out.println("Você teria conseguido exatamente 21 se tivesse continuado.");
                }
            }
        }
        if (somaTotal == 21) {
            System.out.println("Conseguiu exatamente 21. Você venceu.");
        }
    }
}
