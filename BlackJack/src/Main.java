import Classes.BlackJack;
import Classes.EscritorArquivo;
import Classes.Pontuacao;
import Exception.MyException;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws MyException, IOException {
        //System.out.println("\u2660");
        //System.out.println("♣️♥️♠️♦️");
        BlackJack bj = new BlackJack();

        System.out.print("\033[H\033[2J");
        System.out.println("Quantos jogadores irão jogar? ");
        Scanner scMain = new Scanner(System.in);

        int qtdJogadores = scMain.nextInt();
 
        Pontuacao[] pontuacoes = new Pontuacao[qtdJogadores];

        for (int i = 0; i < qtdJogadores; i++) {
            pontuacoes[i] =  bj.comecarPartida(i);
        }

        EscritorArquivo esc = new EscritorArquivo();
        esc.escreverPontuacoes(pontuacoes);

        scMain.close();
    }
}

