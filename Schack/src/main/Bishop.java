package main;

public class Bishop extends Piece {
    public Bishop(int column, int row, Color color, Board board, PieceType piece) {
	super(column, row, color, board, piece);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if (Math.abs(horizontal) == Math.abs(lateral) && horizontal != 0 && !this.pieceInTheWay(newColumn, newRow)) {
	    if (board.getPiece(newColumn, newRow).color != color) {
		board.getPiece(newColumn, newRow).killPiece();
		this.movePiece(newColumn, newRow);
	    }
	}
    }
}
