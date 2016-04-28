package actionlisteners;

import main.Board;
import main.ChessFrame;
import main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener
{
    private ChessFrame chessFrame;
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
