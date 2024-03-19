package Classes;
import ImpPilha.Pilha;
import java.util.HashSet;
import java.util.Scanner;

import Exception.MyException;

public class BlackJack {

    public static final String reset = "\u001B[0m"; 
    public static final String roxo = "\u001B[35m"; 
    public static final String vermelho = "\u001B[31m"; 


    public BlackJack() {}; //Construtor

    public Pontuacao comecarPartida(int jogadorAtual) throws MyException {

        Mao mao = new Mao();
        Mao maoDealer = new Mao(true);
        boolean continuar = true;       //vê se o player decidiu parar
        int decisao;                    //vê se o player decidiu puxar outra carta ou parar

        Pilha<Carta> mesa = prepararMesa();
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        
        maoDealer.adicionarCarta(mesa.pop());
        maoDealer.mostraMao();
        
        //PUXAR PRIMEIRA CARTA
        Carta primeiraCarta = mesa.pop();
        mao.adicionarCarta(primeiraCarta);
        mao.mostraMao();

        while (mao.getSomaTotal() < 21 && continuar) {
            System.out.println(roxo + "=-=-=-=-=-=-=-= BLACKJACK =-=-=-=-=-=-=-=" + reset);
            System.out.println("VALOR ATUAL: " + mao.getSomaTotal());
            System.out.println("Puxar carta?");
            System.out.println("1 - Puxar || 2 - Parar");
            decisao = sc.nextInt();

            System.out.print("\033[H\033[2J");
            limparConsole();

            switch(decisao) {
                case 1:
                    Carta c = mesa.pop();
                    mao.adicionarCarta(c);

                    maoDealer.mostraMao();
                    mao.mostraMao();
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    System.out.println("Numero inválido. Escolha 1 ou 2.");
            }
        }

        while (maoDealer.getSomaTotal() < 21 && maoDealer.getSomaTotal() < mao.getSomaTotal() && mao.getSomaTotal() <= 21) {
            limparConsole();

            maoDealer.adicionarCarta(mesa.pop());

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("Pressione Enter para continuar");
            // Limpando Buffer
            sc.nextLine();
    
            // Pausando o jogo e esperando o User apertar enter
            sc.nextLine();
    
            System.out.println("Continuando a execução...");
        }

        limparConsole();

        if(mao.getSomaTotal() > 21 || (mao.getSomaTotal() < maoDealer.getSomaTotal() && maoDealer.getSomaTotal() <= 21)){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("Voce perdeu");

        } else if (maoDealer.getSomaTotal() > 21 ){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("Voce Venceu");

        } else if (mao.getSomaTotal() == 21 && mao.getSomaTotal() > maoDealer.getSomaTotal()){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("BLACKJACK");

        } else if (mao.getSomaTotal() <= 21 && mao.getSomaTotal() == maoDealer.getSomaTotal()){

            maoDealer.mostraMao();
            mao.mostraMao();

            System.out.println("Empate");

        }

        System.out.println("Pressione Enter para continuar");
        // Limpando Buffer
        sc.nextLine();

        // Pausando o jogo e esperando o User apertar enter
        sc.nextLine();

        Historico historico = new Historico();
        historico.gravarHistorico(jogadorAtual, mao.maoFinal());

        fimDeJogo(mao.getSomaTotal(),mesa);

        System.out.println("Pressione Enter para continuar");
        // Limpando Buffer
        sc.nextLine();

        // Pausando o jogo e esperando o User apertar enter
        sc.nextLine();

        System.out.println("Continuando a execução...");

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

    public void fimDeJogo(int somaTotal, Pilha<Carta> mesa) throws MyException {
        System.out.println(vermelho + "=-=-=-=-=-=-=-= FIM DE JOGO =-=-=-=-=-=-=-=" + reset);

        System.out.println("Você encerrou com uma soma de " + somaTotal);

        if (somaTotal < 21) {
            Carta prox = mesa.peek();
            int valorHipotetico = somaTotal + prox.getValor();

            System.out.println("A proxima carta era um " + prox.getValor() + " de " + prox.getNaipe());

            if (valorHipotetico > 21) {
                System.out.println("Você teria perdido se tivesse continuado. Ainda bem que parou.");
            } else if (valorHipotetico < 21) {
                System.out.println("Você não teria perdido se tivesse continuado. Medroso.");
            } else {
                System.out.println("Você teria conseguido exatamente 21 se tivesse continuado. Deu azar.");
            }
        } else if (somaTotal == 21) {
            System.out.println("Conseguiu exatamente 21. Você venceu.");
        }

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
