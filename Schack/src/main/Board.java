package main;

import java.io.Serializable;

/**
 * Calculates and keeps track on what is on the board.
 */
public class Board implements Serializable{
    /**
     * Width of board.
     */
    public static final int WIDTH = 8;
    /**
     * Height of board.
     */
    public static final int HEIGHT = 8;
    private int turnCounter = 0;
    protected Piece[][] board = null;
    /**
     * Contains boardlisteners
     */
    public BoardListener[] boardListenerList = new BoardListener[1];
    /**
     * Describes whether king is under check (true) or not (false).
     */
    public boolean defendKing = false;

    public Team getTurnTeam() {
	Team color = Team.WHITE;
	if (turnCounter % 2 != 0) {
	    color = Team.BLACK;
	}
	return color;
    }

    public void nextTurn() {
	turnCounter += 1;
		System.out.println(getKing(getTurnTeam()).team);

    }

    public Board() {
	setStartPositions();
    }

    public void setTurnCounterToZero() {
	turnCounter = 0;
    }

    public void setStartPositions() {
	setTurnCounterToZero();
	defendKing = false;
    	board = new Piece[WIDTH][HEIGHT];

    	for (int column = 0; column < WIDTH; column++) {
	    board[column][1] = new Pawn(column, 1, Team.BLACK, this);
	}

   	    for (int column = 0; column < WIDTH; column++) {
   		board[column][6] = new Pawn(column, 6, Team.WHITE, this);
   	    }

   	    board[0][0] = new Rook(0, 0, Team.BLACK, this);
   	    board[1][0] = new Knight(1, 0, Team.BLACK, this);
   	    board[2][0] = new Bishop(2, 0, Team.BLACK, this);
   	    board[3][0] = new King(3, 0, Team.BLACK, this);
   	    board[4][0] = new Queen(4, 0, Team.BLACK, this);
   	    board[5][0] = new Bishop(5, 0, Team.BLACK, this);
   	    board[6][0] = new Knight(6, 0, Team.BLACK, this);
   	    board[7][0] = new Rook(7, 0, Team.BLACK, this);

   	    board[0][7] = new Rook(0, 7, Team.WHITE, this);
   	    board[1][7] = new Knight(1, 7, Team.WHITE, this);
   	    board[2][7] = new Bishop(2, 7, Team.WHITE, this);
   	    board[3][7] = new King(3, 7, Team.WHITE, this);
   	    board[4][7] = new Queen(4, 7, Team.WHITE, this);
   	    board[5][7] = new Bishop(5, 7, Team.WHITE, this);
   	    board[6][7] = new Knight(6, 7, Team.WHITE, this);
   	    board[7][7] = new Rook(7, 7, Team.WHITE, this);
        }


    public Piece getPiece(int column, int row) {
	return board[column][row];
    }

    public void killPiece(int column, int row) {
	board[column][row] = null;
    }

    public void actuallyMovesPiece(int oldColumn, int oldRow, int newColumn, int newRow) {
	Piece tempPiece = board[oldColumn][oldRow];
	board[newColumn][newRow] = tempPiece;
	board[oldColumn][oldRow] = null;
	if (tempPiece.piece == PieceType.PAWN) {
	    if (newRow == 7 || newRow == 0) {
		pawnUpgrade(newColumn, newRow, tempPiece.team);
	    }
	}
	nextTurn();
	notifyListeners();
	if (getKing(getTurnTeam()).isCheckMate()) {
	    // gameover
	    System.out.println("checkmate");
	}
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

    public void setLoadedBoard(Board loadedBoard) {
	turnCounter = loadedBoard.turnCounter;

	// replace all pieces in the current
	// board with those in the loaded board
	for (int i = 0; i < WIDTH; i++) {
	    for (int j = 0; j < HEIGHT; j++) {
		Piece tempPiece = loadedBoard.board[i][j];
		if (tempPiece != null) { // there is a piece
		    if (tempPiece.piece == PieceType.PAWN) {
			board[i][j] = new Pawn(i, j, tempPiece.team, this);
		    }
		    else if (tempPiece.piece == PieceType.BISHOP) {
			board[i][j] = new Bishop(i, j, tempPiece.team, this);
		    }
		    else if (tempPiece.piece == PieceType.KING) {
			board[i][j] = new King(i, j, tempPiece.team, this);
		    }
		    else if (tempPiece.piece == PieceType.QUEEN) {
			board[i][j] = new Queen(i, j, tempPiece.team, this);
		    }
		    else if (tempPiece.piece == PieceType.ROOK) {
			board[i][j] = new Rook(i, j, tempPiece.team, this);
		    }
		    else if (tempPiece.piece == PieceType.KNIGHT) {
			board[i][j] = new Knight(i, j, tempPiece.team, this);
		    }
		    board[i][j].hasMoved = tempPiece.hasMoved;
		}
		else { // no piece
		    board[i][j] = null;
		}
	    }
	}
    }

	public void pawnUpgrade(int column, int row, Team team) {
	    board[column][row] = new Queen(column, row, team, this);
	}

    public void checksForCheck() {
    	King king;
		defendKing = false;
		king = getKing(getTurnTeam());
    	if (king.isCheck()) {
		// show the user
			defendKing = true;
    	}
    }

}
