package actionlisteners;

import main.ChessComponent;
import enums.Mode;
import enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode PVAI is chosen by player this class sets, via setters in
 * class ChessComponent, gamemode and Players to appropriate types.
 */
public class PVAIListener implements ActionListener
{
         public void actionPerformed(final ActionEvent e) {
	     ChessComponent.setGameMode(Mode.PVAI);
	     ChessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
      }
