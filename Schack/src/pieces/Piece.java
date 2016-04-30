package pieces;

import enums.Direction;
import enums.PieceType;
import enums.Team;
import main.Board;

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

public abstract class Piece implements Serializable {
    private int column;
    private int row;
    private boolean hasMoved;
    final Team team;
    final Board board;
    private final PieceType pieceType;
    private final String blImageLocation;
    private final String whImageLocation;

    private transient BufferedImage image = null;

    Piece(int column, int row, Team team, Board board, PieceType pieceType, String blImageLocation,
          String whImageLocation)
    {
        this.row = row;
	    this.column = column;
        hasMoved = false;
        this.team = team;
        this.board = board;
        this.pieceType = pieceType;
        this.whImageLocation = whImageLocation;
        this.blImageLocation = blImageLocation;
        setImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    private void setImage() {
	String imageLocation;
	if (team == Team.WHITE) {
	    imageLocation = whImageLocation;
	}
	else {
	    imageLocation = blImageLocation;
	}
        try {
            image = ImageIO.read(getClass().getResource(imageLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    boolean hasMoved() {
        return hasMoved;
    }

    private void moved() {hasMoved = true;}

    boolean isThreatened(int newColumn, int newRow) {
	boolean threatened = false;
	for (int i = 0; i < Board.WIDTH; i++) {
	    for (int j = 0; j < Board.HEIGHT; j++) {
		Piece tempPiece = board.getPiece(i, j);
		if (tempPiece != null) {
		    if (tempPiece.team != team) {
			if (tempPiece.pieceType == PieceType.PAWN) {
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
    public boolean safeMove(int newColumn, int newRow, boolean silenced) {
        String safetyText = null;
        boolean safe = false;
        Piece tempPiece = board.getPiece(newColumn, newRow);
        board.checksForCheck();
        if (board.isDefendKing()) { // currently in check
            testCheckMove(newColumn, newRow, tempPiece);
            if (!board.isDefendKing() && pieceType != PieceType.KING) { // move saved the king
                safetyText = "king is safe!!";
                safe = true;
            }
            else {
                if (pieceType == PieceType.KING && !this.isThreatened(newColumn, newRow)) { // the king tries to dodge
                    board.setSquareOnBoard(newColumn,newRow, this);
                    board.setSquareOnBoard(column, row, null);
                    if(this.isThreatened(newColumn, newRow)) { // king tried to run in the opposite direction
                        safetyText = "Can't run that way!";
                    }
                    else { // dodge is successful
                        safe = true;
                        safetyText = "King dodge!";
                    }
                    board.setSquareOnBoard(column, row, this);
                    board.setSquareOnBoard(newColumn,newRow, tempPiece);
                }
                else {
                    safetyText = "King isn't safe yet!";
                }
            }
        }
        else { // not currently in check
            testCheckMove(newColumn, newRow, tempPiece);
            if (!board.isDefendKing()) { //
                if (pieceType == PieceType.KING && this.isThreatened(newColumn, newRow)) { // the king moves into check
                    safetyText = "Suicidal !?";
                }
                else { // might be a trap
                    board.setSquareOnBoard(newColumn,newRow, null);
                    if (pieceType == PieceType.KING && this.isThreatened(newColumn, newRow)){
                        safetyText = "It's not worth it, King!!";
                    }
                        else { // safe move
                        safe = true;
                    }
                    board.setSquareOnBoard(newColumn,newRow, tempPiece);
                }
            }
            else { // move puts the king in check
                safetyText = "Trying to kill your king?!";
            }
        }
        if (safetyText != null && !silenced) {
            System.out.println(safetyText);
        }
        return safe;
    }

    /**
     * Moves a piece to a new place and sets the old place to
     * null. Then runs check for checks and undoes the move change.
     *
     * @param newColumn coordinate for the column the piece wants to move to
     * @param newRow coordinate for the row the piece wants to move to
     * @param tempPiece holds the piece on the position we want to check
     */
    private void testCheckMove(int newColumn, int newRow, Piece tempPiece) {
        board.setSquareOnBoard(newColumn, newRow, this);
        board.setSquareOnBoard(column, row, null);
        board.checksForCheck();
        board.setSquareOnBoard(column, row, this);
        board.setSquareOnBoard(newColumn, newRow, tempPiece);
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Begins the movement of a piece on the board to
     * a new place and kills the enemy in that place.
     *
     * @param newColumn column coordinate for the new place
     * @param newRow row coordinate for the new place
     */
    public void move(int newColumn, int newRow) {
            if (board.getPiece(newColumn, newRow) != null) {

                if (board.getPiece(newColumn, newRow).team != team) {
                    board.killPiece(newColumn, newRow);
                }
            }
            board.actuallyMovesPiece(column, row, newColumn, newRow);
            if (!this.hasMoved()) {
                this.moved();
            }
        }

    /**
     * Checks if there is a piece in the way in a specified direction and
     * number of steps away from current position.
     *
     * @param direction the direction the piece wants to go
     * @param steps number of steps to go
     * @return if the piece can move or not
     */
    boolean pieceInTheWay(Direction direction, int steps) {
        boolean canNotMove = false;
        for (int i = 1; i <  Math.abs(steps); i++) {
            int x = i;
            if (steps < 0) {
                x *= -1;
            }
            if (direction == Direction.UP || direction == Direction.DOWN) {
                if (board.getPiece(column, row + x) != null) {
                    canNotMove = true;
                }
            } else if (direction == Direction.RIGHT || direction == Direction.LEFT) {
                if (board.getPiece(column + x, row) != null) {
                    canNotMove = true;
                }
            } else if (direction == Direction.UPRIGHT || direction == Direction.DOWNLEFT) {
                if (board.getPiece(column - x, row + x) != null) {
                    canNotMove = true;
                }
            } else if (direction == Direction.UPLEFT || direction == Direction.DOWNRIGHT) {
                if (board.getPiece(column + x, row + x) != null) {
                    canNotMove = true;
                }
            }
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
    boolean evaluatePieceInTheWay(Direction direction, int steps, int newColumn, int newRow) {
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

    public abstract boolean canMove(int newColumn, int newRow);

    /**
     * calculates and returns a direction for a horizontal and lateral displacement
     * @param horizontal number which indicates direction
     * @param lateral number which indicates direction
     * @return a direction
     */
    Direction moveDirection(int horizontal, int lateral) {
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

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * Returns a list of positions of legal moves for the piece.
     * @return list
     */
    List<Entry<Integer,Integer>> legalMoves() {
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

    public List<Entry<Integer, Integer>> listWithSafeLegalMoves(int column, int row) {
        // returns a list of safe and legal moves for a piece on int column, int row.
        List<Entry<Integer, Integer>> safeLegalMovesList = new ArrayList<>();
        Piece tempPiece = board.getPiece(column, row);
        if (tempPiece != null) { // a piece
            List<Entry<Integer, Integer>> legalMoves = tempPiece.legalMoves();
            for (Entry<Integer, Integer> move : legalMoves) {
                if (tempPiece.safeMove(move.getKey(), move.getValue(), true)) { // there is a safe move
                    Entry<Integer, Integer> pair = new SimpleEntry<>(move.getKey(), move.getValue());
                    safeLegalMovesList.add(pair);
                }
            }
        }
        return safeLegalMovesList;
    }

    /**
     * Returns the number of steps that a piece should check in
     * pieceInTheWay from its position in a certain direction
     * @param horizontal number of steps in horizontal direction
     * @param lateral number of steps in lateral direction
     * @return number of steps
     */
    int calculateSteps(int horizontal, int lateral) {
        int steps = horizontal;
       	if (lateral != 0) {
       	    steps = lateral;
       	}
       return steps;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
