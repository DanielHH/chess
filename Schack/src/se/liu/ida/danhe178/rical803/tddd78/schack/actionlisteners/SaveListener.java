package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.main.Board;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If option Save is chosen by player this class saves the current game by save method in SaveAndLoad.
 * A saved game can then be loaded by calling the LoadListener.
 */
public class SaveListener implements ActionListener
{
    private final Board board;

    public SaveListener(Board board) {
	this.board = board;
    }

    public void actionPerformed(final ActionEvent e) {
	SaveAndLoad.save(board);
    }
}
