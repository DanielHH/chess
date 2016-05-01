package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
          int answer = JOptionPane.showConfirmDialog(chessFrame.getFrame(), "Are you sure?", "Quit?",
						     JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
             System.exit(0);
          }
      }
   }
