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
	board[0][1] = new Rook(0, 1, Color.BLACK, this, PieceType.EMPTY);
	board[1][1] = new Knight(1, 1, Color.BLACK, this, PieceType.EMPTY);
	board[2][1] = new Bishop(2, 1, Color.BLACK, this, PieceType.EMPTY);
	board[3][1] = new King(3, 1, Color.BLACK, this, PieceType.EMPTY);
	board[4][1] = new Queen(4, 1, Color.BLACK, this, PieceType.EMPTY);
	board[5][1] = new Bishop(5, 1, Color.BLACK, this, PieceType.EMPTY);
	board[6][1] = new Knight(6, 1, Color.BLACK, this, PieceType.EMPTY);
	board[7][1] = new Rook(7, 1, Color.BLACK, this, PieceType.EMPTY);

	board[0][6] = new Rook(0, 1, Color.WHITE, this, PieceType.EMPTY);
	board[1][6] = new Knight(1, 1, Color.WHITE, this, PieceType.EMPTY);
	board[2][6] = new Bishop(2, 1, Color.WHITE, this, PieceType.EMPTY);
	board[3][6] = new King(3, 1, Color.WHITE, this, PieceType.EMPTY);
	board[4][6] = new Queen(4, 1, Color.WHITE, this, PieceType.EMPTY);
	board[5][6] = new Bishop(5, 1, Color.WHITE, this, PieceType.EMPTY);
	board[6][6] = new Knight(6, 1, Color.WHITE, this, PieceType.EMPTY);
	board[7][6] = new Rook(7, 1, Color.WHITE, this, PieceType.EMPTY);


    }

    public Piece getPieceType(int column, int row) {
	return board[column][row];
    }

}
