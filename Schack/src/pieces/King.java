package pieces;


import enums.Direction;
import enums.PieceType;
import enums.Team;
import main.Board;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Contains the allowed type of movements for the King piece and contains methods to check
 * if the King is under threat or in check.
 *
 * Except being an extension of piece is also placed on the current instance of Board.
 */
public class King extends Piece {

    /**
     * Fields are static because the relative paths to the images need to be accessed before
     * object construction in the super constructor and might also find later use in a proposed editor mode.
     */
    private final static String BLACK_IMAGE_LOCATION = "png-shad/bk.png";
    private final static String WHITE_IMAGE_LOCATION = "png-shad/wk.png";

    public King(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KING, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override
	public boolean canMove(int newColumn, int newRow) {
	// returns a boolean for if the move is possible for this piece
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = calculateSteps(horizontal, lateral);

	if (Math.abs(lateral) < 2 && Math.abs(horizontal) < 2 && !(lateral == 0 && horizontal == 0)) {
	    moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }

    private List<Entry<Integer, Integer>> unthreatenedPlaces() {
	List<Entry<Integer, Integer>> legalMovesList = this.legalMoves();
	List<Entry<Integer, Integer>> unthreatenedPlacesList = new ArrayList<>();
	if (!legalMovesList.isEmpty()) { // there are legal moves
	    for (Entry<Integer, Integer> pair : legalMovesList) {
		if (safeMove(pair.getKey(), pair.getValue(), true)) { // not under threat in new position
			 unthreatenedPlacesList.add(pair);
		}
	    }
	}
	if (!isThreatened(this.getColumn(), this.getRow())) { // not directly under threat
	    Entry<Integer, Integer> pair = new SimpleEntry<>(this.getColumn(), this.getRow());
	    unthreatenedPlacesList.add(pair);
	}
	return unthreatenedPlacesList;
    }

    public boolean isCheck() {
	return isThreatened(this.getColumn(), this.getRow());
    }

    /**
     * check if the king can save itself or if a teammate can save the king
     * @return boolean for whether it is check mate or not
     */
	public boolean isCheckMate() {
	boolean checkMate = false;
	List<Entry<Integer, Integer>> safePlaces = unthreatenedPlaces();
	if (safePlaces.isEmpty()) {
	    checkMate = true;
		// need to check if the king can be saved
	    for (int i = 0; i < Board.WIDTH; i++) {
			for (int j = 0; j < Board.HEIGHT; j++) {
				if(pieceWithSafeLegalMove(i, j)) { // a piece can save the king
					checkMate = false;
				}
			}
	    }
	}
	return checkMate;
    }

	/**
	 * check if it is a draw. Currently not covering all draw cases (might be hard).
	 * @return boolean for whether it is a draw or not
	 */
	public boolean isDraw() {
		boolean draw = false;
		List<Entry<Integer, Integer>> safePlaces = unthreatenedPlaces();
		if (safePlaces.size() == 1 && safePlaces.get(0).getKey() == this.getColumn() && safePlaces.get(0).getValue() == this.getRow()) {
			// check for stalemate
			draw = true;
			for (int i = 0; i < Board.WIDTH; i++) {
				for (int j = 0; j < Board.HEIGHT; j++) {
					if(pieceWithSafeLegalMove(i, j)) { // a piece can save the king
						draw = false;
					}
				}
			}
		}
		else if (board.numberOfPiecesOnBoard() <= 3) { // check for material draw
			draw = true;
			// check if there is a pawn, queen or rook left. If so not a draw.
			for (int i = 0; i < Board.WIDTH; i++) {
				for (int j = 0; j < Board.HEIGHT; j++) {
					Piece tempPiece = board.getPiece(i, j);
					if (tempPiece != null) { // there is a piece
						if (tempPiece.getPieceType() == PieceType.PAWN ||
								tempPiece.getPieceType() == PieceType.QUEEN ||
								tempPiece.getPieceType() == PieceType.ROOK) {
							draw = false;
						}
					}
				}
			}
		}
		return draw;
	}

    private boolean pieceWithSafeLegalMove(int column, int row) {
	// check's if there is a legal move that is also safe for piece on column, row.
	boolean anySafeLegalMove = false;
	Piece tempPiece = board.getPiece(column, row);
	if (tempPiece != null) { // a piece
	    if (tempPiece.getTeam() == team) { // same team
		List<Entry<Integer, Integer>> legalMoves = tempPiece.legalMoves();
		for (Entry<Integer, Integer> move : legalMoves) {
		    if (tempPiece.safeMove(move.getKey(), move.getValue(), true)) { // there is a safe move
				anySafeLegalMove = true;
		    }
		}
	    }
	}
	return anySafeLegalMove;
    }
}
