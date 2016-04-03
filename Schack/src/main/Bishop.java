package main;

public class Bishop extends Piece {
    public Bishop(int column, int row, Team team, Board board, PieceType piece) {
	super(column, row, team, board, piece);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 && !this.pieceInTheWay(newColumn, newRow)) {
	    if (board.getPiece(newColumn, newRow).team != team) {
		board.getPiece(newColumn, newRow).killPiece();
		this.movePiece(newColumn, newRow);
	    }
	}
    }
}
