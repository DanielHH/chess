package se.liu.ida.danhe178.rical803.tddd78.schack.main;

import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Mode;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PlayerType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Contains the menu and all of the menu options.
 * Sets actionListeners and then sets an instance of JMenuBar on an instance of class ChessFrame.
 */
class Menus {
    private final JMenuBar menuBar = new JMenuBar();

    private final JMenu options = new JMenu("Options");
    private final JMenuItem load = new JMenuItem("Load board");
    private final JMenuItem save = new JMenuItem("Save board");
    private final JMenuItem reset = new JMenuItem("Reset");
    private final JMenuItem quit = new JMenuItem("Quit");

    private final JMenu mode = new JMenu("Mode");
    private final JRadioButtonMenuItem pvp = new JRadioButtonMenuItem("PvP");
    private final JRadioButtonMenuItem pvai = new JRadioButtonMenuItem("PvAI");
    private final JRadioButtonMenuItem aivai = new JRadioButtonMenuItem("AIvAI");
    private final JRadioButtonMenuItem pause = new JRadioButtonMenuItem("Pause");
    private final ButtonGroup whichMode = new ButtonGroup();

    private final ChessFrame chessFrame;


    Menus(ChessFrame frame) {chessFrame = frame;
    }

    void createMenus() { // begins the creation of the menu
       whichMode.add(pvp);
       whichMode.add(pvai);
       whichMode.add(aivai);
       whichMode.add(pause);

       options.add(load);
       options.add(save);
       options.add(reset);
       options.add(quit);
       menuBar.add(options);

       setListeners();

       mode.add(pvp);
       mode.add(pvai);
       mode.add(aivai);
       mode.add(pause);
       menuBar.add(mode);
       chessFrame.setJMenuBar(menuBar);
    }

    private void setListeners() { // sets all the listeners for the menu
       reset.addActionListener(new ResetListener(pvp, chessFrame, chessFrame.board));
       reset.setMnemonic(KeyEvent.VK_R);
       reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));

       quit.addActionListener(new QuitListener(chessFrame));
       quit.setMnemonic(KeyEvent.VK_Q);
       quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));

       load.addActionListener(new LoadListener(chessFrame));
       load.setMnemonic(KeyEvent.VK_L);
       load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));

       save.addActionListener(new SaveListener(chessFrame.board));
       save.setMnemonic(KeyEvent.VK_S);
       save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));

       pvp.addActionListener(new PVPListener(chessFrame.board));
       pvp.setSelected(true);
       pvp.setMnemonic(KeyEvent.VK_P);
       pvp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));

       pvai.addActionListener(new PVAIListener(chessFrame.board));
       pvai.setMnemonic(KeyEvent.VK_V);
       pvai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

       aivai.addActionListener(new AIVAIListener(chessFrame.board));
       aivai.setMnemonic(KeyEvent.VK_A);
       aivai.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

       pause.addActionListener(new PauseListener());
       pause.setMnemonic(KeyEvent.VK_P);
       pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
    }

    /**
     * Listener set in the Menus class.
     * If mode AIVAI is chosen by player this class sets, via setters in
     * class ChessComponent, game mode and Players to appropriate types.
     */
    public class AIVAIListener implements ActionListener
    {
       Board board;

       public AIVAIListener(final Board board) {
           this.board = board;
       }

             public void actionPerformed(final ActionEvent e) {
            ChessComponent.setGameMode(Mode.AIVAI);
            board.setPlayers(PlayerType.AI, PlayerType.AI);
             }
    }

    /**
     * Listener set in the Menus class.
     * If option Load is chosen by player this class loads a saved board with methods via ChessFrame and SaveAndLoad.
     */
    public class LoadListener implements ActionListener
    {
        private final ChessFrame chessFrame;
        public LoadListener (ChessFrame frame){
            chessFrame = frame;
        }
           public void actionPerformed(ActionEvent e) {
              Board loadedBoard = SaveAndLoad.load();
              if (loadedBoard != null) {
                  chessFrame.board.setLoadedBoard(loadedBoard);
                  chessFrame.chessComponent.setClickedPieceNull();
                  chessFrame.repaint();
              }
           }
        }


    /**
     * Listener set in the Menus class.
     * If mode Pause is chosen by player this class sets, via setters in
     * class ChessComponent, game mode to appropriate type.
     */
    public class PauseListener implements ActionListener
    {
       public void actionPerformed(final ActionEvent e) {
           ChessComponent.setGameMode(Mode.PAUSE);
       }
        }

    /**
     * Listener set in the Menus class.
     * If mode PVAI is chosen by player this class sets, via setters in
     * class ChessComponent, game mode and Players to appropriate types.
     */
    public class PVAIListener implements ActionListener
    {
       Board board;

       public PVAIListener(final Board board) {
           this.board = board;
       }

       public void actionPerformed(final ActionEvent e) {
            ChessComponent.setGameMode(Mode.PVAI);
            board.setPlayers(PlayerType.PLAYER, PlayerType.AI);
             }
          }

    /**
     * Listener set in the Menus class.
     * If mode PVP is chosen by player this class sets, via setters in
     * class ChessComponent, game mode and Players to appropriate types.
     */
    public class PVPListener implements ActionListener
    {
       Board board;

       public PVPListener(final Board board) {
           this.board = board;
       }

       public void actionPerformed(final ActionEvent e) {
             ChessComponent.setGameMode(Mode.PVP);
         board.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
          }
       }

    /**
     * Listener set in the Menus class.
     * If option Quit is chosen by player this class opens a
     * dialog window where the player then can choose to exit the game or continue playing.
     */
    public class QuitListener implements ActionListener
    {
        private final ChessFrame chessFrame;
        public QuitListener (ChessFrame frame){
            chessFrame = frame;
        }

          public void actionPerformed(ActionEvent e) {
              int answer = JOptionPane.showConfirmDialog(chessFrame.frame, "Are you sure?", "Quit?",
                                               JOptionPane.YES_NO_OPTION);
              if (answer == JOptionPane.YES_OPTION) {
                 System.exit(0);
              }
          }
       }

    /**
     * Listener set in the Menus class.
     * If option Reset is chosen by player this class resets the game by calling methods via ChessFrame.
     */
    public final class ResetListener implements ActionListener
    {
        private final ChessFrame chessFrame;
        private final JRadioButtonMenuItem internalPvp;
       Board board;

          public ResetListener(JRadioButtonMenuItem pvp, ChessFrame frame, final Board board) {
             this.board = board;
             internalPvp = pvp;
         chessFrame = frame;
          }

          public void actionPerformed(final ActionEvent e) {
             // reset the board to starting state and the player mode to PVP
            chessFrame.chessComponent.pauseTimer();
         ChessComponent.setGameMode(Mode.PVP);
         board.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
         chessFrame.board.setStartPositions();
            internalPvp.setSelected(true);
         chessFrame.chessComponent.setClickedPieceNull();
         chessFrame.repaint();
         chessFrame.board.setTurnCounterToZero();
         chessFrame.chessComponent.resumeTimer();

          }
       }

    /**
     * Listener set in the Menus class.
     * If option Save is chosen by player this class saves the current game by save method in SaveAndLoad.
     * A saved game can then be loaded by calling the LoadListener.
     */
    public class SaveListener implements ActionListener
    {
        private final Board board;

        public SaveListener(Board board) {
       this.board = board;
        }

        public void actionPerformed(final ActionEvent e) {
       SaveAndLoad.save(board);
        }
    }
}