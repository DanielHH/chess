package main;

import enums.Mode;
import enums.PieceType;
import enums.Team;
import pieces.*;

import java.io.Serializable;

/**
 * Calculates which turn it is and keeps track on what pieces are on the board.
 *
 * Is instanced in class BoardTest.
 */
public class Board implements Serializable {

	private King whiteKing = null;
	private King blackKing = null;

	/**
	 * Width of board.
	 * Static because we want to keep a standard chess board size available
	 */
	public static final int WIDTH = 8;
	/**
	 * Height of board.
	 * Static because we want to keep a standard chess board size available
	 */
	public static final int HEIGHT = 8;

	private int turnCounter = 0;
	private Piece[][] board = null;

	/**
	 * Contains boardlisteners
	 */
	private final BoardListener[] boardListenerList = new BoardListener[1];

	private boolean defendKing = false;

	Board() {
		setStartPositions();
	}

	Team getTurnTeam() { // gets the team (color) of the current round
		Team color = Team.WHITE;
		if (turnCounter % 2 != 0) {
			color = Team.BLACK;
		}
		return color;
	}

	private void nextTurn() {
		turnCounter += 1;

	}

	public void setTurnCounterToZero() {
		turnCounter = 0;
	}

	public void setStartPositions() { // sets the standard starting positions for a chess board.
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
		blackKing = new King(3, 0, Team.BLACK, this);
		board[3][0] = blackKing;
		board[4][0] = new Queen(4, 0, Team.BLACK, this);
		board[5][0] = new Bishop(5, 0, Team.BLACK, this);
		board[6][0] = new Knight(6, 0, Team.BLACK, this);
		board[7][0] = new Rook(7, 0, Team.BLACK, this);

		board[0][7] = new Rook(0, 7, Team.WHITE, this);
		board[1][7] = new Knight(1, 7, Team.WHITE, this);
		board[2][7] = new Bishop(2, 7, Team.WHITE, this);
		whiteKing = new King(3, 7, Team.WHITE, this);
		board[3][7] = whiteKing;
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
		Piece tempPiece = getPiece(oldColumn, oldRow);
		System.out.println("Began movement from: " + oldColumn + ", " + oldRow + " to: "  + newColumn + ", " + newRow);
		assert tempPiece != null;
		System.out.println("Moved " + tempPiece.getTeam() + " " +  tempPiece.getPieceType());
		board[newColumn][newRow] = tempPiece;
		board[oldColumn][oldRow] = null;
		pawnUpgrade(newColumn, newRow, tempPiece);
		tempPiece.setColumn(newColumn);
		tempPiece.setRow(newRow);
		nextTurn();
		if (getKing(getTurnTeam()).isCheckMate()) { // game over
			System.out.println(getTurnTeam() + " is checkmated");
			ChessComponent.setGameMode(Mode.PAUSE);
		} else if (getKing(getTurnTeam()).isDraw()) { // game over
			System.out.println("Draw");
			ChessComponent.setGameMode(Mode.PAUSE);
		}
		notifyListeners();
	}

	void addBoardListener(BoardListener bl) {
		boardListenerList[0] = bl;
	}

	private void notifyListeners() {
		for (BoardListener bl : boardListenerList) {
			bl.boardChanged();
		}
	}

	void markPiece() {
		notifyListeners();
	}

	int getTurnCounter() {
		return turnCounter;
	}

	private King getKing(Team team) {
		King king = null;
		if (Team.WHITE == team) {
			king = whiteKing;
		} else if (Team.BLACK == team) {
			king = blackKing;
		}
		return king;
	}

	/**
	 * replace all pieces in the current board with
	 * the same version as those in the loaded board
	 *
	 * @param loadedBoard contains a saved instance of a Board
	 */
	public void setLoadedBoard(Board loadedBoard) {
		turnCounter = loadedBoard.turnCounter;

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				Piece tempPiece = loadedBoard.board[i][j];
				if (tempPiece != null) { // there is a piece
					if (tempPiece.getPieceType() == PieceType.PAWN) {
						board[i][j] = new Pawn(i, j, tempPiece.getTeam(), this);
					} else if (tempPiece.getPieceType() == PieceType.BISHOP) {
						board[i][j] = new Bishop(i, j, tempPiece.getTeam(), this);
					} else if (tempPiece.getPieceType() == PieceType.KING) {
						King king = new King(i, j, tempPiece.getTeam(), this);
						board[i][j] = king;
						if (tempPiece.getTeam() == Team.WHITE) {
							whiteKing = king;
						}
						else {
							blackKing = king;
						}
					} else if (tempPiece.getPieceType() == PieceType.QUEEN) {
						board[i][j] = new Queen(i, j, tempPiece.getTeam(), this);
					} else if (tempPiece.getPieceType() == PieceType.ROOK) {
						board[i][j] = new Rook(i, j, tempPiece.getTeam(), this);
					} else if (tempPiece.getPieceType() == PieceType.KNIGHT) {
						board[i][j] = new Knight(i, j, tempPiece.getTeam(), this);
					}
					board[i][j].setHasMoved(tempPiece.getHasMoved());
				} else { // no piece
					board[i][j] = null;
				}
			}
		}
	}

	/**
	 * checks if the pawn can be upgraded to a queen.
	 * Does that when it reaches the lateral edge of the board
	 */
	private void pawnUpgrade(int newColumn, int newRow, Piece tempPiece) {
		if (tempPiece.getPieceType() == PieceType.PAWN) {
			if (newRow == 7 || newRow == 0) {
				board[newColumn][newRow] = new Queen(newColumn, newRow, tempPiece.getTeam(), this);
			}
		}
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

	public int numberOfPiecesOnBoard() {
		int counter = 0;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (board[i][j] != null) { // there is a piece
					counter++;
				}
			}
		}
		return counter;
	}

	/**
	 * Describes whether king is under check (true) or not (false).
	 */
	public boolean isDefendKing() {
		return defendKing;
	}

	public void setSquareOnBoard(int column, int row, Piece piece) {
		board[column][row] = piece;
	}

	public Piece[] getListAllPiecesInTeam(Team team) {
		Piece[] teamPieces = new Piece[16];
		int count = 0;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				Piece tempPiece = getPiece(i, j);
				if (tempPiece != null) {
					if (tempPiece.getTeam() == team) {
						teamPieces[count] = tempPiece;
						count += 1;
					}
				}
			}
		}
		return teamPieces;
	}
}
