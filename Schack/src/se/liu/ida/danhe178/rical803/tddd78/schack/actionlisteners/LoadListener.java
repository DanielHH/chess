package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.main.Board;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessFrame;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
