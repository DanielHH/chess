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

    protected void startMovement(int newColumn, int newRow) {
        if (board.getPiece(newColumn, newRow) == null) {
            this.movePiece(newColumn, newRow);
        }
        else if (board.getPiece(newColumn, newRow).team != team) {
            board.killPiece(newColumn, newRow);
            this.movePiece(newColumn, newRow);
        }
    }

    public boolean pieceInTheWay(Movement movement, int steps) { // int horizontal, int lateral)
        // !!!!!!!!!!!!! Ersätt med enums i stil med Up, right, down... samt steps - hur många steg som tas
        boolean canNotMove = false;
        System.out.println(movement + " movement");
        System.out.println(steps + " steps");

        if (movement == Movement.UP || movement == Movement.DOWN) {
            for (int i = 1; i <  Math.abs(steps); i++) {
                int x = i;
                if (steps > 0) {
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


        /* !!!!!!!!!!!!!!! OLD CODE
        if (horizontal < 0 && lateral < 0) {
            System.out.println("down right");
            // bishop, move diagonal down right-
            for (int j = -1; j > lateral; j--) {
                System.out.println(j + " j");
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0 && lateral > 0) {
            System.out.println("up right");
            // bishop, move diagonal up right
            for (int j = -1; j > horizontal; j--) {
                System.out.println(j + " j");
                if (board.getPiece(this.getColumn() + j, this.getRow() + j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0 && lateral > 0) {
            System.out.println("down left");
            // bishop, move diagonal down left
            for (int j = 0; j < lateral; j++) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0 && lateral < 0) {
            System.out.println("up left");
            // bishop, move diagonal up left
            for (int j = 0; j < lateral; j--) {
                if (board.getPiece(this.getColumn() - j, this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (lateral > 0) {                                                             //ok
            // up
            System.out.println("up");
            for (int j = 1; j < lateral; j++) {
                if (board.getPiece(this.getColumn(), this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (lateral < 0) {                                                             //ok
            // down
            System.out.println("down");
            for (int j = -1; j > lateral; j--) {
                if (board.getPiece(this.getColumn(), this.getRow() - j) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal > 0) {                                                          //ok
            // left
            System.out.println("left");
            for (int i = 1; i < horizontal; i++) {
                if (board.getPiece(this.getColumn() - i, this.getRow()) != null) {
                    canNotMove = true;
                }
            }
        }
        else if (horizontal < 0) {                                                         //ok
            // right
            System.out.println("right");
            for (int i = -1; i > horizontal; i--) {
                if (board.getPiece(this.getColumn() - i, this.getRow()) != null) {
                    canNotMove = true;
                }
            }
        }
        */

        System.out.println(canNotMove + " canNotmove");
        return canNotMove;
    }

    public void move(int column, int row) {}

    public Movement moveDirection(int horizontal, int lateral) {
        Movement movement = null;
        if (horizontal > 0 && lateral > 0) {
            movement = Movement.UPRIGHT;
        }
        else if  (horizontal < 0 && lateral > 0){
            movement = Movement.UPLEFT;
        }
        else if (horizontal < 0 && lateral < 0) {
            movement = Movement.DOWNRIGHT;
        }
        else if (horizontal > 0 && lateral < 0) {
            movement = Movement.DOWNLEFT;
        }
        else if (horizontal == 0 && lateral > 0) {
            movement = Movement.UP;
        }
        else if (horizontal == 0 && lateral < 0) {
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
}
