import Classes.BlackJack;
import Classes.EscritorArquivo;
import Classes.Pontuacao;
import Exception.MyException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws MyException, IOException {
        System.out.printf("\u2660");
        System.out.println("♣️♥️♠️♦️");
        BlackJack bj = new BlackJack();
        int qtdJogadores = bj.definirJogadores();
        Pontuacao[] pontuacoes = new Pontuacao[qtdJogadores];

        for (int i = 0; i < qtdJogadores; i++) {
            pontuacoes[i] =  bj.comecarPartida();
            System.out.printf("FIM DA RODADA DO JOGADOR %d \n", i+1);
        }

        EscritorArquivo esc = new EscritorArquivo();
        esc.escreverPontuacoes(pontuacoes);

    }
}

