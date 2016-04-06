package main;

import javax.swing.*;
import java.io.IOException;

public class Rook extends Piece {


    public Rook(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.ROOK, imageLocation);
    }

    @Override
    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;
	if (!this.pieceInTheWay(horizontal, lateral)) {
	    if ((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) {
		startMovement(newColumn, newRow);
	    }
	}
	// !!!!!!! rockad behöver implementeras
    }
}
