

package main;

import javax.swing.*;
import java.io.File;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Contains methods for saving and loading a board.
 */
public final class SaveAndLoad
{
    private SaveAndLoad() {}

    public static void save(Board board) {
	// used to save the current board to a file
	Component modalToComponent = null;
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
	    String saveName = fileChooser.getSelectedFile().getPath();
	    ObjectOutput out = null;
	    try {
		FileOutputStream fos = new FileOutputStream(saveName);
		out = new ObjectOutputStream(fos);
		out.writeObject(board);
	    }
	    catch (IOException e) {
		e.printStackTrace();
	    }
	    finally {
		try {
		    if (out != null) {
			out.close();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    public static Board load() {
	// used to load a board from a file
	Board board = null;
	Component modalToComponent = null;
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
	    // load from file
	    File file = fileChooser.getSelectedFile();
	    try {
		FileInputStream fis = new FileInputStream(file.getPath());
		ObjectInputStream in = new ObjectInputStream(fis);
		try {
		    board = (Board) in.readObject();
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
		in.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return board;
    }
}


