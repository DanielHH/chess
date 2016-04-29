package main;

import enums.PlayerType;
import enums.Team;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JFrame object that contains:
 * a menubar, a ChessComponent and a TimerTask.
 *
 * Is used as a link between class Menus and ChessComponent
 * to connect a graphical menuoption to the functions it executes.
 * Is called by methods in package actionlisteners.
 *
 * Is created by class TestBoard at start of a new game.
 */
public class ChessFrame extends JFrame {
    /**
     * Instantiates a ChessComponent.
     */
   	public ChessComponent chessComponent;
    /**
     * Instantiates a frame that contains the dialog frame.
     */
   	public JFrame frame = null;
    /**
     * Instantiates a Board to be the the gameboard.
     */
   	public Board board;
    	private Timer timer = null;
    	private TimerTask runsGameAI = null;
    /**
     * static because number is to stay the same always.
     */
    	private final static int TIME_BETWEEN_AI_CHECKS = 500; // time in ms.

   protected ChessFrame(Board board) {
      super("Schack");
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
       resumeTimer();
   }

    public void pauseTimer() {
        timer.cancel();
    }

    public void resumeTimer() {
        timer = new Timer();
	createNewTimerTasks();
	//Schedule a task to run repeatedly, starting now,
 	// 1000ms fromexecution n ends to execution n+1 begins
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
	    if (chessComponent.player1 == PlayerType.AI) {
		chessComponent.walkAI();
	    }
	}
	else {
	    if (chessComponent.player2 == PlayerType.AI) {
		chessComponent.walkAI();
	    }
        }
    }
}




