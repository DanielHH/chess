package se.liu.ida.danhe178.rical803.tddd78.schack.main;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    // private static because there need only to be one logger per class
    private static final Logger LOG = Logger.getLogger(SaveAndLoad.class.getName());

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
		LOG.log(Level.SEVERE, "IOException while saving board.", e );
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
		LOG.log(Level.SEVERE, e + " error while loading board.", e );
	    }
	}
	return board;
    }
}


