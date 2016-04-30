package main;

import enums.Mode;
import enums.PlayerType;
import enums.Team;
import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

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
     * PlayerType of white player.
     * The game should only have one whitePlayer of PlayerType associated with it hence the static reference
     */
    static PlayerType whitePlayer = PlayerType.PLAYER;
    /**
     * PlayerType of black player.
     * The game should only have one blackPlayer of PlayerType associated with it hence the static reference
     */
    static PlayerType blackPlayer = PlayerType.PLAYER;

    /**
     * The game should only have one Mode, gameMode, associated with it hence the static reference
     */
    private static Mode gameMode = Mode.PVP;

    ChessComponent(Board board) {
        this.board = board;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // only allow clicks if current player is human
                if (board.getTurnTeam() == Team.WHITE && whitePlayer == PlayerType.PLAYER ||
                    board.getTurnTeam() == Team.BLACK && blackPlayer == PlayerType.PLAYER) {
                    int x = e.getX();
                    int y = e.getY();
                    int column = x / SQUARE_SIZE;
                    int row = y / SQUARE_SIZE;
                        tryMove(column, row);
                }
            }
        });
    }

    public void setClickedPieceNull() {
        clickedPiece = null;
    }

    public static void setGameMode(final Mode newGameMode) {
        gameMode = newGameMode;
    }

    private void tryMove(int column, int row) {
        if (gameMode != Mode.PAUSE) {
            Piece newPiece = board.getPiece(column, row);
            if (clickedPiece == null) { // First click
                if (newPiece != null && newPiece.getTeam() == board.getTurnTeam()) { // is a piece and the same team as the current turn's team
                    clickedPiece = newPiece;
                    board.markPiece();
                }
            } else { // a clicked piece exists
                if (newPiece != null && !Objects.equals(newPiece, clickedPiece)) {  // newPiece is a new piece
                    if (clickedPiece.getTeam() == newPiece.getTeam()) { // same team; switch marked piece
                        clickedPiece = newPiece;
                        board.markPiece();
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

                Piece currentPiece = board.getPiece(x, y);

                if (currentPiece != null) { // draw the piece
                    g.drawImage(currentPiece.getImage(), cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE, null);
                }
                if (Objects.equals(clickedPiece, currentPiece) && clickedPiece != null) {
                        g2d.setColor(Color.RED);
                        g2d.setStroke(new BasicStroke(2));
                        g2d.drawLine(cornerX, cornerY, cornerX + SQUARE_SIZE, cornerY);
                        g2d.drawLine(cornerX, cornerY, cornerX, cornerY + SQUARE_SIZE);

                        g2d.drawLine(cornerX, cornerY + SQUARE_SIZE - 1, cornerX + SQUARE_SIZE - 1, cornerY + SQUARE_SIZE - 1);
                        g2d.drawLine(cornerX + SQUARE_SIZE - 1, cornerY, cornerX + SQUARE_SIZE - 1, cornerY + SQUARE_SIZE - 1);
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

    void walkAI() {
        // tries to walk to a random place with a randomly chosen piece
        int turn = board.getTurnCounter();
        Random rand = new Random();
        while (turn == board.getTurnCounter()) {
            int x = rand.nextInt(Board.WIDTH);
            int y = rand.nextInt(Board.HEIGHT);
	    tryMove(x, y);
        }
    }

    public static void setPlayers(PlayerType newWhitePlayer, PlayerType newBlackPlayer) {
        whitePlayer = newWhitePlayer;
        blackPlayer = newBlackPlayer;
    }
}
