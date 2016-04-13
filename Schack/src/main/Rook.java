package main;

import java.io.IOException;

public class Rook extends Piece {


    public Rook(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.ROOK, imageLocation);
    }

    @Override
    public Boolean canMove(int newColumn, int newRow) {
	Boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  this.getRow() - newRow;
		Movement movement = this.moveDirection(horizontal, lateral);
		int steps = horizontal;
		if (lateral != 0) {
			steps = lateral;
		}
		if (!this.pieceInTheWay(movement, steps)) {
	    	if ((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) {
		    moved = true;
	    	}
		}
	return moved;
		// !!!!!!! rockad behöver implementeras
    }
}
