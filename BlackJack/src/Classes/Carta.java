package Classes;

public class Carta {

    private int valor;
    private String naipe;
    public Carta(int numero, String naipe) {
        setValor(numero);
        setNaipe(naipe);
    }

    public void setValor(int numero) {
        if (numero > 0 && numero < 14) {
            this.valor = numero;
        } else {
            throw new IllegalArgumentException("Valor inválido. Deve ser maior que 0 ou menor que 14");
        }
    }

    public void setNaipe(String naipe) {
        if (naipe.equals("Copas") || naipe.equals("Paus") || naipe.equals("Espadas") || naipe.equals("Ouros")) {
            this.naipe = naipe;
        } else {
            throw new  IllegalArgumentException("Naipe inválido. O naipe deve ser 'copas', 'paus', 'espadas' ou 'ouros'.");
        }
    }

    public String getNaipe() {
        return this.naipe;
    }

    public int getValor() {
        return this.valor;
    }
}
