package main;

public class Rook extends Piece {
    public Rook(int column, int row, Team team, Board board, PieceType piece) {
	super(column, row, team, board, piece);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if (!this.pieceInTheWay(newColumn, newRow)) {
	    if ((Math.abs(horizontal) > 0 && lateral == 0) || (Math.abs(lateral) > 0 && horizontal == 0)) {
		if (board.getPiece(newColumn, newRow).team != team) {
		    board.getPiece(newColumn, newRow).killPiece();
		    this.movePiece(newColumn, newRow);
		}
	    }
	}
	// !!!!!!! rockad behöver implementeras
    }
}
