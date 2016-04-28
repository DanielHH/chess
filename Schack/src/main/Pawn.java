package main;


/**
 * Contains the allowed type of movements for the Pawn piece.
 */
public class Pawn extends Piece
{
    final static String blImageLocation = "fantasy/png-shad/bp.png";
    final static String whImageLocation = "fantasy/png-shad/wp.png";

    public Pawn(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.PAWN, blImageLocation, whImageLocation);
    }

    @Override public boolean canMove(int newColumn, int newRow) { // Unfortunately this has to stay complex
	boolean canMove = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();
	if (this.getTeam() == Team.WHITE) {
	    canMove = canMoveWhitePawn(horizontal, lateral, newColumn, newRow);
	} else if (this.getTeam() == Team.BLACK) {
	    canMove = canMoveBlackPawn(horizontal, lateral, newColumn, newRow);
	}
	return canMove;
    }

    private boolean canMoveBlackPawn(int horizontal, int lateral, int newColumn, int newRow) {
	boolean canMove = false;
	if (horizontal == 0 && lateral == 1) {
	    if (board.getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	} else if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
	    if (board.getPiece(newColumn, newRow) != null) {
		if (board.getPiece(newColumn, newRow).team == Team.WHITE) {
		    canMove = true;
		}
	    }
	} else if (horizontal == 0 && lateral == 2) {
	    if (!this.pieceInTheWay(Direction.DOWN, lateral) && !this.hasMoved() &&
		board.getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	}
	return canMove;
    }

    private boolean canMoveWhitePawn(int horizontal, int lateral, int newColumn, int newRow) {
	boolean canMove = false;
	if (horizontal == 0 && lateral == -1) {
	    if (board.getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	} else if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
	    if (board.getPiece(newColumn, newRow) != null) {
		if (board.getPiece(newColumn, newRow).team == Team.BLACK) {
		    canMove = true;
		}
	    }
	} else if (horizontal == 0 && lateral == -2) {
	    if (!this.pieceInTheWay(Direction.UP, lateral) && !this.hasMoved() &&
		board.getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	}
	return canMove;
    }

    // @Override
    public boolean canHit(int newColumn, int newRow) {
	// checks if pawn can hit a position
	boolean canHitIt = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();
	if (team == Team.WHITE) {
	    if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
		canHitIt = true;
	    }
	} else if (team == Team.BLACK) {
	    if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
		canHitIt = true;
	    }
	}
	return canHitIt;

    }
}


