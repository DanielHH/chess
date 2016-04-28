package main;

import java.io.IOException;

/**
* Contains the allowed type of movements for the Knight piece.
 */
public class Knight extends Piece {
    public Knight(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.KNIGHT, imageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
	    moved = true;
	}
	return moved;
    }
}
