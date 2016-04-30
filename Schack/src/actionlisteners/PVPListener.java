package actionlisteners;

import main.ChessComponent;
import enums.Mode;
import enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode PVP is chosen by player this class sets, via setters in
 * class ChessComponent, game mode and Players to appropriate types.
 */
public class PVPListener implements ActionListener
{
      public void actionPerformed(final ActionEvent e) {
         ChessComponent.setGameMode(Mode.PVP);
	  ChessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
      }
   }
