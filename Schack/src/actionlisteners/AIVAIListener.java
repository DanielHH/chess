package actionlisteners;

import enums.Mode;
import enums.PlayerType;
import main.ChessComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode AIVAI is chosen by player this class sets, via setters in
 * class ChessComponent, gamemode and Players to appropriate types.
 */
public class AIVAIListener implements ActionListener
{
         public void actionPerformed(final ActionEvent e) {
	     ChessComponent.setGameMode(Mode.AIVAI);
	     ChessComponent.setPlayers(PlayerType.AI, PlayerType.AI);
         }
      }
