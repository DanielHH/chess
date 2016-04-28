package actionlisteners;

import main.Board;
import main.ChessFrame;
import main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If option Load is chosen by player this class loads a saved board with methods in ChessFrame.
 */
public class LoadListener implements ActionListener
{
    private ChessFrame chessFrame;
    public LoadListener (ChessFrame frame){
        chessFrame = frame;
    }
       public void actionPerformed(ActionEvent e) {
          Board loadedBoard = SaveAndLoad.load();
          if (loadedBoard != null) {
              System.out.println("board found");
              chessFrame.board.setLoadedBoard(loadedBoard);
              chessFrame.chessComponent.setClickedPieceNull();
              chessFrame.repaint();
          }
       }
    }
