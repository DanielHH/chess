package actionlisteners;

import main.ChessComponent;
import main.ChessFrame;
import enums.Mode;
import enums.PlayerType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If option Reset is chosen by player this class resets the game by calling methods via ChessFrame.
 */
public final class ResetListener implements ActionListener
{
    private ChessFrame chessFrame;
    private JRadioButtonMenuItem internalPvp;

      public ResetListener(JRadioButtonMenuItem pvp, ChessFrame frame) {
         internalPvp = pvp;
	  chessFrame = frame;
      }

      public void actionPerformed(final ActionEvent e) {
         // reset the board to starting state and the player mode to PVP
	     chessFrame.pauseTimer();
	  ChessComponent.setGameMode(Mode.PVP);
	  ChessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
	  chessFrame.board.setStartPositions();
	     internalPvp.setSelected(true);
	  chessFrame.chessComponent.setClickedPieceNull();
	  chessFrame.repaint();
	  chessFrame.board.setTurnCounterToZero();
	  chessFrame.resumeTimer();

      }
   }
