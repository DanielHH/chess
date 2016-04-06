package main;

import java.io.IOException;

public class Bishop extends Piece {
    public Bishop(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.BISHOP, imageLocation);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;
	System.out.println(horizontal + " horizontal");
	System.out.println(lateral + " lateral");

	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 && !this.pieceInTheWay(horizontal, lateral)) {
	    startMovement(newColumn, newRow);
	}
    }
}
