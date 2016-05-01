package se.liu.ida.danhe178.rical803.tddd78.schack.main;

/**
 * The main class for the chess program,
 * creates a Board and a ChessFrame.
 */

final class BoardTest
{
    private BoardTest() {}

    /**
     * Static method because it is used as an application entry point
     * and is only stored once because it should not be changed.
     */
    public static void main(String[] args) {
        Board testBoard = new Board();
        new ChessFrame(testBoard); // Result not relevant. Line is just used to start game.
    }
}
