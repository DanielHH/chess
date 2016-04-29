

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
 *
 * Comments on Static methods: Methods are only affected by input data.
 * 	Methods are accessed without instantiating the class.
 */
public final class SaveAndLoad
{
    private SaveAndLoad() {}

    /**
     * Used to save the current board to a file
     * @param board current board
     */
    public static void save(Board board) {
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

    /**
     * Used to load a board from a file
     * @return saved board which is chosen to be loaded in
     */
    public static Board load() {
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


