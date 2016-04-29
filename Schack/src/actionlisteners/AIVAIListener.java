package actionlisteners;

import main.ChessFrame;
import enums.Mode;
import enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If mode AIVAI is chosen by player, via menu-methods in class ChessFrame,
 * this class sets, via setters in class ChessComponent, gamemode and Players to appropriate Mode-types.
 */
public class AIVAIListener implements ActionListener
{
    private ChessFrame chessFrame;
    public AIVAIListener (ChessFrame frame){
        chessFrame = frame;
    }
         public void actionPerformed(final ActionEvent e) {
	     chessFrame.chessComponent.setGameMode(Mode.AIVAI);
	     chessFrame.chessComponent.setPlayers(PlayerType.AI, PlayerType.AI);
         }
      }
