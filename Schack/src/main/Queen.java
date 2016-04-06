package main;

import java.io.IOException;

public class Queen extends Piece {
    public Queen(int column, int row, Team team, Board board, String imageLocation) throws IOException {
	super(column, row, team, board, PieceType.QUEEN, imageLocation);
    }

    @Override
    public void move(int newColumn, int newRow) {
		int horizontal = newColumn - this.getColumn();
		int lateral =  newRow - this.getRow();
		Movement movement = this.moveDirection(horizontal, lateral);

		if (!this.pieceInTheWay(movement, newRow) && (((Math.abs(horizontal) > 0 && lateral == 0) ||
				(Math.abs(lateral) > 0 && horizontal == 0)) ||
				(Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 ))) {
			if (board.getPiece(newColumn, newRow).team != team) {
				board.killPiece(newColumn, newRow);
				this.movePiece(newColumn, newRow);
			}
		}
	}
}

