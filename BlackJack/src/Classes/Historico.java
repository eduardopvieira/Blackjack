package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historico {
    Historico(){}

    void gravarHistorico(int numJogador, String cartasJogadas){
        LocalDateTime data = LocalDateTime.now();

        String strData = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("BlackJack/src/Saida/historico.txt",true))) {

            if(numJogador + 1 == 1){
                writer.write("=-=-=-=-=-=-=-= BLACKJACK =-=-=-=-=-=-=-=");
                writer.newLine();
                writer.write("Partida jogada em: " + strData);
                writer.newLine();
                writer.write("=-=-=-=- Jogador " + (numJogador + 1) + ": -=-=-=-=\n");
                writer.newLine();
                writer.write(cartasJogadas);
                writer.newLine();
            } else {
                writer.write("=-=-=-=- Jogador " + (numJogador + 1) + ": -=-=-=-=\n");
                writer.newLine();
                writer.write(cartasJogadas);
                writer.newLine();
            }
            
            System.out.println("Historico escrito com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
