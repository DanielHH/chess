package main;

public class Piece
{
    private boolean alive;
    private int i;
    private int j;

    public Piece(final int i, final int j) {
	this.j = j;
	this.i = i;
	this.alive = true;
    }

    public boolean isAlive() {
        return this.alive;
    }


    public void reanimatePiece() {
        this.alive = true;
    }

    public void killPiece() {
        this.alive = false;
    }

    public int getColumn() {
        return i;
    }
    public void setColumn(int i) {
        this.i = i;
    }
    public int getRow() {
        return j;
    }
    public void setRow(int j) {
        this.j = j;
    }


}
