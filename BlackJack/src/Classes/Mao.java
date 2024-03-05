package Classes;

import java.util.LinkedList;

public class Mao {
    LinkedList<Carta> listaCartas = new LinkedList<>();

    public Mao() {}

    public int getSomaTotal() {
        int somaTotal = 0;

        for (Carta carta : listaCartas) {
            somaTotal += carta.getValor();
        }

        return somaTotal;
    }

    public int getQtdCartas() {
        return listaCartas.size();
    }

    public LinkedList<Carta> getListaCartas() {
        return listaCartas;
    }

    public void adicionarCarta(Carta carta){
        this.listaCartas.add(carta);
    }

    private String conversorNaipe(Carta carta){

        String Naipe = "";

        switch (carta.getNaipe()) {
            case "Copas":
                Naipe = "\u2665";
                System.out.println(Naipe);
                break;
            case "Paus":
                Naipe = "\u2665";
                System.out.println(Naipe);
                break;
            case "Espadas":
                Naipe = "\u2665";
                System.out.println(Naipe);
                break;
            case "Ouros":
                Naipe = "\u2665";
                System.out.println(Naipe);
                break;
            default:
                break;
        }

        return Naipe;
    }

    private String conversorNumero(Carta carta){

        String numero = "";

        switch (carta.getValor()) {
            case 1:
                numero = "A";
                break;
            case 2:
                numero = "2";
                break;
            case 3:
                numero = "3";
                break;
            case 4:
                numero = "4";
                break;
            case 5:
                numero = "5";
                break;
            case 6:
                numero = "6";
                break;
            case 7:
                numero = "7";
                break;
            case 8:
                numero = "8";
                break;
            case 9:
                numero = "9";
                break;
            case 10:
                numero = "10";
                break;
            case 11:
                numero = "J";
                break;
            case 12:
                numero = "Q";
                break;
            case 13:
                numero = "K";
                break;
            default:
                break;
        }

        return numero;
    }

    public void mostraMao(){
        System.out.println("Sua mão atual é:");

        String maoFinal = "";

        String linha1 = "";
        String linha2 = "";
        String linha3 = "";
        String linha4 = "";

        for (Carta carta : listaCartas) {

            String naipe = conversorNaipe(carta);
            String numero = conversorNumero(carta);

            linha1 += " _____  ";
            if(numero == "10"){
                linha2 += "|"+ numero +"   | ";
            } else {
                linha2 += "|"+ numero +"    | ";
            }
            linha3 += "|  "+ naipe +"  | ";
            linha4 += "|_____| ";
        }

        maoFinal = linha1 + "\n" + linha2 + "\n" + linha3 + "\n" + linha4 + "\n"; 

        System.out.println(maoFinal);
    }


    
}
