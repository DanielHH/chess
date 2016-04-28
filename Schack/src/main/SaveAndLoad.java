

package main;

import javax.swing.*;
import java.io.File;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Contains methods for saving and loading a board.
 */
public final class SaveAndLoad
{
    private SaveAndLoad() {}

    public static void save(Board board) {
	Component modalToComponent = null;
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
	    String saveName = fileChooser.getSelectedFile().getPath();

	    FileOutputStream fos;
	    ObjectOutputStream out;
	    try {
		fos = new FileOutputStream(saveName);
		out = new ObjectOutputStream(fos);
		out.writeObject(board);
		out.close();
	    }
	    catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

    public static Board load() {
	Board board = null;
	Component modalToComponent = null;
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
	    // load from file
	    File file = fileChooser.getSelectedFile();
	    FileInputStream fis;
	    ObjectInputStream in;
	    try {
		fis = new FileInputStream(file.getPath());
		in = new ObjectInputStream(fis);
		board = (Board) in.readObject();
		in.close();
	    }
	    catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	return board;
    }
}


