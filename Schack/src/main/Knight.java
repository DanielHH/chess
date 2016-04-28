package main;

/**
 * Contains the allowed type of movements for the Knight piece.
 */
public class Knight extends Piece {
    final static String BLACK_IMAGE_LOCATION = "fantasy/png-shad/bn.png";
    final static String WHITE_IMAGE_LOCATION = "fantasy/png-shad/wn.png";

    public Knight(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KNIGHT, BLACK_IMAGE_LOCATION, WHITE_IMAGE_LOCATION);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
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
