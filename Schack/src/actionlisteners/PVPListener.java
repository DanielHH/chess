package actionlisteners;

import main.ChessFrame;
import enums.Mode;
import enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If mode PVP is chosen by player, via menu-methods in class ChessFrame,
 * this class sets gamemode, via setters in class ChessComponent, and Players to appropriate Mode-types.
 */
public class PVPListener implements ActionListener
{
    private ChessFrame chessFrame;
    public PVPListener (ChessFrame frame){
        chessFrame = frame;
    }
      public void actionPerformed(final ActionEvent e) {
         chessFrame.chessComponent.setGameMode(Mode.PVP);
	  chessFrame.chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
      }
   }
