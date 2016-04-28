package main;

/**
* Contains the allowed type of movements for the Rook piece.
 */
public class Rook extends Piece {

    final static String blImageLocation = "fantasy/png-shad/br.png";
    final static String whImageLocation = "fantasy/png-shad/wr.png";

    public Rook(int column, int row, Team team, Board board) {
	super(column, row, team, board, PieceType.ROOK, blImageLocation, whImageLocation);
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
	    	if ((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) {
		    if (!this.pieceInTheWay(movement, steps)) {
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
		}
	return moved;
		// !!!!!!! rockad behöver implementeras
    }
}
