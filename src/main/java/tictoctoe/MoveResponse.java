package tictoctoe;

import java.util.Optional;

public class MoveResponse {
    private Status status;
    private Player winner;

    public MoveResponse(Status status, Player winner) {
        this.status = status;
        this.winner = winner;
    }

    public MoveResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Optional<Player> getWinner() {
        return Optional.ofNullable(winner);
    }
}
