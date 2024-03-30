package Classes;

public class Pontuacao {
    private int pontos;
    private int qtdCartas;

    public Pontuacao() { }

    public Pontuacao(int pontos, int qtdCartas) {
        this.pontos = pontos;
        this.qtdCartas = qtdCartas;
    }

    public int getPontos() {
        return this.pontos;
    }

    public int getCartas() {
        return this.qtdCartas;
    }
}
