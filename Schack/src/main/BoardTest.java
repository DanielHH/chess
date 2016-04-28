package main;

/**
 * The main class for the chess program,
 * creates a Board and a ChessFrame.
 * The gameBoard updates each time the timer "ticks".
 */
public final class BoardTest
{
    private BoardTest() {}

    public static void main(String[] args) {
        Board testBoard = new Board();
        new ChessFrame(testBoard);
        /*
        System.out.println(testBoard.getPiece(3,3));
        System.out.println(testBoard.getPiece(3,0));
        testBoard.killPiece(3,0);
        System.out.println(testBoard.getPiece(3,0));
        */
    }
}
