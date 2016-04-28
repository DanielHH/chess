package main;

import java.io.IOException;

/**
* Contains the allowed type of movements for the Queen piece.
 */
public class Queen extends Piece {
    final static String blImageLocation = "fantasy/png-shad/bq.png";
    final static String whImageLocation = "fantasy/png-shad/wq.png";

    public Queen(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.QUEEN, blImageLocation, whImageLocation);
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
		    	System.out.println(" queen check");
			if (!this.pieceInTheWay(movement, steps)) { // no piece in the way
				Piece tempPiece = board.getPiece(newColumn, newRow);
				if (tempPiece != null) {
					if (tempPiece.team != team) {
						moved = true;
					}
				}
				else {
					moved = true;
				}
		}}
	return moved;
	}
}

