package main;

/**
 * Contains the allowed type of movements for the Queen piece.
 */
public class Queen extends Piece
{
    final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bq.png";
    final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wq.png";

    public Queen(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.QUEEN, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
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

	if ((((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) ||
	     (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0))) {
		moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }
}

