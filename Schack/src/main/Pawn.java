package main;

import java.io.IOException;


/**
* Contains the allowed type of movements for the Pawn piece.
 */
public class Pawn extends Piece
{

    public Pawn(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.PAWN, imageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
		boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();

		// många if-fall över tillåtna rörelser
		if (this.getTeam() == Team.WHITE) {
	    	if (horizontal == 0 && lateral == -1) {
				if (board.getPiece(newColumn, newRow) == null) {
				    moved = true;
				}
	    	}
	    	else if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
		    if (board.getPiece(newColumn, newRow) != null) {
			if (board.getPiece(newColumn, newRow).team == Team.BLACK) {
			    board.killPiece(newColumn, newRow);
			    moved = true;
			}
		    }
		    else {
				// !!!!!!!! specialfall passant
		    }
	    	}
	    	else if (horizontal == 0 && lateral == -2) {
				if (!this.pieceInTheWay(Movement.UP, lateral) && !this.hasMoved() &&
		    		board.getPiece(newColumn, newRow) == null) {
				    moved = true;
				}
	    	}
		}
		else if (this.getTeam() == Team.BLACK) {
	    	if (horizontal == 0 && lateral == 1) {
	  		    if (board.getPiece(newColumn, newRow) == null) {
				moved = true;
	  		    }
	  		}
	    	else if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
		    		if (board.getPiece(newColumn, newRow) != null) {
				    if (board.getPiece(newColumn, newRow).team == Team.WHITE) {
					board.killPiece(newColumn, newRow);
					moved = true;
				    }
				}
				else {
		   			 // !!!!!!!!!!! specialfall passant
				}
	  		}
	    	else if (horizontal == 0 && lateral == 2) {
				if (!this.pieceInTheWay(Movement.DOWN, lateral) && !this.hasMoved() &&
		    		board.getPiece(newColumn, newRow) == null) {
				    moved = true;
				}
	    	}

		}
		return moved;
    }

   // @Override
    public boolean canHit(int newColumn, int newRow) {
        // checks if pawn can hit a position
		boolean canHitIt = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();
		if (team == Team.WHITE) {
			if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
				canHitIt = true;
			}
		}
		else if (team == Team.BLACK) {
			if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
				canHitIt = true;
			}
		}
        return canHitIt;

    }
}


