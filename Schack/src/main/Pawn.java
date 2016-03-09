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
		if (horizontal == 0 && lateral == 1) {
			if (board.getPieceType(newColumn, newRow) == PieceType.EMPTY) {
				this.row = newRow;
			}
		else if (horizontal == 1 && lateral == 1) {
			    if (board.getPieceType(newColumn, newRow) == SVART PJÄS!!!)
			}
		}
	}


	// ändfall när bonde ska uppgraderas
	if (this.)
    }

}
