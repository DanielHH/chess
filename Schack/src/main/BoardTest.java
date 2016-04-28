package main;

/**
 * The main class for the chess program,
 * creates a Board and a ChessFrame.
 */

public final class BoardTest
{
    private BoardTest() {}

    public static void main(String[] args) {
        Board testBoard = new Board();
        new ChessFrame(testBoard); // Result not relevant. Field is just used to start game.
    }
}
