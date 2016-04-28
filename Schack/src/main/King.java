package main;


import enums.Direction;
import enums.PieceType;
import enums.Team;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Contains the allowed type of movements for the King piece and checks if the King is under threat or in check.
 */
public class King extends Piece
{
    private final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bk.png";
    private final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wk.png";

    protected King(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KING, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override protected boolean canMove(int newColumn, int newRow) {
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

    protected boolean isCheck() {
	return isThreatened(column, row);
    }

    protected boolean isCheckMate() {
	boolean checkMate = false;
	// check if the king can save itself or if
	// a teammate can save the king
	if (unthreatenedPlaces() == null) {
	    checkMate = true;
	    for (int i = 0; i < Board.WIDTH; i++) {
		for (int j = 0; j < Board.HEIGHT; j++) {
			checkMate = pieceWithSafeLegalMove(i, j);
		}
	    }
	}
	return checkMate;
    }

    private boolean pieceWithSafeLegalMove(int column, int row) {
	// check's if there is a legal move that is also safe for piece on column, row.
	boolean anySafeLegalMove = false;
	Piece tempPiece = board.getPiece(column, row);
	if (tempPiece != null) {
	    if (tempPiece.team == team) { // same team
		List<Entry<Integer, Integer>> legalmoves = tempPiece.legalMoves();
		for (Entry<Integer, Integer> move : legalmoves) {
		    if (tempPiece.safeMove(move.getKey(), move.getValue())) {
			anySafeLegalMove = false;
		    }
		}
	    }
	}
	return anySafeLegalMove;
    }
}
