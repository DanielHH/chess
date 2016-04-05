package main;

import sun.applet.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

    protected void movePiece(int nextColumn, int nextRow) {
        column = nextColumn;
        row = nextRow;
        if (!this.hasMoved()) {
            this.moved();
        }
    }

    public boolean pieceInTheWay(int horizontal, int lateral) {
        boolean canMove = true;
        if (horizontal > 0 && lateral > 0) {
            // bishop, move diagonal down right
            for (int j = 0; j < lateral; j++) {
                if (board.getPiece(this.getColumn() + j, this.getRow() + j) != null) {
                    canMove = false;
                }
            }
        }
        else if (horizontal > 0 && lateral < 0) {
            // bishop, move diagonal up right
            for (int j = 0; j < horizontal; j++) {
                if (board.getPiece(this.getColumn() + j, this.getRow() - j) != null) {
                    canMove = false;
                }
            }
        }
        else if (horizontal < 0 && lateral > 0) {
            // bishop, move diagonal down left
            for (int j = 0; j < lateral; j++) {
                if (board.getPiece(this.getColumn() - j, this.getRow() + j) != null) {
                    canMove = false;
                }
            }
        }
        else if (horizontal < 0 && lateral < 0) {
            // bishop, move diagonal up left
            for (int j = 0; j < lateral; j--) {
                if (board.getPiece(this.getColumn() + j, this.getRow() + j) != null) {
                    canMove = false;
                }
            }
        }
        else if (lateral > 0) {
            // down
            for (int j = 0; j < lateral; j++) {
                if (board.getPiece(this.getColumn(), this.getRow() + j) != null) {
                    canMove = false;
                }
            }
        }
        else if (lateral < 0) {
            // up
            for (int j = 0; j < lateral; j--) {
                if (board.getPiece(this.getColumn(), this.getRow() + j) != null) {
                    canMove = false;
                }
            }
        }
        else if (horizontal > 0) {
            // right
            for (int i = 0; i < horizontal; i++) {
                if (board.getPiece(this.getColumn() + i, this.getRow()) != null) {
                    canMove = false;
                }
            }
        }
        else if (horizontal < 0) {
            // left
            for (int i = 0; i < horizontal; i--) {
                if (board.getPiece(this.getColumn() + i, this.getRow()) != null) {
                    canMove = false;
                }
            }
        }
        return canMove;
    }
}
