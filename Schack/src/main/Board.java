package main;

/*
Det finns en array som motsvarar ett schackbräde.
Schackbrädet kan ritas ut grafiskt.
Schackbrädet är rutat i två färger.
*/


import java.io.IOException;

public class Board
{
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Piece[][] board;


    public Board() throws IOException {
	this.board = new Piece[WIDTH][HEIGHT];

	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] =  new Pawn(column, 1, Team.BLACK, this, PieceType.PAWN, "fantasy/png-shad/bp.png");
	}

	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Team.WHITE, this, PieceType.PAWN, "fantasy/png-shad/wp.png");
	}
	board[0][0] = new Rook(0, 0, Team.BLACK, this, PieceType.ROOK, "fantasy/png-shad/br.png");
	board[1][0] = new Knight(1, 0, Team.BLACK, this, PieceType.KNIGHT, "fantasy/png-shad/bn.png");
	board[2][0] = new Bishop(2, 0, Team.BLACK, this, PieceType.BISHOP, "fantasy/png-shad/bb.png");
	board[3][0] = new King(3, 0, Team.BLACK, this, PieceType.KING,  "fantasy/png-shad/bk.png");
	board[4][0] = new Queen(4, 0, Team.BLACK, this, PieceType.QUEEN,  "fantasy/png-shad/bq.png");
	board[5][0] = new Bishop(5, 0, Team.BLACK, this, PieceType.BISHOP,  "fantasy/png-shad/bb.png");
	board[6][0] = new Knight(6, 0, Team.BLACK, this, PieceType.KNIGHT,  "fantasy/png-shad/bn.png");
	board[7][0] = new Rook(7, 0, Team.BLACK, this, PieceType.ROOK,  "fantasy/png-shad/br.png");

	board[0][7] = new Rook(0, 7, Team.WHITE, this, PieceType.ROOK, "fantasy/png-shad/wr.png");
	board[1][7] = new Knight(1, 7, Team.WHITE, this, PieceType.KNIGHT, "fantasy/png-shad/wn.png");
	board[2][7] = new Bishop(2, 7, Team.WHITE, this, PieceType.BISHOP, "fantasy/png-shad/wb.png");
	board[3][7] = new King(3, 7, Team.WHITE, this, PieceType.KING, "fantasy/png-shad/wk.png");
	board[4][7] = new Queen(4, 7, Team.WHITE, this, PieceType.QUEEN, "fantasy/png-shad/wq.png");
	board[5][7] = new Bishop(5, 7, Team.WHITE, this, PieceType.BISHOP, "fantasy/png-shad/wb.png");
	board[6][7] = new Knight(6, 7, Team.WHITE, this, PieceType.KNIGHT, "fantasy/png-shad/wn.png");
	board[7][7] = new Rook(7, 7, Team.WHITE, this, PieceType.ROOK, "fantasy/png-shad/wr.png");


    }

    public Piece getPiece(int column, int row) {
	return board[column][row];
    }

    public void killPiece(int column, int row) {
	board[column][row] = null;
    }

    public void actuallyMovesPiece(int oldColumn, int oldRow, int newColumn, int newRow) {
	board[newColumn][newRow] = board[oldColumn][oldRow];
	board[oldColumn][oldRow] = null;
    }

    public static int getWIDTH() {
	return WIDTH;
    }

    public static int getHEIGHT() {
	return HEIGHT;
    }
}
