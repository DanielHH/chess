package main;

import java.io.IOException;

public class Queen extends Piece {
    public Queen(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.QUEEN, imageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();
		Movement movement = this.moveDirection(horizontal, lateral);
		int steps = horizontal;
		if (lateral != 0) {
			steps = lateral;
		}

		if ((((Math.abs(horizontal) > 0 && lateral == 0) ||
		      (Math.abs(lateral) > 0 && horizontal == 0)) ||
		     (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 ))) {
		    if (!this.pieceInTheWay(movement, steps)) { // no piece in the way
		    		moved = true;
		}}
	return moved;
	}
}

