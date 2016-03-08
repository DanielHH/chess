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
    private PieceType[][] board;


    public Board() {
	board = new PieceType[WIDTH][HEIGHT];

	for (int i = 0; i < WIDTH; i++) {
	    for (int j = 0; j < HEIGHT; j++) {
		board[i][j] = PieceType.EMPTY;
	    }
	}

	for (int i = 0; i < WIDTH; i++) {
	    board[i][1] = PieceType.PAWN;
	}
	for (int i = 0; i < WIDTH; i++) {
	    board[i][6] = PieceType.PAWN;
	}
	board[0][0] = PieceType.ROOK;
	board[1][0] = PieceType.KNIGHT;
	board[2][0] = PieceType.BISHOP;
	board[3][0] = PieceType.KING;
	board[4][0] = PieceType.QUEEN;
	board[5][0] = PieceType.BISHOP;
	board[6][0] = PieceType.KNIGHT;
	board[7][0] = PieceType.ROOK;
	board[0][7] = PieceType.ROOK;
	board[1][7] = PieceType.KNIGHT;
	board[2][7] = PieceType.BISHOP;
	board[3][7] = PieceType.KING;
	board[4][7] = PieceType.QUEEN;
	board[5][7] = PieceType.BISHOP;
	board[6][7] = PieceType.KNIGHT;
	board[7][7] = PieceType.ROOK;

    }

    public PieceType getPieceType(int i, int j) {
	return board[i][j];
    }

}
