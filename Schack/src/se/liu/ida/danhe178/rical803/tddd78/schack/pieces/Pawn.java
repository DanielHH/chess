package se.liu.ida.danhe178.rical803.tddd78.schack.pieces;


import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Direction;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PieceType;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Team;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.Board;

/**
 * Contains the allowed type of movements for the Pawn piece.
 *
 * Except being an extension of piece is also placed on the current instance of Board.
 */
public class Pawn extends Piece {

    /**
     * Fields are static because the relative paths to the images need to be accessed before
     * object construction in the super constructor and might also find later use in a proposed editor mode.
     */
    private final static String BLACK_IMAGE_LOCATION = "pieceimages/bp.png";
    private final static String WHITE_IMAGE_LOCATION = "pieceimages/wp.png";

    public Pawn(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.PAWN, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    /**
     * returns a boolean for whether the move is possible for this piece or not
     * @param newColumn coordinate for column
     * @param newRow coordinate for row
     * @return boolean declaring whether move can go through or not
     */
    @Override
	public boolean canMove(int newColumn, int newRow) {
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
	    if (getBoard().getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	} else if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
	    if (getBoard().getPiece(newColumn, newRow) != null) {
		if (getBoard().getPiece(newColumn, newRow).getTeam() == Team.WHITE) {
		    canMove = true;
		}
	    }
	} else if (horizontal == 0 && lateral == 2) {
	    if (!this.pieceInTheWay(Direction.DOWN, lateral) && !this.hasMoved() &&
		getBoard().getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	}
	return canMove;
    }

    private boolean canMoveWhitePawn(int horizontal, int lateral, int newColumn, int newRow) {
	boolean canMove = false;
	if (horizontal == 0 && lateral == -1) {
	    if (getBoard().getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	} else if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
	    if (getBoard().getPiece(newColumn, newRow) != null) {
		if (getBoard().getPiece(newColumn, newRow).getTeam() == Team.BLACK) {
		    canMove = true;
		}
	    }
	} else if (horizontal == 0 && lateral == -2) {
	    if (!this.pieceInTheWay(Direction.UP, lateral) && !this.hasMoved() &&
		getBoard().getPiece(newColumn, newRow) == null) {
		canMove = true;
	    }
	}
	return canMove;
    }

    public boolean canHit(int newColumn, int newRow) {
	// checks if pawn can hit a position
	boolean canHitIt = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();
	if (getTeam() == Team.WHITE) {
	    if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
		canHitIt = true;
	    }
	} else if (getTeam() == Team.BLACK) {
	    if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
		canHitIt = true;
	    }
	}
	return canHitIt;

    }
}


