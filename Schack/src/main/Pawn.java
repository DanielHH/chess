package main;

public class Pawn extends Piece
{

    public Pawn(int column, int row, Color color, Board board, PieceType piece) {
	super(column, row, color, board, piece);
    }

    public void move(int newColumn, int newRow) {
	/* försöker komma fram till någon sätt att göra så att
	båda lagens bönder kan röra sig med hjälp av samma beräkning */

	int horizontal = this.getColumn() - newColumn;
	int lateral = this.getRow() - newRow;

	// många if-fall över tillåtna rörelser
	if (this.getColor() == Color.WHITE) {
	    // !!!!!!!! ändfall när bonde ska uppgraderas behöver läggas till
	    if (horizontal == 0 && lateral == 1) {
		if (board.getPiece(newColumn, newRow).piece == PieceType.EMPTY) {
		    this.movePiece(this.getColumn(), newRow);
		}
	    }
	    // Kan man göra såhär? 1 and 1 eller 1 and -1 är vad vi vill ha.
	    else if (lateral == 1 && (horizontal == 1 || horizontal == -1)) {
		if (board.getPiece(newColumn, newRow).color == Color.BLACK) {
		    board.getPiece(newColumn, newRow).killPiece();
		    this.movePiece(newColumn, newRow);
		}
		else {
		    // !!!!!!!! specialfall passant
		}
	    }
	    else if (horizontal == 0 && lateral == 2) {
		if (!this.pieceInTheWay(horizontal, lateral) && !this.hasMoved() &&
		    board.getPiece(newColumn, newRow).piece == PieceType.EMPTY) {
		    this.movePiece(this.getColumn(), newRow);
		}
	    }
	}
	else if (this.getColor() == Color.BLACK) {
	    // !!!!!!!! ändfall när bonde ska uppgraderas behöver läggas till
	    if (horizontal == 0 && lateral == -1) {
	  		    if (board.getPiece(newColumn, newRow).piece == PieceType.EMPTY) {
				this.movePiece(this.getColumn(), newRow);
	  		    }
	  		}
	    // Kan man göra såhär? 1 and 1 eller 1 and -1 är vad vi vill ha.
	    else if (lateral == -1 && (horizontal == 1 || horizontal == -1)) {
		if (board.getPiece(newColumn, newRow).color == Color.WHITE) {
		    board.getPiece(newColumn, newRow).killPiece();
		    this.movePiece(newColumn, newRow);
		}
		else {
		    // !!!!!!!!!!! specialfall passant
		}
	  		}
	    else if (horizontal == 0 && lateral == -2) {
		if (!this.pieceInTheWay(horizontal, lateral) && !this.hasMoved() &&
		    board.getPiece(newColumn, newRow).piece == PieceType.EMPTY) {
		    this.movePiece(this.getColumn(), newRow);
		}
	    }
	}
    }
}


