package main;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JFrame object that contains:
 * a menubar, a ChessComponent and a TimerTask.
 */
public class ChessFrame extends JFrame {
    /**
     * Instantiates a ChessComponent.
     */
   	public ChessComponent chessComponent;
   	public JFrame frame = null;
   	public Board board;
    	private Timer timer = null;
    	private TimerTask runsGameAI = null;
    	//private TimerTask runsChecksForCheck;

   public ChessFrame(Board board) {
      super("Schack");
      this.setLayout(new BorderLayout());
      Menus menu = new Menus(this);
       menu.createMenus();

      chessComponent= new ChessComponent(board);
      board.addBoardListener(chessComponent);
      this.add(chessComponent);
      this.pack();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.board = board;
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
        timer.schedule(runsGameAI, new Date(), 1000);
    }

    private void createNewTimerTasks() {
	runsGameAI = new TimerTask() {
	    public void run() {
		try {
		    gameAI();
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	};
    }

    private void gameAI() throws InterruptedException {
	if (board.getTurnTeam() == Team.WHITE) {
	    if (chessComponent.player1 == PlayerType.AI) {
		chessComponent.AIWalk();
	    }
	}
	else {
	    if (chessComponent.player2 == PlayerType.AI) {
		chessComponent.AIWalk();
	    }
        }
    }
}




