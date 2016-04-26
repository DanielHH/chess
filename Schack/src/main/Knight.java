package main;

public class Knight extends Piece {

    final static String blImageLocation = "fantasy/png-shad/bn.png";
    final static String whImageLocation = "fantasy/png-shad/wn.png";

    public Knight(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.KNIGHT, blImageLocation, whImageLocation);
    }

    @Override
    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
	    moved = true;
	}
	return moved;
    }
}
