import Classes.BlackJack;
import Classes.EscritorArquivo;
import Classes.Pontuacao;
import Exception.MyException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws MyException, IOException {
        //System.out.println("\u2660");
        //System.out.println("♣️♥️♠️♦️");
        BlackJack bj = new BlackJack();
        int qtdJogadores = bj.definirJogadores();
        Pontuacao[] pontuacoes = new Pontuacao[qtdJogadores];

        for (int i = 0; i < qtdJogadores; i++) {
            pontuacoes[i] =  bj.comecarPartida();
        }

        EscritorArquivo esc = new EscritorArquivo();
        esc.escreverPontuacoes(pontuacoes);

    }
}

