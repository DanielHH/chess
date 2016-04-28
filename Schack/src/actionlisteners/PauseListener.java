package actionlisteners;

import main.ChessFrame;
import main.Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseListener implements ActionListener
{
    ChessFrame chessFrame;
    public PauseListener (ChessFrame frame){
        chessFrame = frame;
    }
	public void actionPerformed(final ActionEvent e) {
	    chessFrame.chessComponent.setGameMode(Mode.PAUSE);
	}
    }
