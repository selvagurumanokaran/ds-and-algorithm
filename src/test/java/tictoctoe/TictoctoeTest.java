package tictoctoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TictoctoeTest {

    Player player1 = new Player(1, "you-know-who-circles");
    Player player2 = new Player(2, "you-know-who-crosses");

    @Test
    public void testSingleMove() {

        Board board = new Board(player1, player2);
        MoveResponse move = board.move(player1, 0, 0);
        assertEquals(move.getStatus(), Status.PENDING);
        assertFalse(move.getWinner().isPresent());
    }

    @Test
    public void testInvalidPlayer() {
        Board board = new Board(player1, player2);
        board.move(player1, 0, 0);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> board.move(player1, 2, 1));
        assertEquals("Invalid player", illegalArgumentException.getMessage());
    }

    @Test
    public void testWinner() {
        Board board = new Board(player1, player2);
        board.move(player1, 0, 0);
        System.out.println(board.toString());
        board.move(player2, 0, 1);
        System.out.println(board.toString());
        board.move(player1, 1, 1);
        System.out.println(board.toString());
        board.move(player2, 0, 2);
        System.out.println(board.toString());
        MoveResponse move = board.move(player1, 2, 2);
        assertEquals(move.getStatus(), Status.WON);
        assertTrue(move.getWinner().isPresent());
        assertEquals(move.getWinner().get(), player1);
        System.out.println(board.toString());
    }

    @Test
    public void testDraw() {
        Board board = new Board(player1, player2);
        board.move(player1, 0, 0);
        System.out.println(board.toString());
        board.move(player2, 0, 1);
        System.out.println(board.toString());
        board.move(player1, 1, 1);
        System.out.println(board.toString());
        board.move(player2, 2, 2);
        System.out.println(board.toString());
        board.move(player1, 0, 2);
        System.out.println(board.toString());
        board.move(player2, 1, 2);
        System.out.println(board.toString());
        board.move(player1, 1, 0);
        System.out.println(board.toString());
        board.move(player2, 2, 0);
        System.out.println(board.toString());
        MoveResponse move = board.move(player1, 2, 1);
        System.out.println(board.toString());
        assertEquals(Status.DRAW, move.getStatus());
        assertFalse(move.getWinner().isPresent());
    }

    @Test
    public void testGameOver() {
        Board board = new Board(player1, player2);
        board.move(player1, 0, 0);
        System.out.println(board.toString());
        board.move(player2, 0, 1);
        System.out.println(board.toString());
        board.move(player1, 1, 1);
        System.out.println(board.toString());
        board.move(player2, 0, 2);
        System.out.println(board.toString());
        board.move(player1, 2, 2);
        System.out.println(board.toString());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> board.move(player1, 2, 1));
        assertEquals("Game Over", illegalArgumentException.getMessage());
    }
}
