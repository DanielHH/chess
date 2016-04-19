package main;

import java.awt.*;
import java.io.IOException;

public final class BoardTest
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Board testBoard = new Board();
        ChessFrame chessFrame = new ChessFrame(testBoard);
        /*
        System.out.println(testBoard.getPiece(3,3));
        System.out.println(testBoard.getPiece(3,0));
        testBoard.killPiece(3,0);
        System.out.println(testBoard.getPiece(3,0));
        */
    }
}
