package actionlisteners;

import main.ChessFrame;
import enums.Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If option Pause is chosen by player this class pauses the game by calling methods in ChessFrame.
 */
public class PauseListener implements ActionListener
{
    private ChessFrame chessFrame;
    public PauseListener (ChessFrame frame){
        chessFrame = frame;
    }
	public void actionPerformed(final ActionEvent e) {
	    chessFrame.chessComponent.setGameMode(Mode.PAUSE);
	}
    }
