package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorHistorico {

    public static ArrayList<Pontuacao> lerArquivo(String nomeArquivo) {
        ArrayList<Pontuacao> jogadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Jogador:")) {
                    String[] partes = linha.split("|");
                    String nome = partes[1];
                    int pontos = Integer.parseInt(partes[2]);
                    double tempo = Double.parseDouble(partes[3]);
                    jogadores.add(new Pontuacao(nome, pontos, tempo));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogadores;
    }
}
