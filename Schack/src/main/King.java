package main;


import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Contains the allowed type of movements for the King piece and checks if the King is under threat or in check.
 */
public class King extends Piece
{
    final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bk.png";
    final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wk.png";

    public King(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KING, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = horizontal;
	if (lateral != 0) {
	    steps = lateral;
	}

	if (Math.abs(lateral) < 2 && Math.abs(horizontal) < 2 && !(lateral == 0 && horizontal == 0)) {
	    moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }

    public boolean isThreatened(int newColumn, int newRow) {
	boolean threatened = false;
	for (int i = 0; i < Board.WIDTH; i++) {
	    for (int j = 0; j < Board.HEIGHT; j++) {
		Piece tempPiece = board.getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team != this.team) {
			if (tempPiece.piece == PieceType.PAWN) {
			    if (((Pawn) tempPiece).canHit(newColumn, newRow)) {
				threatened = true;
			    }
			} else {
			    if (tempPiece.canMove(newColumn, newRow)) {
				threatened = true;
			    }
			}
		    }
		}
	    }
	}
	return threatened;
    }

    private List<Entry<Integer, Integer>> unthreatenedPlaces() {
	List<Entry<Integer, Integer>> legalMovesList = this.legalMoves();
	List<Entry<Integer, Integer>> unthreatenedPlacesList = new ArrayList<>();
	if (!legalMovesList.isEmpty()) {
	    for (Entry<Integer, Integer> pair : legalMovesList) {
		if (!isThreatened(pair.getKey(), pair.getValue())) {
		    unthreatenedPlacesList.add(pair);
		}
	    }
	}
	if (!isThreatened(column, row)) {
	    Entry<Integer, Integer> pair = new SimpleEntry<>(column, row);
	    unthreatenedPlacesList.add(pair);
	}
	return unthreatenedPlacesList;
    }

    public boolean isCheck() {
	return isThreatened(column, row);
    }

    public boolean isCheckMate() {
	boolean checkMate = false;
	// check if the king can save itself or if
	// a teammate can save the king
	if (unthreatenedPlaces() == null) {
	    checkMate = true;
	    for (int i = 0; i < Board.WIDTH; i++) {
		for (int j = 0; j < Board.HEIGHT; j++) {
		    Piece tempPiece = board.getPiece(i, j);
		    if (tempPiece != null) {
			if (tempPiece.team == team) { // same team
			    List<Entry<Integer, Integer>> legalmoves = tempPiece.legalMoves();
			    // need to check if there is a legal move that is also safe,
			    // if not then it's checkmate
			    for (Entry<Integer, Integer> move : legalmoves) {
				if (tempPiece.safeMove(move.getKey(), move.getValue())) {
				    checkMate = false;
				}
			    }
			}
		    }
		}
	    }
	}
	return checkMate;
    }
}
