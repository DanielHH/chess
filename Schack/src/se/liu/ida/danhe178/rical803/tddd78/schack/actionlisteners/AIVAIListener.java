package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Mode;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PlayerType;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode AIVAI is chosen by player this class sets, via setters in
 * class ChessComponent, game mode and Players to appropriate types.
 */
public class AIVAIListener implements ActionListener
{
         public void actionPerformed(final ActionEvent e) {
	     ChessComponent.setGameMode(Mode.AIVAI);
	     ChessComponent.setPlayers(PlayerType.AI, PlayerType.AI);
         }
      }
