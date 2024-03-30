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
import javax.swing.JPanel;

import Exception.MyException;
import ImpLL.MyLinkedList;

public class BlackJack {

    public static final String reset = "\u001B[0m"; 
    public static final String roxo = "\u001B[35m"; 
    public static final String vermelho = "\u001B[31m"; 
    public static final String verde = "\u001B[32m";
    public static final String amarelo = "\u001B[33m";

    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110;
    int cardHeight = 154;

    Pilha<Carta> mesa;

    Mao mao = new Mao();
    Mao maoDealer = new Mao(true);

    JFrame frame = new JFrame("Blackjack");
    JPanel gamePanel = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            try {
                // Escrevendo uma carta
                Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                MyLinkedList<Carta> cartasDealer = maoDealer.getListaCartas();

                for (int i = 0; i < cartasDealer.getSize(); i++) {
                    Carta carta = cartasDealer.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource("./cards/" + carta.getValor() + "-" + carta.conversorNaipe() + ".png")).getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5)*i, 20 , cardWidth, cardHeight, null);
                }

                MyLinkedList<Carta> cartasPlayer = mao.getListaCartas();

                for (int i = 0; i < cartasPlayer.getSize(); i++) {
                    Carta carta = cartasPlayer.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource("./cards/" + carta.getValor() + "-" + carta.conversorNaipe() + ".png")).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth + 5)*i, 320 , cardWidth, cardHeight, null);
                }

                if (!stayButton.isEnabled() || mao.getSomaTotal() >= 21) {

                    hiButton.setEnabled(false);
                    stayButton.setEnabled(false);

                    String message = "";

                    if(mao.getSomaTotal() > 21 || (mao.getSomaTotal() < maoDealer.getSomaTotal() && maoDealer.getSomaTotal() <= 21)){

                        message = "Voce perdeu";
            
                        System.out.println("Voce perdeu");
            
                    } else if (maoDealer.getSomaTotal() > 21 ){
            
                        message = "Voce Venceu";
            
                        System.out.println("Voce Venceu");
            
                    } else if (mao.getSomaTotal() == 21 && mao.getSomaTotal() > maoDealer.getSomaTotal()){
            
                        message = "BLACKJACK";
            
                        System.out.println("BLACKJACK");
            
                    } else if (mao.getSomaTotal() <= 21 && mao.getSomaTotal() == maoDealer.getSomaTotal()){
            
                        message = "Empate";

                        System.out.println("Empate");
            
                    }

                    g.setFont(new Font("Arial", Font.PLAIN, 30));
                    g.setColor(Color.white);
                    g.drawString(message, 220, 250);

                }   

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hiButton = new JButton("Hit");
    JButton stayButton = new JButton("Parar");


    public BlackJack() {}; //Construtor

    public Pontuacao comecarPartida() throws MyException {


        this.mesa = prepararMesa();
        System.out.print("\033[H\033[2J");
        
        maoDealer.adicionarCarta(mesa.pop());
        maoDealer.mostraMao();
        
        //PUXAR PRIMEIRA CARTA
        mao.adicionarCarta(mesa.pop());
        mao.mostraMao();

        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocation(400,50);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53,101,77));
        frame.add(gamePanel);

        hiButton.setFocusable(false);
        buttonPanel.add(hiButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
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

         stayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hiButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (maoDealer.getSomaTotal() < 21 && maoDealer.getSomaTotal() < mao.getSomaTotal() && mao.getSomaTotal() <= 21) {
        
                    try {
                        maoDealer.adicionarCarta(mesa.pop());
                    } catch (MyException e1) {
                        e1.printStackTrace();
                    }
                }


                gamePanel.repaint();
            }
        });

        gamePanel.repaint();


        if(mao.getSomaTotal() > 21 || (mao.getSomaTotal() < maoDealer.getSomaTotal() && maoDealer.getSomaTotal() <= 21)){

            maoDealer.mostraMao();
            mao.mostraMao();

            //System.out.println("Voce perdeu");

        } else if (maoDealer.getSomaTotal() > 21 ){

            maoDealer.mostraMao();
            mao.mostraMao();

            //System.out.println("Voce Venceu");

        } else if (mao.getSomaTotal() == 21 && mao.getSomaTotal() > maoDealer.getSomaTotal()){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("BLACKJACK");

        } else if (mao.getSomaTotal() <= 21 && mao.getSomaTotal() == maoDealer.getSomaTotal()){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("Empate");

        }

        //System.out.println("Pressione Enter para continuar");
        // Limpando Buffer
        //sc.nextLine();

        // Pausando o jogo e esperando o User apertar enter
        //sc.nextLine();

        fimDeJogo(mao.getSomaTotal(),mesa, maoDealer.getSomaTotal());

        Historico historico = new Historico();
        historico.gravarHistorico(1, mao.maoFinal());

        Pontuacao pt = new Pontuacao(mao.getSomaTotal(), mao.getQtdCartas());
        return pt;
    }

    public Pilha<Carta> prepararMesa() throws MyException {
        Baralho todasAsCartas = new Baralho(); //Inicializa todas as cartas
        HashSet<Carta> baralho = todasAsCartas.getBaralho(); //Cria um HashSet de todas as cartas
        Pilha<Carta> pilhaCartas = new Pilha<>(baralho.size()); //Cria a pilha do tamanho do baralho

        //Coloca todas as cartas do baralho na pilha, porém, ela ainda não está embaralhada
        for (Carta carta : baralho) {
            pilhaCartas.push(carta);
        }
        pilhaCartas.embaralharPilha(); //Agora ela ta embaralhada
        return pilhaCartas;
    }

    public void fimDeJogo(int somaPlayer, Pilha<Carta> mesa, int somaDealer) throws MyException {
        System.out.println(vermelho + "=-=-=-=-=-=-=-= FIM DE JOGO =-=-=-=-=-=-=-=" + reset);
    
        System.out.println("Você encerrou com um valor total de " + verde + somaPlayer + reset);
        System.out.println("O dealer encerrou com um valor total de " + vermelho + somaDealer + reset);
        System.out.println("");
    
        if (somaPlayer <= 21) {
            Carta prox = mesa.peek();
            int valorHipotetico = somaPlayer + prox.getValor();
    
            System.out.println("A próxima carta era um " + prox.getValor() + " de " + prox.getNaipe());
    
            if (valorHipotetico == 21) {
                System.out.println("Você conseguiu exatamente 21. Você venceu!");
                return;
            } else if (valorHipotetico < 21) {
                System.out.println("Você não teria perdido se tivesse continuado. Medroso.");
            } else {
                System.out.println("Você teria perdido se tivesse continuado. Ainda bem que parou.");
            }
        }
        System.out.println("");
    
        if (somaDealer > 21) {
            System.out.println("O dealer estourou. " + verde + "Você ganhou!" + reset);
        } else if (somaDealer == 21) {
            System.out.println("O dealer conseguiu exatamente 21." + vermelho + " O dealer ganhou!" + reset);
        } else if (somaPlayer <= 21) {
            if (somaDealer > somaPlayer) {
                System.out.println("O dealer está mais próximo de 21 que você." + vermelho + " O dealer ganhou!" + reset);
            } else if (somaDealer == somaPlayer) {
                System.out.println("Ambos têm o mesmo valor." + amarelo + " Empate!" + reset);
            } else {
                System.out.println("Você está mais próximo de 21 que o dealer." + verde +" Você ganhou!" + reset);
            }
        } else {
            System.out.println("Você ultrapassou 21." + vermelho + " Você perdeu!" + reset);
        }
        System.out.println("");
    }

    public static void limparConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Limpa o console no Windows
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "cls");
                builder.inheritIO().start().waitFor();
            } else {
                // Limpa o console em outros sistemas operacionais
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Tratar exceções
        }
    }

}
