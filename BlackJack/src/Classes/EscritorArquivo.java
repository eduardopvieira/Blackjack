package Classes;
import java.io.*;

public class EscritorArquivo {

    public static final String amarelo = "\u001B[33m";
    public static final String reset = "\u001B[0m"; 
    
    public EscritorArquivo(){}

    public void escreverPontuacoes(Pontuacao pontuacoes) throws IOException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("BlackJack/src/Saida/pontuacoes.txt"))) {
                Pontuacao pt = pontuacoes;
                String escrever = "=-=-=-=- Jogador " + 1 + ": -=-=-=-=\n" +
                                  "Pontos: " + pt.getPontos() + " | Cartas Puxadas: " + pt.getCartas() + "\n";
            writer.write(escrever);
            
            System.out.println(amarelo + "Pontuações escritas com sucesso." + reset);
        }
    }
}
