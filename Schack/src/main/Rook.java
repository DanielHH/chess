package main;

import enums.Direction;
import enums.PieceType;
import enums.Team;

/**
 * Contains the allowed type of movements for the Rook piece.
 */
public class Rook extends Piece
{

    private final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/br.png";
    private final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wr.png";

    protected Rook(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.ROOK, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override protected boolean canMove(int newColumn, int newRow) {
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = calculateSteps(horizontal, lateral);

	if ((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) {
		moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }
}