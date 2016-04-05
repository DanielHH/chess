package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;

public class ChessComponent extends JComponent
{
    private Board board;
    public static final int SQUARE_SIZE = 120;
    private Piece currentPiece;

    public ChessComponent(Board board) {
	this.board = board;

	addMouseListener(new MouseAdapter() {
     		public void mouseClicked(MouseEvent e) {
       		System.out.println(e);
		    int x=e.getX();
		    int y=e.getY();
		    int column = x / SQUARE_SIZE;
		    int row = y / SQUARE_SIZE;
		    System.out.println(column);
		    System.out.println(row);
		    if (currentPiece == null) {
			currentPiece = board.getPiece(column, row);
		    }
		    else {
			System.out.println("kjhkjhkjh");
			currentPiece.move(column, row);
			repaint();
			currentPiece = null;
		    }
     		}
	});
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

		Piece currentPiece = board.getPiece(x,y);
		// kolla efter pjäs och rita upp
		if ( currentPiece != null) {
		    g.drawImage(currentPiece.getImage(), cornerX, cornerY, SQUARE_SIZE, SQUARE_SIZE, null);
		}
	    }
	}
    }

    @Override public Dimension getPreferredSize(){
    return new Dimension((board.getWIDTH())*SQUARE_SIZE,
                      (board.getHEIGHT())*SQUARE_SIZE);
      }

}
