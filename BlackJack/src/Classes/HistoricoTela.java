package Classes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Busca.BuscaLinear;

public class HistoricoTela extends JFrame {
    HistoricoTela(){}

    HistoricoTela(ArrayList<Pontuacao> jogadores, String nome){

        BuscaLinear busca = new BuscaLinear();

        setSize(900, 700);
        setLocation(400, 50);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel historicoJPanel = new JPanel();

        historicoJPanel.setLayout(new BoxLayout(historicoJPanel, BoxLayout.Y_AXIS));
        historicoJPanel.setBackground(new Color(244, 244, 244));
        historicoJPanel.setBackground(new Color(53, 101, 77));


        JLabel tituloJLabel = new JLabel("LISTA DE JOGADORES");
        tituloJLabel.setFont(new Font("Arial", Font.BOLD, 30));
        tituloJLabel.setForeground(Color.BLACK);
        historicoJPanel.add(tituloJLabel);

        // ArrayList<Pontuacao> listaJogadoresLidos = leitor.lerArquivo(caminho);
        ArrayList<Pontuacao> listaJogadores = busca.buscarNome(jogadores, nome);
        int tamanhoLista = listaJogadores.size();

        JLabel label[] = new JLabel[tamanhoLista];


        for (int i = 0; i < tamanhoLista; i++) {

            label[i] = new JLabel(listaJogadores.get(i).toString());
            label[i].setFont(new Font("Arial", Font.PLAIN, 20));
            label[i].setForeground(Color.WHITE);

            historicoJPanel.add(label[i]);
        }

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(new Color(244, 244, 244));
        centerPanel.setBackground(new Color(53, 101, 77));

        centerPanel.add(historicoJPanel);
        add(centerPanel);
    }
}
