package Classes;

import ImpPilha.Pilha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Exception.MyException;
import ImpLL.MyLinkedList;

public class BlackJack {

    double tempoTotal;
    String nomeJogador;
    long startTime;

    int boardWidth = 900;
    int boardHeight = 700;

    int cardWidth = 110;
    int cardHeight = 154;

    Pilha<Carta> mesa;

    Mao mao = new Mao();
    Mao maoDealer = new Mao();

    public JFrame frame = new JFrame("Blackjack") {
    };

    public JFrame historico = new JFrame("Historico");

    public String popout() {
        String nomeJogador = JOptionPane.showInputDialog("Digite seu nome seu maldito:");

        startTime = System.currentTimeMillis();


        return nomeJogador;
    };

    public LeitorHistorico leitor = new LeitorHistorico();


    JPanel gamePanel = new JPanel() {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                // Escrevendo uma carta
                Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                MyLinkedList<Carta> cartasDealer = maoDealer.getListaCartas();

                for (int i = 0; i < cartasDealer.getSize(); i++) {
                    Carta carta = cartasDealer.get(i);
                    Image cardImg = new ImageIcon(getClass()
                            .getResource("./cards/" + carta.getValor() + "-" + carta.conversorNaipe() + ".png"))
                            .getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                }

                MyLinkedList<Carta> cartasPlayer = mao.getListaCartas();

                for (int i = 0; i < cartasPlayer.getSize(); i++) {
                    Carta carta = cartasPlayer.get(i);
                    Image cardImg = new ImageIcon(getClass()
                            .getResource("./cards/" + carta.getValor() + "-" + carta.conversorNaipe() + ".png"))
                            .getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
                }

                Integer maoTotal = mao.getSomaTotal();

                String somatorioMao = "Valor total na mão:" + maoTotal.toString();

                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString(somatorioMao, 10, 520);

                Integer maoTotalDealer = maoDealer.getSomaTotal();

                String somatorioMaoDealer = "Valor total na mão:" + maoTotalDealer.toString();

                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.setColor(Color.white);
                g.drawString(somatorioMaoDealer, 0, 210);

                if (!stayButton.isEnabled() || mao.getSomaTotal() >= 21) {

                    hitButton.setEnabled(false);
                    stayButton.setEnabled(false);

                    String message = "";

                    long endTime = System.currentTimeMillis();
                    tempoTotal = endTime - startTime;

                    String resultadoDaPartida = "";

                    if (mao.getSomaTotal() > 21 || (mao.getSomaTotal() < maoDealer.getSomaTotal() && maoDealer.getSomaTotal() <= 21)) {

                        message = "Você perdeu! Tempo de jogo: " + (tempoTotal / 1000);
                        resultadoDaPartida = "DERROTA";

                    } else if (maoDealer.getSomaTotal() > 21) {

                        message = "Você venceu!";
                        resultadoDaPartida = "VITORIA";


                    } else if (mao.getSomaTotal() == 21 && mao.getSomaTotal() > maoDealer.getSomaTotal()) {

                        message = "BLACKJACK";
                        resultadoDaPartida = "BLACKJACK";


                    } else if (mao.getSomaTotal() <= 21 && mao.getSomaTotal() == maoDealer.getSomaTotal()) {
                        message = "Empate";
                        resultadoDaPartida = "EMPATE";

                    }

                    g.setFont(new Font("Arial", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString(message, 200, 600);

                    Historico gravarHistorico = new Historico();
                    Pontuacao pt = new Pontuacao(nomeJogador, mao.getSomaTotal(), mao.getQtdCartas(), tempoTotal, resultadoDaPartida);
                    gravarHistorico.gravarHistorico(mao.maoFinal(), pt);

                    rankingButton.setVisible(true);

                    historico.setSize(boardWidth, boardHeight);
                    historico.setLocation(400, 50);
                    historico.setResizable(false);
                    historico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    Border borda = BorderFactory.createLineBorder(Color.BLACK, 2); // Cor preta e 2 pixels de largura

                    historicoJPanel.setLayout(new BoxLayout(historicoJPanel, BoxLayout.Y_AXIS));
                    historicoJPanel.setBackground(new Color(244, 244, 244));
                    historicoJPanel.setBackground(new Color(53, 101, 77));
                    historicoJPanel.setBorder(borda);


                    JLabel tituloJLabel = new JLabel("LISTA DE JOGADORES");
                    tituloJLabel.setFont(new Font("Arial", Font.BOLD, 30));
                    tituloJLabel.setForeground(Color.BLACK);
                    historicoJPanel.add(tituloJLabel);

                    String caminho = "./Saida/historico.txt";

                    ArrayList<Pontuacao> listaJogadores = leitor.lerArquivo(caminho);
                    int tamanhoLista = listaJogadores.size();

                    JLabel label[] = new JLabel[tamanhoLista];


                    for (int i = 0; i < tamanhoLista; i++) {

                        label[i] = new JLabel(listaJogadores.get(i).toString());
                        label[i].setFont(new Font("Arial", Font.PLAIN, 20));
                        label[i].setForeground(Color.WHITE);
                        System.out.println(listaJogadores.get(i).toString());

                        historicoJPanel.add(label[i]);
                    }

                    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    centerPanel.setBackground(new Color(244, 244, 244));
                    centerPanel.setBackground(new Color(53, 101, 77));

                    centerPanel.add(historicoJPanel);
                    historico.add(centerPanel);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    JPanel historicoJPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JTextField textField = new JTextField();
    JButton hitButton = new JButton("Puxar");
    JButton stayButton = new JButton("Parar");
    JButton rankingButton = new JButton("Ranking");


    public BlackJack() {
    }

    public void comecarPartida() throws MyException {
        this.mesa = prepararMesa();

        maoDealer.adicionarCarta(mesa.pop());

        // PUXAR PRIMEIRA CARTA
        mao.adicionarCarta(mesa.pop());

        nomeJogador = popout();

        if (nomeJogador == null) {
            System.exit(0);
        }

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocation(400, 50);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53, 101, 77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        rankingButton.setFocusable(false);
        rankingButton.setVisible(false);
        buttonPanel.add(rankingButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() { //BOTAO DE HIT
            public void actionPerformed(ActionEvent e) {
                try {
                    Carta c;
                    c = mesa.pop();
                    mao.adicionarCarta(c);
                } catch (MyException e1) {
                    e1.printStackTrace();
                }
                gamePanel.repaint();
            }
        });

        stayButton.addActionListener(new ActionListener() { //BOTAO DE QND PARA DE PUXAR
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);
                rankingButton.setEnabled(true);

                while (maoDealer.getSomaTotal() < 21 && maoDealer.getSomaTotal() < mao.getSomaTotal()
                        && mao.getSomaTotal() <= 21) {
                    try {
                        maoDealer.adicionarCarta(mesa.pop());
                    } catch (MyException e1) {
                        e1.printStackTrace();
                    }
                }

                rankingButton.setVisible(true);
                gamePanel.repaint();

            }
        });

        rankingButton.addActionListener(new ActionListener(){ //BOTAO DE IR PRO RANKING
            public void actionPerformed(ActionEvent e) {

                frame.dispose(); // Fecha a tela de jogo
                historico.setVisible(true); // Exibe a tela de histórico
            }
        });

    }

    public Pilha<Carta> prepararMesa() throws MyException {
        Baralho todasAsCartas = new Baralho(); // Inicializa todas as cartas
        HashSet<Carta> baralho = todasAsCartas.getBaralho(); // Cria um HashSet de todas as cartas
        Pilha<Carta> pilhaCartas = new Pilha<>(baralho.size()); // Cria a pilha do tamanho do baralho

        // Coloca todas as cartas do baralho na pilha, porém, ela ainda não está
        // embaralhada
        for (Carta carta : baralho) {
            pilhaCartas.push(carta);
        }
        pilhaCartas.embaralharPilha(); // Agora ela ta embaralhada
        return pilhaCartas;
    }
}
