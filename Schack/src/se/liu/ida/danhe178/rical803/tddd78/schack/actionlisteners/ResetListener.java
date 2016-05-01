package se.liu.ida.danhe178.rical803.tddd78.schack.actionlisteners;

import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessComponent;
import se.liu.ida.danhe178.rical803.tddd78.schack.main.ChessFrame;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.Mode;
import se.liu.ida.danhe178.rical803.tddd78.schack.enums.PlayerType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener set in the Menus class.
 * If option Reset is chosen by player this class resets the game by calling methods via ChessFrame.
 */
public final class ResetListener implements ActionListener
{
    private final ChessFrame chessFrame;
    private final JRadioButtonMenuItem internalPvp;

      public ResetListener(JRadioButtonMenuItem pvp, ChessFrame frame) {
         internalPvp = pvp;
	  chessFrame = frame;
      }

      public void actionPerformed(final ActionEvent e) {
         // reset the board to starting state and the player mode to PVP
	     chessFrame.chessComponent.pauseTimer();
	  ChessComponent.setGameMode(Mode.PVP);
	  ChessComponent.setPlayers(PlayerType.PLAYER, PlayerType.PLAYER);
	  chessFrame.board.setStartPositions();
	     internalPvp.setSelected(true);
	  chessFrame.chessComponent.setClickedPieceNull();
	  chessFrame.repaint();
	  chessFrame.board.setTurnCounterToZero();
	  chessFrame.chessComponent.resumeTimer();

      }
   }
