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
		board[column][row] = new Empty(column, row, Color.WHITE, this, PieceType.EMPTY);
	    }
	}

	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] =  new Pawn(column, 1, Color.BLACK, this, PieceType.EMPTY);
	}
	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Color.WHITE, this, PieceType.EMPTY);
	}
	board[0][0] = new Rook(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[1][0] = new Knight(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[2][0] = new Bishop(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[3][0] = new King(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[4][0] = new Queen(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[5][0] = new Bishop(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[6][0] = new Knight(column, 1, Color.BLACK, this, PieceType.EMPTY);
	board[7][0] = new Rook(column, 1, Color.BLACK, this, PieceType.EMPTY);

	board[0][7] = new Rook(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[1][7] = new Knight(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[2][7] = new Bishop(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[3][7] = new King(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[4][7] = new Queen(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[5][7] = new Bishop(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[6][7] = new Knight(column, 1, Color.WHITE, this, PieceType.EMPTY);
	board[7][7] = new Rook(column, 1, Color.WHITE, this, PieceType.EMPTY);


    }

    public PieceType getPieceType(int column, int row) {
	return board[column][row];
    }

}
