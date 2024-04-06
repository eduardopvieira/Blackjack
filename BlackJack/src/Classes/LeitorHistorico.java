package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Ordenacao.QuickSortPontuacao;


public class LeitorHistorico {

    public ArrayList<Pontuacao> lerArquivo(String nomeArquivo) {
        ArrayList<Pontuacao> jogadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Jogador:")) {
                    String[] partes = linha.split(";");

                    String[] nomes = partes[0].split(":");
                    String nome = nomes[1];

                    String[] pontosdalinha = partes[1].split(":");
                    int pontos = Integer.parseInt(pontosdalinha[1]);

                    String[] tempos = partes[3].split(":");
                    double tempo = Double.parseDouble(tempos[1]);

                    jogadores.add(new Pontuacao(nome, pontos, tempo));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        QuickSortPontuacao qsp = new QuickSortPontuacao();
        qsp.sort(jogadores);
        return jogadores;
    }
}
