package main;

import java.io.IOException;

public class Bishop extends Piece {
    public Bishop(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.BISHOP, imageLocation);
    }

    public void move(int newColumn, int newRow) {
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();
		Movement movement = this.moveDirection(horizontal, lateral);
	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 && !this.pieceInTheWay(movement, lateral)) {
	    startMovement(newColumn, newRow);
	}
    }
}
