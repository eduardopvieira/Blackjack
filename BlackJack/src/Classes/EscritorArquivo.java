package Classes;
import java.io.*;

public class EscritorArquivo {

    public EscritorArquivo(){}

    public void escreverPontuacoes(Pontuacao[] pontuacoes) throws IOException {
        //TODO ajeitar essa poha
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("BlackJack/src/Saida/pontuacoes.txt"))) {
            int qtdJogadores = pontuacoes.length;
            for (int i = 0; i < qtdJogadores; i++) {
                Pontuacao pt = pontuacoes[i];
                String escrever = "=-=-=-=- Jogador " + (i + 1) + ": -=-=-=-=\n" +
                                  "Pontos: " + pt.getPontos() + " | Cartas Puxadas: " + pt.getCartas() + "\n";
                writer.write(escrever);
            }
            System.out.println("Pontuações escritas com sucesso.");
        }
    }
}
