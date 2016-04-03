package main;

import javax.swing.*;
import java.awt.*;

public class ChessComponent extends JComponent
{
    private final Board board;
    public static final int SQUARE_SIZE = 120;
    public ChessComponent(Board board) {
	this.board = board;
    }

    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;
	for (int y = 0; y < board.getHEIGHT(); y++) {
	    for (int x = 0; x < board.getWIDTH(); x++) {
		Color color = Color.WHITE;
		if (y % 2 == 0 && x % 2 == 1) {
		    color = Color.BLACK;
		}
		else if (y % 2 == 1 && x %  2 == 0) {
		    color = Color.BLACK;
		}
		g2d.setColor(color);

		int cornerX = x * SQUARE_SIZE ;
		int cornerY = y * SQUARE_SIZE;

		g2d.fillRect(cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE);

	    }
	}
    }

    @Override public Dimension getPreferredSize(){
    return new Dimension((board.getWIDTH())*SQUARE_SIZE,
                      (board.getHEIGHT())*SQUARE_SIZE);
      }

}
