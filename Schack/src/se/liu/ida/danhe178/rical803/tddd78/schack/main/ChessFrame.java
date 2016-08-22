package se.liu.ida.danhe178.rical803.tddd78.schack.main;


import javax.swing.*;
import java.awt.*;


/**
 * JFrame object that contains:
 * a menu bar, a ChessComponent and a TimerTask.
 *
 * Is used as a link between class Menus and ChessComponent
 * to connect a graphical menu option to the functions it executes.
 * Is called by methods in package actionlisteners.
 *
 * Is created by class TestBoard at start of a new game.
 */
public class ChessFrame extends JFrame {
    /**
     * Instantiates a ChessComponent.
     */
   	protected final ChessComponent chessComponent;
    /**
     * Instantiates a frame that contains the dialog frame.
     */
   	protected final JFrame frame = null;
    /**
     * Instantiates a Board to be the the game board.
     */
   	protected final Board board;




    ChessFrame(Board board) {
      super("Chess");
       this.board = board;
       chessComponent= new ChessComponent(board);
       board.addBoardListener(chessComponent);
      this.setLayout(new BorderLayout());
      Menus menu = new Menus(this);
       menu.createMenus();
       
      this.add(chessComponent);
      this.pack();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);

   }

    public ChessComponent getChessComponent() {
        return chessComponent;
    }

    public Board getBoard() {
        return board;
    }

    public JFrame getFrame() {
        return frame;
    }
}




