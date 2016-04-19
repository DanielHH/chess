package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChessFrame extends JFrame {
   public ChessComponent chessComponent;
   private JFrame frame;
   private Mode gameMode = Mode.PVP;

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

      start.addActionListener(new StartListener());
      quit.addActionListener(new QuitListener());
      options.add(start);
      options.add(load);
      options.add(save);
      options.add(reset);
      options.add(quit);
      menuBar.add(options);

      pvp.addActionListener(new PvpListener());
      pvai.addActionListener(new PvaiListener());
      aivai.addActionListener(new AivaiListener());
      editor.addActionListener(new EditorListener());
      mode.add(pvp);
      mode.add(pvai);
      mode.add(aivai);
      mode.add(editor);
      menuBar.add(mode);
      this.setJMenuBar(menuBar);
   }

   private class StartListener implements ActionListener {
      public void actionPerformed(final ActionEvent e) {
         if (gameMode == Mode.PVP) {
            chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
         }
         else if(gameMode == Mode.PVAI) {
            chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
         else if (gameMode == Mode.AIVAI) {
            chessComponent.setPlayers(PlayerType.AI, PlayerType.AI);
         }
         else if(gameMode == Mode.EDITOR) {
            chessComponent.setPlayers(PlayerType.EDITOR, PlayerType.EDITOR);
         }
      }
   }

   private class QuitListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          int answer = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Quit?",
                                                JOptionPane.YES_NO_OPTION);
          if (answer == JOptionPane.YES_OPTION) {
             System.exit(0);
          }
      }
   }

   private class PvpListener implements ActionListener {
      public void actionPerformed(final ActionEvent e) {
         gameMode = Mode.PVP;
      }
   }

   private class PvaiListener implements ActionListener {
         public void actionPerformed(final ActionEvent e) {
            gameMode = Mode.PVAI;
         }
      }

   private class AivaiListener implements ActionListener {
         public void actionPerformed(final ActionEvent e) {
            gameMode = Mode.AIVAI;
         }
      }

   private class EditorListener implements ActionListener {
         public void actionPerformed(final ActionEvent e) {
            gameMode = Mode.EDITOR;
         }
      }
}
