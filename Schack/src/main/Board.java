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
	    board[column][1] =  new Pawn(column, 1, Team.BLACK, this, PieceType.PAWN, "pieces/blackpawn.svg");
	}
	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Team.WHITE, this, PieceType.PAWN, "pieces/whitepawn.svg");
	}
	board[0][0] = new Rook(0, 0, Team.BLACK, this, PieceType.ROOK, "pieces/blackrook.svg");
	board[1][0] = new Knight(1, 0, Team.BLACK, this, PieceType.KNIGHT, "pieces/blackknight.svg");
	board[2][0] = new Bishop(2, 0, Team.BLACK, this, PieceType.BISHOP, "pieces/blackbishop.svg");
	board[3][0] = new King(3, 0, Team.BLACK, this, PieceType.KING,  "pieces/blackking.svg");
	board[4][0] = new Queen(4, 0, Team.BLACK, this, PieceType.QUEEN,  "pieces/blackqueen.svg");
	board[5][0] = new Bishop(5, 0, Team.BLACK, this, PieceType.BISHOP,  "pieces/blackbishop.svg");
	board[6][0] = new Knight(6, 0, Team.BLACK, this, PieceType.KNIGHT,  "pieces/blackknight.svg");
	board[7][0] = new Rook(7, 0, Team.BLACK, this, PieceType.ROOK,  "pieces/blackrook.svg");

	board[0][7] = new Rook(0, 7, Team.WHITE, this, PieceType.ROOK, "pieces/whiterook.svg");
	board[1][7] = new Knight(1, 7, Team.WHITE, this, PieceType.KNIGHT, "pieces/whiteknight.svg");
	board[2][7] = new Bishop(2, 7, Team.WHITE, this, PieceType.BISHOP, "pieces/whitebishop.svg");
	board[3][7] = new King(3, 7, Team.WHITE, this, PieceType.KING, "pieces/whiteking.svg");
	board[4][7] = new Queen(4, 7, Team.WHITE, this, PieceType.QUEEN, "pieces/whitequeen.svg");
	board[5][7] = new Bishop(5, 7, Team.WHITE, this, PieceType.BISHOP, "pieces/whitebishop.svg");
	board[6][7] = new Knight(6, 7, Team.WHITE, this, PieceType.KNIGHT, "pieces/whiteknight.svg");
	board[7][7] = new Rook(7, 7, Team.WHITE, this, PieceType.ROOK, "pieces/whiterook.svg");


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
