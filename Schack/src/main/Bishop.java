package main;

import enums.Direction;
import enums.PieceType;
import enums.Team;

/**
* Contains the allowed type of movements for the Bishop piece.
 */
public class Bishop extends Piece {

    private final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bb.png";
    private final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wb.png";

    protected Bishop(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.BISHOP, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    protected boolean canMove(int newColumn, int newRow) {
	// returns a boolean for if the move is possible for this piece
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral =  newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = calculateSteps(horizontal, lateral);

	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0) {
	    moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }
}
