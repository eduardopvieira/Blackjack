package Classes;

import ImpPilha.Pilha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Exception.MyException;
import ImpLL.MyLinkedList;

public class BlackJack {

    public static final String reset = "\u001B[0m";
    public static final String roxo = "\u001B[35m";
    public static final String vermelho = "\u001B[31m";
    public static final String verde = "\u001B[32m";
    public static final String amarelo = "\u001B[33m";

    double tempoTotal;

    String nomeJogador;

    int boardWidth = 900;
    int boardHeight = 700;

    int cardWidth = 110;
    int cardHeight = 154;

    String somatorioMao = "Valor total na mão: ";

    Pilha<Carta> mesa;

    Mao mao = new Mao();
    Mao maoDealer = new Mao(true);

    public JFrame frame = new JFrame("Blackjack"){};

    public JFrame historico = new JFrame("Historico");

    public String popout(){
        String nomeJogador = JOptionPane.showInputDialog("Digite seu nome seu maldito:");

        return nomeJogador;
    };


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
    
                        hiButton.setEnabled(false);
                        stayButton.setEnabled(false);
    
                        String message = "";
    
                        if (mao.getSomaTotal() > 21
                                || (mao.getSomaTotal() < maoDealer.getSomaTotal() && maoDealer.getSomaTotal() <= 21)) {
    
                            message = "Você perdeu! Tempo de jogo: " + (tempoTotal/1000);
    
                        } else if (maoDealer.getSomaTotal() > 21) {
    
                            message = "Você venceu!";
    
                        } else if (mao.getSomaTotal() == 21 && mao.getSomaTotal() > maoDealer.getSomaTotal()) {
    
                            message = "BLACKJACK";
    
                        } else if (mao.getSomaTotal() <= 21 && mao.getSomaTotal() == maoDealer.getSomaTotal()) {
    
                            message = "Empate";
    
                        }
    
                        g.setFont(new Font("Arial", Font.PLAIN, 30));
                        g.setColor(Color.white);
                        g.drawString(message, 200, 600);

                        frame.dispose();


                        historico.setVisible(true);
                        historico.setSize(boardWidth, boardHeight);
                        historico.setLocation(400, 50);
                        historico.setResizable(false);
                        historico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        historicoJPanel.setLayout(new BorderLayout());
                        historicoJPanel.setBackground(new Color(244, 244, 244));
                        historicoJPanel.setBackground(new Color(53, 101, 77));
                        historico.add(historicoJPanel);

                        JLabel label = new JLabel(nomeJogador);
                        label.setFont(new Font("Arial", Font.PLAIN, 30));
                        label.setForeground(Color.WHITE);

                        historicoJPanel.add(label);
    
                    }
    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
        }
    };
    JPanel historicoJPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JTextField textField = new JTextField();
    JButton hiButton = new JButton("Hit");
    JButton stayButton = new JButton("Parar");

    public BlackJack() {
    }; // Construtor

    public Pontuacao comecarPartida() throws MyException {
        long startTime = System.currentTimeMillis();
        this.mesa = prepararMesa();

        maoDealer.adicionarCarta(mesa.pop());

        // PUXAR PRIMEIRA CARTA
        mao.adicionarCarta(mesa.pop());

        nomeJogador = popout();  
        
        if(nomeJogador == null){
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

        hiButton.setFocusable(false);
        buttonPanel.add(hiButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hiButton.addActionListener(new ActionListener() { //BOTAO DE HIT
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
                hiButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (maoDealer.getSomaTotal() < 21 && maoDealer.getSomaTotal() < mao.getSomaTotal()
                        && mao.getSomaTotal() <= 21) {

                    try {
                        maoDealer.adicionarCarta(mesa.pop());
                    } catch (MyException e1) {
                        e1.printStackTrace();
                    }
                }

                gamePanel.repaint();
            }
        });

        long endTime = System.currentTimeMillis();
        tempoTotal = endTime - startTime;

        mao.maoFinal();
        Historico historico = new Historico();
        Pontuacao pt = new Pontuacao(nomeJogador, mao.getSomaTotal(), mao.getQtdCartas(), tempoTotal);
        historico.gravarHistorico(nomeJogador, mao.maoFinal(), pt);
        return pt;
    }

    public void jogandoPartida() throws MyException{
        System.out.println("teste");
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
