package main;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Class is utility class that contains methods for saving and loading an instance of a Board object.
 *
 * Comments on Static methods save and load:
 * Methods are only affected by input data.
 * Methods are accessed without instantiating the class.
 * And lastly the methods are referenced from a static context
 * thus the requirement of having it in a global (existing) state.
 */
public final class SaveAndLoad {
    private SaveAndLoad() {}

    /**
     * Used to save the current board to a file
     * @param board current board
     */
    public static void save(Board board) {
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	    String saveName = fileChooser.getSelectedFile().getPath();
	    try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(saveName))){
		out.writeObject(board);
	    }
	    catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Used to load a board from a file
     * @return saved board which is chosen to be loaded in
     */
    public static Board load() {
	Board board = null;
	JFileChooser fileChooser = new JFileChooser();
	if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    // load from file
	    File file = fileChooser.getSelectedFile();
	    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getPath()))){
		    board = (Board) in.readObject();
	    } catch (ClassNotFoundException | IOException e) {
		e.printStackTrace();
	    }
	}
	return board;
    }
}


