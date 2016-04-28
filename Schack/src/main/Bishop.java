package main;

import java.io.IOException;


public class Bishop extends Piece {


    final static String blImageLocation = "fantasy/png-shad/bb.png";
    final static String whImageLocation = "fantasy/png-shad/wb.png";

    public Bishop(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.BISHOP, blImageLocation, whImageLocation);
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
