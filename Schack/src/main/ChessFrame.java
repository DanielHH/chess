package main;

import java.awt.event.InputEvent;

import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class ChessFrame extends JFrame {
   	public ChessComponent chessComponent;
   	private JFrame frame;
   	private Board board;
    	private Timer timer;
    	private TimerTask runsGameAI;
    	//private TimerTask runsChecksForCheck;

   public ChessFrame(Board board) throws InterruptedException {
      super("Schack");
      this.setLayout(new BorderLayout());
      createMenus();

      chessComponent= new ChessComponent(board);
      board.addBoardListener(chessComponent);
      this.add(chessComponent);
      this.pack();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
      this.board = board;
      resumeTimer();

   }


   private void createMenus() {
      final JMenuBar menuBar = new JMenuBar();

      final JMenu options = new JMenu("Options");
      final JMenuItem load = new JMenuItem("Load game");
      final JMenuItem save = new JMenuItem("Save");
      final JMenuItem reset = new JMenuItem("Reset");
      final JMenuItem quit = new JMenuItem("Quit");

      final JMenu mode = new JMenu("Mode");
      final JRadioButtonMenuItem pvp = new JRadioButtonMenuItem("PvP");
      final JRadioButtonMenuItem pvai = new JRadioButtonMenuItem("PvAI");
      final JRadioButtonMenuItem aivai = new JRadioButtonMenuItem("AIvAI");
       final JRadioButtonMenuItem pause = new JRadioButtonMenuItem("Pause");
      final ButtonGroup whichMode = new ButtonGroup();
      whichMode.add(pvp);
      whichMode.add(pvai);
      whichMode.add(aivai);
      whichMode.add(pause);




      reset.addActionListener(new ResetListener(pvp));
      reset.setMnemonic(KeyEvent.VK_R);
      reset.setAccelerator(
                     KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));

      quit.addActionListener(new QuitListener());
      quit.setMnemonic(KeyEvent.VK_Q);
      quit.setAccelerator(
         KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

       load.addActionListener(new LoadListener());
            load.setMnemonic(KeyEvent.VK_L);
            load.setAccelerator(
                     KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

            save.addActionListener(new SaveListener());
            save.setMnemonic(KeyEvent.VK_S);
            save.setAccelerator(
                     KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));

      options.add(load);
      options.add(save);
      options.add(reset);
      options.add(quit);
      menuBar.add(options);

      pvp.addActionListener(new PvpListener());
      pvp.setSelected(true);
      pvp.setMnemonic(KeyEvent.VK_P);
      pvp.setAccelerator(
               KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

      pvai.addActionListener(new PvaiListener());
      pvai.setMnemonic(KeyEvent.VK_V);
      pvai.setAccelerator(
               KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

      aivai.addActionListener(new AivaiListener());
      aivai.setMnemonic(KeyEvent.VK_A);
      aivai.setAccelerator(
               KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

       pause.addActionListener(new PauseListener());
      	pause.setMnemonic(KeyEvent.VK_P);
      	pause.setAccelerator(
		KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));

      mode.add(pvp);
      mode.add(pvai);
      mode.add(aivai);
       mode.add(pause);
      menuBar.add(mode);
      this.setJMenuBar(menuBar);
   }


   private class ResetListener implements ActionListener {
      JRadioButtonMenuItem pvp;
      public ResetListener(JRadioButtonMenuItem pvp) {
         this.pvp = pvp;
      }

      public void actionPerformed(final ActionEvent e) {
         try { // reset the board to starting state and the player mode to PVP
	     pauseTimer();
	     chessComponent.setGameMode(Mode.PVP);;
	     chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
	     board.setStartPositions();
	     pvp.setSelected(true);
	     chessComponent.setClickedPieceNull();
	     repaint();
	     board.setTurnCounterToZero();
	     resumeTimer();


         }
	 catch (IOException e1) {
	    e1.printStackTrace();
	 }
	 catch (InterruptedException e1) {
		e1.printStackTrace();
    	}

      }
   }
   private class QuitListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Quit?",
                                                JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
             System.exit(0);
          }
      }
   }

    private class LoadListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
          Board loadedBoard = SaveAndLoad.load();
          if (loadedBoard != null) {
             board.setLoadedBoard(loadedBoard);
	      chessComponent.setClickedPieceNull();
             repaint();
          }
       }
    }


    private class SaveListener implements ActionListener {
       public void actionPerformed(final ActionEvent e) {
	   SaveAndLoad.save(board);
       }}

   private class PvpListener implements ActionListener {
      public void actionPerformed(final ActionEvent e) {
         chessComponent.setGameMode(Mode.PVP);
	  chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
      }
   }

   private class PvaiListener implements ActionListener {
         public void actionPerformed(final ActionEvent e) {
	     chessComponent.setGameMode(Mode.PVAI);
	     chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
      }

   private class AivaiListener implements ActionListener {
         public void actionPerformed(final ActionEvent e) {
	     chessComponent.setGameMode(Mode.AIVAI);
	     chessComponent.setPlayers(PlayerType.AI, PlayerType.AI);
         }
      }

    private class PauseListener implements ActionListener {
	public void actionPerformed(final ActionEvent e) {
	    chessComponent.setGameMode(Mode.PAUSE);
	}
    }


    public void pauseTimer() {
        timer.cancel();
    }

    public void resumeTimer() {
        timer = new Timer();
	createNewTimerTasks();
        timer.schedule(runsGameAI, new Date(), 1000 );
        //timer.schedule(runsChecksForCheck, new Date(), 500);
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
/*
	runsChecksForCheck = new TimerTask() {
	    public void run() {
		board.checksForCheck();
	    }
	};*/
    }

     //Schedule a task to run repeatedly, starting now,
     // 500ms fromexecution n ends to execution n+1 begins

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




