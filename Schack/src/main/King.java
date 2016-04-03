package main;


// !!!!!! KOLLAR INTE OM KUNGEN ÄR HOTAD !!!!!!!!
public class King extends Piece
{
    public King(int column, int row, Team team, Board board, PieceType piece) {
	super(column, row, team, board, piece);
    }

    public void move(int newColumn, int newRow) {
	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	if (Math.abs(lateral) < 2 && Math.abs(horizontal) < 2 && !(lateral == 0 && horizontal == 0)) {
	    if (board.getPiece(newColumn, newRow).team != team) {
		board.killPiece(newColumn, newRow);
		this.movePiece(newColumn, newRow);
	    }
	}
    }
}