package main;

public class Bishop extends Piece {

    final static String blImageLocation = "fantasy/png-shad/bb.png";
    final static String whImageLocation = "fantasy/png-shad/wb.png";

    public Bishop(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.BISHOP, blImageLocation, whImageLocation);
    }

    public boolean canMove(int newColumn, int newRow) {
	boolean moved = false;
	int horizontal = newColumn - this.getColumn();
	int lateral = newRow - this.getRow();
	Movement movement = this.moveDirection(horizontal, lateral);
	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0) {
	    if (!this.pieceInTheWay(movement, lateral)) {
	    moved = true;
	    }
	}
	return moved;
    }
}
