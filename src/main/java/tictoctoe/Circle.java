package tictoctoe;

public class Circle extends Coin {
    public Circle() {
        this.id = 1;
    }

    @Override
    public String toString() {
        return "O";
    }
}
