package main;

import enums.Direction;
import enums.PieceType;
import enums.Team;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;


/**
 * An abstract class containing mutual characteristics
 * and behaviours of the different pieces in classes: Bishop, King, Knight, Pawn, Queen, Rook.
 */

public abstract class Piece implements Serializable
{
    protected int column;
    protected int row;
    protected boolean hasMoved;
    protected Team team;
    protected Board board;
    protected PieceType piece;
    protected String blImageLocation;
    protected String whImageLocation;

    protected transient BufferedImage image = null;

    protected Piece(final int column, final int row, Team team, Board board, PieceType piece, String blImageLocation,
                    String whImageLocation)
    {
        this.row = row;
	this.column = column;
        hasMoved = false;
        this.team = team;
        this.board = board;
        this.piece = piece;
        this.whImageLocation = whImageLocation;
        this.blImageLocation = blImageLocation;
        setImage();
    }

    protected BufferedImage getImage() {
        return image;
    }

    protected void setImage() {
	String imageLocation;
	if (this.team == Team.WHITE) {
	    imageLocation = whImageLocation;
	}
	else {
	    imageLocation = blImageLocation;
	}
        try {
            this.image = ImageIO.read(getClass().getResource(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int getColumn() {
        return column;
    }

    protected int getRow() {
        return row;
    }

    protected Team getTeam() {
        return team;
    }

    protected boolean hasMoved() {
        return hasMoved;
    }

    private void moved() {hasMoved = true;}

    protected boolean isThreatened(int newColumn, int newRow) {
	boolean threatened = false;
	for (int i = 0; i < Board.WIDTH; i++) {
	    for (int j = 0; j < Board.HEIGHT; j++) {
		Piece tempPiece = board.getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team != this.team) {
			if (tempPiece.piece == PieceType.PAWN) {
			    if (((Pawn) tempPiece).canHit(newColumn, newRow)) {
				threatened = true;
			    }
			} else {
			    if (tempPiece.canMove(newColumn, newRow)) {
				threatened = true;
			    }
			}
		    }
		}
	    }
	}
	return threatened;
    }

    /**
     * Takes in a legal move and checks if position, which is clicked, ends up in check for own team's king, in which case
     * move does not go through, else the move takes place.
     * @param newColumn column coordinate of clicked position
     * @param newRow row coordinate of clicked position
     * @return boolean safe
     */
    protected boolean safeMove(int newColumn, int newRow) {
        boolean safe = false;
        Piece tempPiece = board.board[newColumn][newRow];
        board.checksForCheck();
        if (board.defendKing) { // currently in check
            System.out.println("Defend the King!!");
            testCheckMove(newColumn, newRow, tempPiece);
            if (!board.defendKing && piece != PieceType.KING) { // move saved the king
                System.out.println("king is safe!!");
                safe = true;
            }
            else {
                if (piece == PieceType.KING && !this.isThreatened(newColumn, newRow)) { // the king tries to dodge
                    board.board[newColumn][newRow] = board.board[column][row];
                    board.board[column][row] = null;
                    if(this.isThreatened(newColumn, newRow)) { // king tried to run in the opposite direction
                        System.out.println("Can't run that way!");
                    }
                    else { // dodge is successful
                        safe = true;
                        System.out.println("King dodge!");
                    }
                    board.board[column][row] = board.board[newColumn][newRow];
                    board.board[newColumn][newRow] = null;
                    board.board[newColumn][newRow] = tempPiece;
                }
                else {
                    System.out.println("King isn't safe yet!");
                }
            }
        }
        else { // not currently in check
            testCheckMove(newColumn, newRow, tempPiece);
            if (!board.defendKing) { //
                if (piece == PieceType.KING && this.isThreatened(newColumn, newRow)) { // the king moves into check
                    System.out.println("Suicidal !?");
                }
                else {
                    board.board[newColumn][newRow] = null;
                    if (piece == PieceType.KING && this.isThreatened(newColumn, newRow)){
                        System.out.println("It's not worth it, King!!");
                    }
                        else { // safe move
                        safe = true;
                    }
                    board.board[newColumn][newRow] = tempPiece;
                }
            }
            else { // move puts the king in check
                System.out.println("Trying to kill your king?!");
            }
        }
        return safe;
    }

    /**
     * Moves a piece to a new place and sets the old place to
     * null. Then runs check for checks and undos the move change.
     *
     * @param newColumn coordinate for the column the piece wants to move to
     * @param newRow coordinate for the row the piece wants to move to
     * @param tempPiece holds the piece on the position we want to check
     */
    private void testCheckMove(int newColumn, int newRow, Piece tempPiece) {
        board.board[newColumn][newRow] = board.board[column][row];
        board.board[column][row] = null;
        board.checksForCheck();
        board.board[column][row] = board.board[newColumn][newRow];
        board.board[newColumn][newRow] = null;
        board.board[newColumn][newRow] = tempPiece;
    }

    /**
     * Begins the movement of a piece on the board to
     * a new place and kills the enemy in that place.
     *
     * @param newColumn column coordinate for the new place
     * @param newRow row coordinate for the new place
     */
    protected void move(int newColumn, int newRow) {
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

    /**
     * Checks if there is a piece in the way in a specified direction and
     * number of steps away from current position.
     *
     * There are small but significant difference in code. Falseflag as duplicated.
     * Also not overly complex as it can not be made smaller
     * if it is supposed to handle all directions.
     *
     * @param direction the direction the piece wants to go
     * @param steps number of steps to go
     * @return if the piece can move or not
     */
    protected boolean pieceInTheWay(Direction direction, int steps) {
        boolean canNotMove = false;
        if (direction == Direction.UP || direction == Direction.DOWN) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(column, row + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (direction == Direction.RIGHT || direction == Direction.LEFT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(column + x, row) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (direction == Direction.UPRIGHT || direction == Direction.DOWNLEFT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(column - x, row + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (direction == Direction.UPLEFT || direction == Direction.DOWNRIGHT) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps < 0) {
                    x *= -1;
                }
                if (board.getPiece(column + x, row + x) != null) {
                    canNotMove = true;
                }
            }
        }
        else {
            canNotMove = true;
        }
        return canNotMove;
    }

    /**
     * Is used to evaluate the pieceInTheWay method.
     * @param direction the direction the piece wants to go
     * @param steps number of steps to go
     * @param newColumn column coordinate for the new place
     * @param newRow row coordinate for the new place
     * @return a boolean for whether the move can be done or not
     */
    protected boolean evaluatePieceInTheWay(Direction direction, int steps, int newColumn, int newRow) {
        boolean canMove = false;
        if (!this.pieceInTheWay(direction, steps)) { // no piece in the way
            Piece tempPiece = board.getPiece(newColumn, newRow);
            if (tempPiece != null) {
                if (tempPiece.team != team) {
                    canMove = true;
                }
            } else {
                canMove = true;
            }
        }
        return canMove;
    }

    protected abstract boolean canMove(int newColumn, int newRow);

    /**
     * calculates and returns a direction for a horizontal and lateral displacement
     * @param horizontal number which indicates direction
     * @param lateral number which indicates direction
     * @return a direction
     */
    protected Direction moveDirection(int horizontal, int lateral) {
        Direction direction = null;
        if (horizontal > 0 && lateral < 0) {
            direction = Direction.UPRIGHT;
        }
        else if  (horizontal < 0 && lateral < 0){
            direction = Direction.UPLEFT;
        }
        else if (horizontal > 0 && lateral > 0) {
            direction = Direction.DOWNRIGHT;
        }
        else if (horizontal < 0 && lateral > 0) {
            direction = Direction.DOWNLEFT;
        }
        else if (horizontal == 0 && lateral < 0) {
            direction = Direction.UP;
        }
        else if (horizontal == 0 && lateral > 0) {
            direction = Direction.DOWN;
        }
        else if (horizontal > 0 && lateral == 0) {
            direction = Direction.RIGHT;
        }
        else if (horizontal < 0 && lateral == 0) {
            direction = Direction.LEFT;
        }
        return direction;
    }

    /**
     * Returns a list of positions of legal moves for the piece.
     * @return list
     */
    protected List<Entry<Integer,Integer>> legalMoves() {
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

    /**
     * Returns the number of steps that a piece should check in
     * pieceInTheWay from its position in a certain direction
     * @param horizontal number of steps in horizontal direction
     * @param lateral number of steps in lateral direction
     * @return number of steps
     */
    protected int calculateSteps(int horizontal, int lateral) {
        int steps = horizontal;
       	if (lateral != 0) {
       	    steps = lateral;
       	}
       return steps;
    }
}
