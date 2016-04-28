package actionlisteners;

import main.Board;
import main.SaveAndLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener
{
    private Board board;

    public SaveListener(Board board) {
	this.board = board;
    }

    public void actionPerformed(final ActionEvent e) {
	SaveAndLoad.save(board);
    }
}
