package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessComponent;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Mode;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode PVAI is chosen by player this class sets, via setters in
 * class ChessComponent, game mode and Players to appropriate types.
 */
public class PVAIListener implements ActionListener
{
         public void actionPerformed(final ActionEvent e) {
	     ChessComponent.setGameMode(Mode.PVAI);
	     ChessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
      }
