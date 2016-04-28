package main;

/**
* Contains the allowed type of movements for the Bishop piece.
 */
public class Bishop extends Piece {


    final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bb.png";
    final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wb.png";

    public Bishop(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.BISHOP, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral =  newRow - this.getRow();

	Direction direction = this.moveDirection(horizontal, lateral);
	int steps = horizontal;
	if (lateral != 0) {
		steps = lateral;
	}

	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0) {
	    moved = evaluatePieceInTheWay(direction, steps, newColumn, newRow);
	}
	return moved;
    }
}
