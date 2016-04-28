package main;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An abstract class containing mutual characteristics
 * and behaviours of the different pieces.
 */
public abstract class Piece
{
    protected int column;
    protected int row;
    protected boolean hasMoved;
    protected Team team;
    protected Board board;
    protected PieceType piece;
    protected BufferedImage image;


    protected Piece(final int column, final int row, Team team, Board board, PieceType piece, String imageLocation)
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

    public boolean safeMove(int newColumn, int newRow) {
        boolean safe = false;
        board.checksForCheck();
        if (board.defendKing) { // currently in check
            System.out.println("first line defend");
            board.board[newColumn][newRow] = board.board[column][row];
            board.board[column][row] = null;
            board.checksForCheck();
            board.board[column][row] = board.board[newColumn][newRow];
            board.board[newColumn][newRow] = null;
            if (!board.defendKing && piece != PieceType.KING) { // move saved the king
                System.out.println("king is safe!!");
                safe = true;
            }
            else {
                if (piece == PieceType.KING && !((King) this).isThreatened(newColumn, newRow)) { // the king tries to dogde
                    board.board[newColumn][newRow] = board.board[column][row];
                    board.board[column][row] = null;
                    if(((King) this).isThreatened(newColumn, newRow)) { // king tried to run in the opposite direction
                        System.out.println("Can't run that way!");
                    }
                    else { // dogde is succesfull
                        safe = true;
                        System.out.println("King dogde!");
                    }
                    board.board[column][row] = board.board[newColumn][newRow];
                    board.board[newColumn][newRow] = null;
                }
                else {
                    System.out.println("still in check, maddafakka");
                }
            }
        }
        else { // not currently in check
            board.board[newColumn][newRow] = board.board[column][row];
            board.board[column][row] = null;
            board.checksForCheck();
            board.board[column][row] = board.board[newColumn][newRow];
            board.board[newColumn][newRow] = null;
            if (!board.defendKing) {
                if (piece == PieceType.KING && ((King) this).isThreatened(newColumn, newRow)) { // the king moves into check
                    System.out.println("Suicidal !?");
                }
                else { // safe move
                    System.out.println("Totally safe");
                    safe = true;
                }
            }
            else { // move puts the king in check
                System.out.println("Trying to kill your king?!");
            }
        }
        return safe;
    }

    protected void move(int newColumn, int newRow) throws InterruptedException {
            if (board.getPiece(newColumn, newRow) != null) {

                if (board.getPiece(newColumn, newRow).team != team) {
                    board.killPiece(newColumn, newRow);
                }
            }
            board.actuallyMovesPiece(column, row, newColumn, newRow);
            column = newColumn;
            row = newRow;
            if (!this.hasMoved()) {
                this.moved();
            }
        }


    public boolean pieceInTheWay(Movement movement, int steps) {
        boolean canNotMove = false;
        if (movement == Movement.UP || movement == Movement.DOWN) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(this.getColumn(), this.getRow() + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (movement == Movement.RIGHT || movement == Movement.LEFT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(this.getColumn() + x, this.getRow()) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (movement == Movement.UPRIGHT || movement == Movement.DOWNLEFT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(this.getColumn() - x, this.getRow() + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (movement == Movement.UPLEFT || movement == Movement.DOWNRIGHT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(this.getColumn() + x, this.getRow() + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else {
            canNotMove = true;
        }
        return canNotMove;
    }

    public abstract boolean canMove(int column, int row);

    public Movement moveDirection(int horizontal, int lateral) {
        Movement movement = null;
        if (horizontal > 0 && lateral < 0) {
            movement = Movement.UPRIGHT;
        }
        else if  (horizontal < 0 && lateral < 0){
            movement = Movement.UPLEFT;
        }
        else if (horizontal > 0 && lateral > 0) {
            movement = Movement.DOWNRIGHT;
        }
        else if (horizontal < 0 && lateral > 0) {
            movement = Movement.DOWNLEFT;
        }
        else if (horizontal == 0 && lateral < 0) {
            movement = Movement.UP;
        }
        else if (horizontal == 0 && lateral > 0) {
            movement = Movement.DOWN;
        }
        else if (horizontal > 0 && lateral == 0) {
            movement = Movement.RIGHT;
        }
        else if (horizontal < 0 && lateral == 0) {
            movement = Movement.LEFT;
        }
        return movement;
    }

    public List<Entry<Integer,Integer>> legalMoves() {
        List<Entry<Integer,Integer>> legalMovesList = new ArrayList<>();
        for (int i = 0; i < Board.WIDTH; i++) {
            for (int j = 0; j < Board.HEIGHT; j++) {
                if (canMove(i, j)) { // piece can move there
                    Entry<Integer, Integer> pair = new SimpleEntry<>(i, j);
                    legalMovesList.add(pair);
                }
            }
        }
        return legalMovesList;
    }
}
