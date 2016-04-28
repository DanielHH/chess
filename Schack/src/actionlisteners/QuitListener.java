package actionlisteners;

import main.ChessFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitListener implements ActionListener
{
    ChessFrame chessFrame;
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
