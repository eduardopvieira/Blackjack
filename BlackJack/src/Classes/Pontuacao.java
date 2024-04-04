package Classes;

public class Pontuacao {
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

    public Pontuacao(String nome, int pontos, int qtdCartas, double tempo) {
        this.nome = nome;
        this.pontos = pontos;
        this.qtdCartas = qtdCartas;
        this.tempo = tempo;
    }

    public int getPontos() {
        return this.pontos;
    }

    public int getCartas() {
        return this.qtdCartas;
    }

    public double getTempo() {
        return this.tempo / 1000;
    }
}
