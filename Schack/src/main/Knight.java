package main;

import java.io.IOException;

public class Knight extends Piece {
    public Knight(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.KNIGHT, imageLocation);
    }

    @Override
    public Boolean move(int newColumn, int newRow) {
	Boolean moved = false;
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
	    startMovement(newColumn, newRow);
	    moved = true;
	}
	return moved;
    }
}
