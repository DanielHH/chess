package main;


import java.io.IOException;

// !!!!!! KOLLAR INTE OM KUNGEN ÄR HOTAD !!!!!!!!
public class King extends Piece
{
    public King(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.KING, imageLocation);
    }

    @Override
    public Boolean canMove(int newColumn, int newRow) {
	Boolean moved = false;
		int horizontal = newColumn - this.getColumn();
		int lateral =  this.getRow() - newRow;

		if (Math.abs(lateral) < 2 && Math.abs(horizontal) < 2 && !(lateral == 0 && horizontal == 0)) {
		    moved = true;
		}
	return moved;
    }

    public boolean isThreatened(int newColumn, int newRow) {
	boolean threatened = false;
	for (int i = 0; i < ; i++) {
	    for (int j = 0; j < ; j++) {
		Piece tempPiece = board.getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team != this.team) {
			tempPiece.canMove(newColumn, newRow);
		    }
		}
	    }

	}
    }
}