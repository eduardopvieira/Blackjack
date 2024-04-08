package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Busca.BuscaLinear;
import Ordenacao.QuickSort;


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

        BuscaLinear bl = new BuscaLinear();

        ArrayList<Pontuacao> p = bl.buscarNome(jogadores, "eduardo");

        System.out.println("A chamada do algoritmo de busca ta na classe LeitorHistorico");
        if (p != null) {
            for (Pontuacao teste : p) {
                System.out.println("Pontuacao: " + teste.getNome() + "| Pontos: " + teste.getPontos() + "| Tempo: " + teste.getTempo()/1000);
            }
        } else {
            System.out.println("Não há ninguem com o nome buscado.");
        }

        QuickSort qs = new QuickSort();
        qs.sort(jogadores);
        return jogadores;


    }
}
