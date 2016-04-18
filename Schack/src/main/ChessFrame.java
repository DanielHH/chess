package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChessFrame extends JFrame {
   private ChessComponent chessComponent;
   private JFrame frame;

   public ChessFrame(Board board) {
      super("Schack");
      this.setLayout(new BorderLayout());
      createMenus();

      chessComponent= new ChessComponent(board);
      board.addBoardListener(chessComponent);
      this.add(chessComponent);
      this.pack();
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      this.setVisible(true);
   }

   private void createMenus() {
      final JMenuBar menuBar = new JMenuBar();

      final JMenu options = new JMenu("Options");
      final JMenuItem start = new JMenuItem("Start game", 'S');
      final JMenuItem load = new JMenuItem("Load game", 'L');
      final JMenuItem save = new JMenuItem("Save", 'S');
      final JMenuItem reset = new JMenuItem("Reset", 'R');
      final JMenuItem quit = new JMenuItem("Quit", 'Q');

      final JMenu mode = new JMenu("Mode");
      final JMenuItem pvp = new JMenuItem("PvP");
      final JMenuItem pvai = new JMenuItem("PvAI");
      final JMenuItem aivai = new JMenuItem("AIvAI");
      final JMenuItem editor = new JMenuItem("Editor");

      quit.addActionListener(new ExitListener());
      options.add(start);
      options.add(load);
      options.add(save);
      options.add(reset);
      options.add(quit);
      menuBar.add(options);

      mode.add(pvp);
      mode.add(pvai);
      mode.add(aivai);
      mode.add(editor);
      menuBar.add(mode);
      this.setJMenuBar(menuBar);
   }

   private class ExitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) {
          int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Quit?",
                                                JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
             System.exit(0);
          }
      }
   }
}
