package actionlisteners;

import main.ChessComponent;
import enums.Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If mode Pause is chosen by player this class sets, via setters in
 * class ChessComponent, game mode to appropriate type.
 */
public class PauseListener implements ActionListener
{
	public void actionPerformed(final ActionEvent e) {
	    ChessComponent.setGameMode(Mode.PAUSE);
	}
    }
