package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    Historico() {
    }

    void gravarHistorico(String nomeJogador, String cartasJogadas, Pontuacao pt) {
        LocalDateTime data = LocalDateTime.now();

        String strData = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./Saida/historico.txt", true))) {

            writer.write("=-=-=-=-=-=-=-= BLACKJACK =-=-=-=-=-=-=-=");
            writer.newLine();

            String escrever = "Jogador: " + nomeJogador + "\n" +
                    "Pontos: " + pt.getPontos() + " | Cartas Puxadas: " + pt.getCartas() + " | Tempo: " + pt.getTempo()
                    + "s\n";
            writer.write(escrever);
            writer.newLine();
            writer.write("Partida jogada em: " + strData);
            writer.newLine();
            writer.write(cartasJogadas);
            writer.newLine();
            System.out.println("Historico escrito com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
