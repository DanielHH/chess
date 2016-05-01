package se.liu.ida.danhe178.rical803.tddd78.schack.pieces;

import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PieceType;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Team;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.Board;

/**
 * Contains the allowed type of movements for the Knight piece.
 *
 * Except being an extension of piece is also placed on the current instance of Board.
 */
public class Knight extends Piece {

    /**
     * Fields are static because the relative paths to the images need to be accessed before
     * object construction in the super constructor and might also find later use in a proposed editor mode.
     */
    private final static String BLACK_IMAGE_LOCATION = "pieceimages/bn.png";
    private final static String WHITE_IMAGE_LOCATION = "pieceimages/wn.png";

    public Knight(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KNIGHT, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    /**
     * returns a boolean for whether the move is possible for this piece or not
     * @param newColumn coordinate of column
     * @param newRow coordinate of row
     * @return boolean declaring whether a move can go through or not
     */
    @Override
	public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
		Piece tempPiece = getBoard().getPiece(newColumn, newRow);
		if (tempPiece != null) {
			if (tempPiece.getTeam() != getTeam()) {
				moved = true;
			}
		}
		else {
			moved = true;
		}
	}
	return moved;
    }
}
