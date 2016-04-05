package main;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Piece
{
    protected int column;
    protected int row;
    protected boolean hasMoved;
    protected Team team;
    protected Board board;
    protected PieceType piece;

    protected BufferedImage image;


    public Piece(final int column, final int row, Team team, Board board, PieceType piece, String imageLocation)
            throws IOException
    {
	this.row = row;
	this.column = column;
        this.hasMoved = false;
        this.team = team;
        this.board = board;
        this.piece = piece;
        this.image = ImageIO.read(getClass().getResource(imageLocation));
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Team getTeam() {
        return team;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    protected void moved() {hasMoved = true;}

    public Board getBoard() {
        return board;
    }

    protected void movePiece(int newColumn, int newRow) {
        board.actuallyMovesPiece(column, row, newColumn, newRow);
        column = newColumn;
        row = newRow;
        if (!this.hasMoved()) {
            this.moved();
        }
    }

    public boolean pieceInTheWay(int horizontal, int lateral) {
        boolean canNotMove = false;
        if (horizontal < 0 && lateral < 0) {
            // bishop, move diagonal down right
            for (int j = -1; j > lateral; j--) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal > 0 && lateral < 0) {
            // bishop, move diagonal up right
            for (int j = 0; j < horizontal; j++) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0 && lateral > 0) {
            // bishop, move diagonal down left
            for (int j = 0; j < lateral; j++) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0 && lateral < 0) {
            // bishop, move diagonal up left
            for (int j = 0; j < lateral; j--) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (lateral > 0) {                                                             //ok
            // up
            for (int j = 1; j < lateral; j++) {
                if (board.getPiece(this.getColumn(), this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (lateral < 0) {                                                             //ok
            // down
            for (int j = -1; j > lateral; j--) {
                if (board.getPiece(this.getColumn(), this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal > 0) {                                                          //ok
            // left
            for (int i = 1; i < horizontal; i++) {
                if (board.getPiece(this.getColumn() - i, this.getRow()) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0) {                                                         //ok
            // right
            for (int i = -1; i > horizontal; i--) {
                if (board.getPiece(this.getColumn() - i, this.getRow()) != null) {
                    canNotMove = true;
                }
            }
        }
        System.out.println(canNotMove + " canNotmove");
        return canNotMove;
    }

    public void move(int column, int row) {
    }
}
