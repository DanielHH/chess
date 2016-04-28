package actionlisteners;

import main.ChessFrame;
import main.Mode;
import main.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If mode PVP is chosen by player this class sets gamemode and Players to appropriate types.
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
