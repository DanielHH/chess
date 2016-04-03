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
	    for (int row = 0; row < HEIGHT; row++) {
		board[column][row] = new Empty(column, row, Team.GREEN, this, PieceType.EMPTY);
	    }
	}

	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] =  new Pawn(column, 1, Team.BLACK, this, PieceType.PAWN);
	}
	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Team.WHITE, this, PieceType.PAWN);
	}
	board[0][1] = new Rook(0, 1, Team.BLACK, this, PieceType.ROOK);
	board[1][1] = new Knight(1, 1, Team.BLACK, this, PieceType.KNIGHT);
	board[2][1] = new Bishop(2, 1, Team.BLACK, this, PieceType.BISHOP);
	board[3][1] = new King(3, 1, Team.BLACK, this, PieceType.KING);
	board[4][1] = new Queen(4, 1, Team.BLACK, this, PieceType.QUEEN);
	board[5][1] = new Bishop(5, 1, Team.BLACK, this, PieceType.BISHOP);
	board[6][1] = new Knight(6, 1, Team.BLACK, this, PieceType.KNIGHT);
	board[7][1] = new Rook(7, 1, Team.BLACK, this, PieceType.ROOK);

	board[0][6] = new Rook(0, 1, Team.WHITE, this, PieceType.ROOK);
	board[1][6] = new Knight(1, 1, Team.WHITE, this, PieceType.KNIGHT);
	board[2][6] = new Bishop(2, 1, Team.WHITE, this, PieceType.BISHOP);
	board[3][6] = new King(3, 1, Team.WHITE, this, PieceType.KING);
	board[4][6] = new Queen(4, 1, Team.WHITE, this, PieceType.QUEEN);
	board[5][6] = new Bishop(5, 1, Team.WHITE, this, PieceType.BISHOP);
	board[6][6] = new Knight(6, 1, Team.WHITE, this, PieceType.KNIGHT);
	board[7][6] = new Rook(7, 1, Team.WHITE, this, PieceType.ROOK);


    }

    public Piece getPiece(int column, int row) {
	return board[column][row];
    }


    public static int getWIDTH() {
	return WIDTH;
    }

    public static int getHEIGHT() {
	return HEIGHT;
    }
}
