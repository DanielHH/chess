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
    private int turnCounter = 0;
    private Piece[][] board;
    public BoardListener[] boardListenerList = new BoardListener[1];

    public Team getTurnTeam() {
	Team color = Team.WHITE;
	if (turnCounter % 2 != 0) {
	    color = Team.BLACK;
	}
	return color;
    }

    public void nextTurn() {
	this.turnCounter += 1;
    }

    public Board() throws IOException {
	board = new Piece[WIDTH][HEIGHT];

	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] =  new Pawn(column, 1, Team.BLACK, this, "fantasy/png-shad/bp.png");
	}

	for (int column = 0; column < WIDTH; column++) {
	    board[column][6] = new Pawn(column, 6, Team.WHITE, this, "fantasy/png-shad/wp.png");
	}

	// !!!!!!!!!!!!!!!1 Rensa bilder från konstruktorn ?????
	board[0][0] = new Rook(0, 0, Team.BLACK, this, "fantasy/png-shad/br.png");
	board[1][0] = new Knight(1, 0, Team.BLACK, this, "fantasy/png-shad/bn.png");
	board[2][0] = new Bishop(2, 0, Team.BLACK, this, "fantasy/png-shad/bb.png");
	board[3][0] = new King(3, 0, Team.BLACK, this,  "fantasy/png-shad/bk.png");
	board[4][0] = new Queen(4, 0, Team.BLACK, this,  "fantasy/png-shad/bq.png");
	board[5][0] = new Bishop(5, 0, Team.BLACK, this,  "fantasy/png-shad/bb.png");
	board[6][0] = new Knight(6, 0, Team.BLACK, this,  "fantasy/png-shad/bn.png");
	board[7][0] = new Rook(7, 0, Team.BLACK, this,  "fantasy/png-shad/br.png");

	board[0][7] = new Rook(0, 7, Team.WHITE, this, "fantasy/png-shad/wr.png");
	board[1][7] = new Knight(1, 7, Team.WHITE, this, "fantasy/png-shad/wn.png");
	board[2][7] = new Bishop(2, 7, Team.WHITE, this, "fantasy/png-shad/wb.png");
	board[3][7] = new King(3, 7, Team.WHITE, this, "fantasy/png-shad/wk.png");
	board[4][7] = new Queen(4, 7, Team.WHITE, this, "fantasy/png-shad/wq.png");
	board[5][7] = new Bishop(5, 7, Team.WHITE, this, "fantasy/png-shad/wb.png");
	board[6][7] = new Knight(6, 7, Team.WHITE, this, "fantasy/png-shad/wn.png");
	board[7][7] = new Rook(7, 7, Team.WHITE, this, "fantasy/png-shad/wr.png");
	}

    public Piece getPiece(int column, int row) {
	System.out.println(column + " column");
	System.out.println(row + " row");

	return board[column][row];
    }

    public void killPiece(int column, int row) {
	board[column][row] = null;
    }

    public void actuallyMovesPiece(int oldColumn, int oldRow, int newColumn, int newRow) {
	board[newColumn][newRow] = board[oldColumn][oldRow];
	board[oldColumn][oldRow] = null;
		nextTurn();
	notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
	this.boardListenerList[0] = bl;
    }

    private void notifyListeners() {
	for (BoardListener bl: boardListenerList) {
	    bl.boardChanged();
	}
    }

    public void markPiece() {
	notifyListeners();
    }

    public boolean onBoard(int column, int row) {
	boolean on = false;
	if (column >= 0 && column < Board.WIDTH) {
	    if (row >= 0 && row <= Board.HEIGHT) {
		on = true;
	    }
	}
	return on;
    }

    public int getTurnCounter() {
	return turnCounter;
    }

    public King getKing(Team team) {
	King king = null;
	for (int i = 0; i < Board.WIDTH; i++) {
	    for (int j = 0; j < Board.HEIGHT; j++) {
		Piece tempPiece = getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team == team && tempPiece.piece == PieceType.KING) {
			king = (King)tempPiece;
		    }
		}
	    }
	}
	return king;
    }
}
