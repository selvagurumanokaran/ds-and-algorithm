package tictoctoe;

public class Player {
    private int id;
    private String name;

    public Player(int id, String name) {
        this(id);
        this.name = name;
    }

    public Player(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id == player.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
