package Classes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
                break;
            case "Paus":
                Naipe = "\u2663";
                break;
            case "Espadas":
                Naipe = "\u2660";
                break;
            case "Ouros":
                Naipe = "\u2666";
                break;
            default:
                break;
        }

        return Naipe;
    }

    private String conversorNumero(Carta carta){

        String numero = "";
        Map<String, Character> mapearCarta = new HashMap<>();
        mapearCarta.put("1", 'A');
        mapearCarta.put("11", 'J');
        mapearCarta.put("12", 'Q');
        mapearCarta.put("13", 'K');
        
        String valor = String.valueOf(carta.getValor());

        if (mapearCarta.containsKey(valor)) {
            numero = String.valueOf(mapearCarta.get(valor));
        } else {
            numero = valor;
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
