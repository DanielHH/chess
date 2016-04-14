package main;

import java.io.IOException;

public class Bishop extends Piece {
    public Bishop(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.BISHOP, imageLocation);
    }

    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();
	Movement movement = this.moveDirection(horizontal, lateral);
	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0) {
	    if (!this.pieceInTheWay(movement, lateral)) {
	    moved = true;
	    }
	}
	return moved;
    }
}
