package actionlisteners;

import main.Board;
import main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If option Save is chosen by player this class saves the current game by calling methods in ChessFrame.
 * A saved game can then be loaded by calling the LoadListener.
 */
public class SaveListener implements ActionListener
{
    private Board board;

    public SaveListener(Board board) {
	this.board = board;
	System.out.println(board + " in savelistener");
    }

    public void actionPerformed(final ActionEvent e) {
	SaveAndLoad.save(board);
    }
}
