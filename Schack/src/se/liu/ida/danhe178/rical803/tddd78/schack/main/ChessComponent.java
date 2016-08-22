package se.liu.ida.danhe178.rical803.tddd78.schack.main;

import se.liu.ida.danhe178.rical803.tddd78.schack.pieces.Team;
import se.liu.ida.danhe178.rical803.tddd78.schack.pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;

/**
 * Paints the board, the pieces and the markings (if a piece or square gets clicked).
 * Contains the AI.
 * Contains a mouseListener which listens to clicks on board.
 * Contains which game mode the game currently is in and is called by class ChessFrame.
 */
public class ChessComponent extends JComponent implements BoardListener {
    private final Board board;
    /**
     * Size of the squares on the screen
     *
     * Static because size of the squares is a constant.
     */
    private static final int SQUARE_SIZE = 100;
    private Piece clickedPiece = null;

    /**
     * Static because number is a constant.
     */
    private final static int TIME_BETWEEN_AI_CLICKS = 1000; // time in ms.

    /**
     * Static because number is a constant.
     */
    private static final float VALUE_FOR_TRANSPARENCY = 0.75f;



    private transient Timer timer = null;
    private transient TimerTask runsGameAI = null;
    /**
     * static because number is a constant.
     */
    private final static int TIME_BETWEEN_AI_CHECKS = 2000; // time in ms.

    ChessComponent(Board board) {
        this.board = board;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // only allow clicks if current player is human
                if (board.getTurnTeam() == Team.WHITE && board.getWhitePlayer() == PlayerType.PLAYER ||
                    board.getTurnTeam() == Team.BLACK && board.getBlackPlayer() == PlayerType.PLAYER) {
                    int x = e.getX();
                    int y = e.getY();
                    int column = x / SQUARE_SIZE;
                    int row = y / SQUARE_SIZE;
                        tryMove(column, row);
                }
            }
        });
        resumeTimer();
    }

    public void setClickedPieceNull() {
        clickedPiece = null;
    }

    private void tryMove(int column, int row) {
        if (board.getGameMode() != Mode.PAUSE) {
            Piece newPiece = board.getPiece(column, row);
            if (clickedPiece == null) { // First click
                if (newPiece != null && newPiece.getTeam() == board.getTurnTeam()) { // is a piece and the same team as the current turn's team
                    clickedPiece = newPiece;
                    boardChanged();
                }
            } else { // a clicked piece exists
                if (newPiece != null && !Objects.equals(newPiece, clickedPiece)) {  // newPiece is a new piece
                    if (clickedPiece.getTeam() == newPiece.getTeam()) { // same team; switch marked piece
                        clickedPiece = newPiece;
                        boardChanged();
                    } else { // piece belong to the other team
                        tryMoveAndRemoveMark(column, row);
                    }
                } else { // no new piece
                    tryMoveAndRemoveMark(column, row);
                }
            }
        }
    }

    /**
     * tries to move if it can and if it's safe.
     * If so removes the mark from the piece and moves.
     * @param column coordinate of column
     * @param row coordinate of row
     */
    private void tryMoveAndRemoveMark(int column, int row) {
        boolean move = clickedPiece.canMove(column, row);
        if (move) { // can move
            boolean safe = clickedPiece.safeMove(column, row, false);
            if (safe) { // remove mark & move
                clickedPiece.move(column, row);
                clickedPiece = null;
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        List<Entry<Integer, Integer>> possibleMovesList = null;
        if (clickedPiece != null) {
            possibleMovesList = clickedPiece.listWithSafeLegalMoves(clickedPiece.getColumn(), clickedPiece.getRow());
        }
        for (int y = 0; y < Board.HEIGHT; y++) {
            for (int x = 0; x < Board.WIDTH; x++) {
                Color color = Color.WHITE;
                if (y % 2 == 0 && x % 2 == 1 || y % 2 == 1 && x % 2 == 0) { // negative values will not occur
                    color = Color.BLACK;
                }
                g2d.setColor(color);
                int cornerX = x * SQUARE_SIZE;
                int cornerY = y * SQUARE_SIZE;
                g2d.fillRect(cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE);

                if (possibleMovesList != null) { // there are possible moves to mark
                    Entry<Integer, Integer> tempMove = new SimpleEntry<>(x, y);
                    if (possibleMovesList.contains(tempMove)) { // mark the possible move
                        float alpha = VALUE_FOR_TRANSPARENCY; // alpha value for transparency
                        Color color1 = new Color(0, 1, 0, alpha);
                        g2d.setColor(color1);
                        g2d.fillRect(cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE);
                        g2d.setColor(Color.BLACK);
                        g2d.drawRect(cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE);
                    }
                }

                Piece currentPiece = board.getPiece(x, y);
                if (currentPiece != null) { // draw the piece
                    g.drawImage(currentPiece.getImage(), cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE, null);
                }
                if (Objects.equals(clickedPiece, currentPiece) && clickedPiece != null) { // mark the clickedPiece
                    g2d.setColor(Color.RED);
                    g2d.setStroke(new BasicStroke(4));
                    g2d.drawRect(cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension((Board.WIDTH) * SQUARE_SIZE,
                (Board.HEIGHT) * SQUARE_SIZE);
    }

    @Override
    public void boardChanged() {
            repaint();
    }

    /**
     * Picks a random piece of the current teams pieces and tries do a move.
     * If there is a killing move it picks that one otherwise just chooses a random one.
     * Continues to try to do a move until the game reaches the next turn and thus have made a move.
     */
    void walkAI() {
        int turn = board.getTurnCounter();
        Random rand = new Random();


        while (turn == board.getTurnCounter()) {
            Piece[] piecesInTeam = board.getListAllPiecesInTeam(board.getTurnTeam());
            int n = rand.nextInt(piecesInTeam.length);
            Piece tempPiece = piecesInTeam[n];

            if (tempPiece != null) { // there is a piece
                List<Entry<Integer, Integer>> moves = tempPiece.listWithSafeLegalMoves(tempPiece.getColumn(),
                                                                                       tempPiece.getRow());
                if (!moves.isEmpty()) { // there is a doable move
                    tryMove(tempPiece.getColumn(), tempPiece.getRow()); // mark the piece

                    Entry<Integer, Integer> move = null;
                    for (Entry<Integer, Integer> tempMove: moves) {
                        if (board.getPiece(tempMove.getKey(), tempMove.getValue()) != null) { // there is a kill move
                            move = tempMove;
                        }
                    }
                    if (move == null) { // no kill move, choose a random move.
                        int m = rand.nextInt(moves.size());
                        move = moves.get(m);
                    }
                    try {
                        Thread.sleep(TIME_BETWEEN_AI_CLICKS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tryMove(move.getKey(), move.getValue()); // do the move
                }
            }
        }
    }



    public void pauseTimer() {
        timer.cancel();
    }

    public void resumeTimer() {
        timer = new Timer();
	createNewTimerTasks();
	//Schedule a task to run repeatedly, starting now,
 	// 1000ms from execution n ends to execution n+1 begins
        timer.schedule(runsGameAI, new Date(), TIME_BETWEEN_AI_CHECKS);
    }

    private void createNewTimerTasks() {
	runsGameAI = new TimerTask() {

	    public void run() {
		    gameAI();
	    }
	};
    }

    /**
     * Runs the game AI, only tries to walk if it's the AI's turn
     */
    private void gameAI() {
	if (board.getTurnTeam() == Team.WHITE) {
	    if (board.getWhitePlayer() == PlayerType.AI) {
		walkAI();
	    }
	}
	else {
	    if (board.getBlackPlayer() == PlayerType.AI) {
		walkAI();
	    }
        }
    }
}
