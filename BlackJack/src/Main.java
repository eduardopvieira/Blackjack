import Classes.BlackJack;
import Exception.MyException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws MyException, IOException {

        BlackJack bj = new BlackJack();

        bj.comecarPartida();
    }
}
