package main;

import java.io.IOException;

public class Pawn extends Piece
{

    public Pawn(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.PAWN, imageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
		/* försöker komma fram till någon sätt att göra så att
		båda lagens bönder kan röra sig med hjälp av samma beräkning */
		boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();

		// många if-fall över tillåtna rörelser
		if (this.getTeam() == Team.WHITE) {
	    	// !!!!!!!! ändfall när bonde ska uppgraderas behöver läggas till
	    	if (horizontal == 0 && lateral == -1) {
				if (board.getPiece(newColumn, newRow) == null) {
				    moved = true;
				}
	    	}
	    	// Kan man göra såhär? 1 and 1 eller 1 and -1 är vad vi vill ha.
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
	    	// !!!!!!!! ändfall när bonde ska uppgraderas behöver läggas till
	    	if (horizontal == 0 && lateral == 1) {
	  		    if (board.getPiece(newColumn, newRow) == null) {
				moved = true;
	  		    }
	  		}
	    	// Kan man göra såhär? 1 and 1 eller 1 and -1 är vad vi vill ha.
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
    public boolean canHit(int column, int row) {
        // för pawn
        return false;

    }
}


