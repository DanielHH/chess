package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChessFrame extends JFrame {
   private Board board;
   private ChessComponent chessComponent;
   private JFrame frame;

   public ChessFrame(Board board) {
      super("Schackspel");
      this.board = board;
      this.setLayout(new BorderLayout());
      createMenus();

      chessComponent= new ChessComponent(board);
      this.add(chessComponent);
      this.pack();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   private void createMenus() {
      final JMenuBar menuBar = new JMenuBar();
      final JMenu options = new JMenu("Options");
      final JMenuItem quit = new JMenuItem("Quit", 'Q');
      quit.addActionListener(new ExitListener());
      options.add(quit);
      menuBar.add(options);
      this.setJMenuBar(menuBar);
   }

   private class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) {
          int answer = JOptionPane.showConfirmDialog(frame, "Do you really wanna quit, n00b?", "Quit?",
                                                JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
             System.exit(0);
          }
      }
   }
}
