package Classes;

public class Pontuacao implements Comparable<Pontuacao> {
    String nome;
    private int pontos;
    private int qtdCartas;
    private double tempo;

    public Pontuacao() {
    }

    public Pontuacao(String nome, int pontos, double tempo) {
        this.nome = nome;
        this.pontos = pontos;
        this.tempo = tempo;
    }

    public Pontuacao(String nome, int pontos, int qtdCartas, double tempo, String resultadoDaPartida) {
        this.nome = nome.toUpperCase();
        this.pontos = pontos;
        this.qtdCartas = qtdCartas;
        this.tempo = tempo; 

        int pontosDeTempo = (int)(50/(tempo/1000));
        int pontosDePartida = 0;

        if (resultadoDaPartida == "DERROTA") {
            pontosDePartida = 0;
            pontosDeTempo = 0;
        } else if (resultadoDaPartida == "VITORIA") {
            pontosDePartida = 30;
        } else if (resultadoDaPartida == "BLACKJACK") {
            pontosDePartida = 60;
        } else if (resultadoDaPartida == "EMPATE") {
            pontosDePartida = 15;
        } else {
            pontosDePartida = 0;
            pontosDeTempo = 0;
        }

        this.pontos = pontosDeTempo + pontosDePartida;
    }

    public int getPontos() {
        return pontos;
    }

    public int getQtdCartas() {
        return qtdCartas;
    }

    public double getTempo() {
        return Math.round(this.tempo * 1000.0) / 1000.0; // fiz isso pra retornar 3 casas decimais
    }

    @Override
    public String toString() {
        return "| Jogador:" + nome + " | Pontuação:" + pontos + "| Tempo de jogo:" + (tempo/1000) + " |\n";
    }

    public String getNome() {
        return nome;
    }

    public int getCartas() {
        return this.qtdCartas;
    }

    @Override
    public int compareTo(Pontuacao outraPontuacao) {
        return Integer.compare(this.pontos, outraPontuacao.getPontos());
    }

}

