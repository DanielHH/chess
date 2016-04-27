package main;

import java.io.IOException;


public class Bishop extends Piece {
    public Bishop(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.BISHOP, imageLocation);
    }

    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
	int horizontal = newColumn - this.getColumn();
	int lateral =  newRow - this.getRow();
	Movement movement = this.moveDirection(horizontal, lateral);
	int steps = horizontal;
	if (lateral != 0) {
		steps = lateral;
	}
	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0) {
	    if (!this.pieceInTheWay(movement, steps)) {
		Piece tempPiece = board.getPiece(newColumn, newRow);
		if (tempPiece != null) {
		    if (tempPiece.team != team) {
			moved = true;
		    }
		}
		else {
		    moved = true;
		}
	    }
	}
	return moved;
    }
}
