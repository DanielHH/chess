package main;

/*
Det finns en array som motsvarar ett schackbräde.
Schackbrädet kan ritas ut grafiskt.
Schackbrädet är rutat i två färger.
*/


public class Board
{
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private Piece[][] board;


    public Board() {
	this.board = new Piece[WIDTH][HEIGHT];

	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] =  new Pawn(column, 1, Team.BLACK, this, PieceType.PAWN);
	}
	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Team.WHITE, this, PieceType.PAWN);
	}
	board[0][0] = new Rook(0, 0, Team.BLACK, this, PieceType.ROOK);
	board[1][0] = new Knight(1, 0, Team.BLACK, this, PieceType.KNIGHT);
	board[2][0] = new Bishop(2, 0, Team.BLACK, this, PieceType.BISHOP);
	board[3][0] = new King(3, 0, Team.BLACK, this, PieceType.KING);
	board[4][0] = new Queen(4, 0, Team.BLACK, this, PieceType.QUEEN);
	board[5][0] = new Bishop(5, 0, Team.BLACK, this, PieceType.BISHOP);
	board[6][0] = new Knight(6, 0, Team.BLACK, this, PieceType.KNIGHT);
	board[7][0] = new Rook(7, 0, Team.BLACK, this, PieceType.ROOK);

	board[0][7] = new Rook(0, 7, Team.WHITE, this, PieceType.ROOK);
	board[1][7] = new Knight(1, 7, Team.WHITE, this, PieceType.KNIGHT);
	board[2][7] = new Bishop(2, 7, Team.WHITE, this, PieceType.BISHOP);
	board[3][7] = new King(3, 7, Team.WHITE, this, PieceType.KING);
	board[4][7] = new Queen(4, 7, Team.WHITE, this, PieceType.QUEEN);
	board[5][7] = new Bishop(5, 7, Team.WHITE, this, PieceType.BISHOP);
	board[6][7] = new Knight(6, 7, Team.WHITE, this, PieceType.KNIGHT);
	board[7][7] = new Rook(7, 7, Team.WHITE, this, PieceType.ROOK);


    }

    public Piece getPiece(int column, int row) {
	return board[column][row];
    }

    public void killPiece(int column, int row) {
	board[column][row] = null;
    }


    public static int getWIDTH() {
	return WIDTH;
    }

    public static int getHEIGHT() {
	return HEIGHT;
    }
}
