package Busca;
import Classes.Pontuacao;

import java.util.ArrayList;

public class BuscaLinear {
    public BuscaLinear() {}

    public ArrayList<Pontuacao> buscarNome(ArrayList<Pontuacao> valores, String parametro) {
        ArrayList<Pontuacao> retorno = new ArrayList<>();
        String parametroMaiusculo = parametro.toUpperCase();
        for (Pontuacao pontuacao : valores) {
            if (pontuacao.getNome().equals(parametroMaiusculo)) {
                retorno.add(pontuacao);
            }
        }
        return retorno;
    }
}
