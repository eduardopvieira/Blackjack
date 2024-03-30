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

        // System.out.print("\033[H\033[2J");
 
        Pontuacao pontuacoes;

        pontuacoes =  bj.comecarPartida();


        EscritorArquivo esc = new EscritorArquivo();
        esc.escreverPontuacoes(pontuacoes);

    }
}

