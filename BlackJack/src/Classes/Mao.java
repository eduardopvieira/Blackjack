package Classes;

import java.util.HashMap;
import java.util.Map;
import ImpLL.MyLinkedList;

public class Mao {
    MyLinkedList<Carta> listaCartas = new MyLinkedList<>();

    private boolean isDealer;

    public Mao() {
        this.isDealer = false;
    }

    public Mao(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public int getSomaTotal() {
        int somaTotal = 0;

        for (Carta carta : listaCartas) {
            somaTotal += carta.getValor();
        }

        return somaTotal;
    }

    public int getQtdCartas() {
        return listaCartas.getSize();
    }

    public MyLinkedList<Carta> getListaCartas() {
        return listaCartas;
    }

    public void adicionarCarta(Carta carta){
        this.listaCartas.addLast(carta);
    }

    private String conversorNaipe(Carta carta){

        String Naipe = "";

        switch (carta.getNaipe()) {
            case "Copas":
                Naipe = "C"; //"\u2665";
                break;
            case "Paus":
                Naipe = "P"; //"\u2663";
                break;
            case "Espadas":
                Naipe = "E"; // "\u2660";
                break;
            case "Ouros":
                Naipe = "O";//\u2666";
                break;
            default:
                break;
        }

        return Naipe;
    }

    private String conversorNumero(Carta carta){

        String numero = "";
        Map<String, String> mapearCarta = new HashMap<>();
        mapearCarta.put("1", "A");
        mapearCarta.put("10", "10"); //deve ter alguma forma de otimizar essa linha aqui
        mapearCarta.put("11", "J");
        mapearCarta.put("12", "Q");
        mapearCarta.put("13", "K");
        
        String valor = String.valueOf(carta.getValor());

        if (mapearCarta.containsKey(valor)) {
            numero = String.valueOf(mapearCarta.get(valor));
        } else {
            numero = valor;
        } 
        return numero;
    }

    public void mostraMao(){

        if(isDealer == true){
            System.out.println("Mão atual do Dealer é: " + this.getSomaTotal());
        } else{
            System.out.println("Sua mão atual é:");
        }

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

    public String maoFinal(){

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

        return linha1 + "\n" + linha2 + "\n" + linha3 + "\n" + linha4 + "\n"; 
    }
    
}
