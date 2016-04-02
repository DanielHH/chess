package main;

public class Piece
{
    protected boolean alive;
    protected int column;
    protected int row;
    protected boolean hasMoved;
    protected Color color;
    protected Board board;
    protected PieceType piece;

    public Piece(final int column, final int row, Color color, Board board, PieceType piece) {
	this.row = row;
	this.column = column;
	this.alive = true;
        this.hasMoved = false;
        this.color = color;
        this.board = board;
        this.piece = piece;
    }

    public boolean isAlive() {
        return alive;
    }

    public void reanimatePiece() {
        alive = true;
    }

    public void killPiece() {
        alive = false;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Color getColor() {
        return color;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    protected void moved() {hasMoved = false;}

    public Board getBoard() {
        return board;
    }
}
