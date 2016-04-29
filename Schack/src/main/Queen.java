package main;

import enums.Direction;
import enums.PieceType;
import enums.Team;

/**
 * Contains the allowed type of movements for the Queen piece.
 *
 * Except being an exception of piece is also placed on the current instance of Board
 */
public class Queen extends Piece
{
    /**
     * Fields are static because the relative paths to the images are unchangeable.
     */
    private final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bq.png";
    private final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wq.png";

    protected Queen(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.QUEEN, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override protected boolean canMove(int newColumn, int newRow) {
	// returns a boolean for if the move is possible for this piece
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = calculateSteps(horizontal, lateral);

	if ((((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) ||
	     (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0))) {
		moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }
}

