package tictoctoe;

public class Coin {
    protected int id;

    public Coin() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return id == coin.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
