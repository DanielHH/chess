package main;

public class Knight extends Piece {
    public Knight(int column, int row, Team team, Board board, PieceType piece) {
	super(column, row, team, board, piece);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if ((Math.abs(lateral) == 2 && Math.abs(horizontal) == 1) || (Math.abs(lateral) == 1 && Math.abs(horizontal) == 2)) {
	    if (board.getPiece(newColumn, newRow).team != team) {
		board.getPiece(newColumn, newRow).killPiece();
		this.movePiece(newColumn, newRow);
	    }
	}
    }
}
