package tictoctoe;


public class Board {
    private Coin[][] board;
    private Player player1;
    private Player player2;
    private boolean isGameOver;
    private int size;
    private Player currentPlayer;

    public Board(Player player1, Player player2) {
        board = new Coin[3][3];
        size = 0;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        System.out.println("Player1 = Circle, Player = Cross");
    }

    public MoveResponse move(Player player, int x, int y) {
        if (isGameOver) throw new IllegalArgumentException("Game Over");
        if (x < 0 || x > 2 || y < 0 || y > 2)
            throw new IllegalArgumentException("Invalid cell");
        if (board[x][y] != null)
            throw new IllegalArgumentException("Invalid cell");
        if (!currentPlayer.equals(player))
            throw new IllegalArgumentException("Invalid player");
        if (player.equals(player1)) {
            Circle circle = new Circle();
            if (place(x, y, circle)) return new MoveResponse(Status.WON, player1);
        } else {
            Cross cross = new Cross();
            if (place(x, y, cross)) return new MoveResponse(Status.WON, player2);
        }
        size++;
        if (size == 9) {
            isGameOver = true;
            return new MoveResponse(Status.DRAW);
        }
        return new MoveResponse(Status.PENDING);
    }

    private boolean place(int x, int y, Coin coin) {
        board[x][y] = coin;
        if (isPlayerWon(coin)) {
            return true;
        }
        currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
        return false;
    }

    private boolean isPlayerWon(Coin coin) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != null && board[row][0].equals(coin) &&
                    board[row][1] != null && board[row][1].equals(coin) &&
                    board[row][2] != null && board[row][2].equals(coin)) {
                isGameOver = true;
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] != null && board[0][col].equals(coin) &&
                    board[1][col] != null && board[1][col].equals(coin) &&
                    board[2][col] != null && board[2][col].equals(coin)) {
                isGameOver = true;
                return true;
            }
        }

        if (board[0][0] != null && board[0][0].equals(coin) &&
                board[1][1] != null && board[1][1].equals(coin) &&
                board[2][2] != null && board[2][2].equals(coin)) {
            isGameOver = true;
            return true;
        }

        if (board[0][2] != null && board[0][2].equals(coin) &&
                board[1][1] != null && board[1][1].equals(coin) &&
                board[2][0] != null && board[2][0].equals(coin)) {
            isGameOver = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(board[i][j] != null ? board[i][j].toString() : "-");
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
