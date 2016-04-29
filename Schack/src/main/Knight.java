package main;

import enums.PieceType;
import enums.Team;

/**
 * Contains the allowed type of movements for the Knight piece.
 *
 * Except being an exception of piece is also placed on the current instance of Board
 */
public class Knight extends Piece {
    /**
     * Fields are static because the relative paths to the images are unchangeable.
     */
    private final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bn.png";
    private final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wn.png";

    protected Knight(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KNIGHT, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    /**
     * returns a boolean for whether the move is possible for this piece or not
     * @param newColumn coordinate of column
     * @param newRow coordinate of row
     * @return boolean declaring whether a move can go through or not
     */
    @Override
    protected boolean canMove(int newColumn, int newRow) {
	boolean moved = false;

	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
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
	return moved;
    }
}
