package main;

public class Pawn extends Piece
{
    private boolean hasMoved;
    private String direction;

    public Pawn(int i, int j, String direction) {
	@super();
	this.hasMoved = false;
	this.direction = direction;
    }

    public void move(int i, int j) {
	/* försöker komma fram till någon sätt att göra så att
	båda lagens bönder kan röra sig med hjälp av samma beräkning */

	int horizontal = this.getColumn() - i;
	int lateral = this.getRow() - j;

	// många if-fall över tillåtna rörelser
	if (horizontal == 0 && lateral == 1) {

	}


	// ändfall när bonde ska uppgraderas
	if (this.)
    }

}
