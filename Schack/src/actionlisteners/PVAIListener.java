package actionlisteners;

import main.ChessFrame;
import enums.Mode;
import enums.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * If mode PVAI is chosen by player this class sets gamemode and Players to appropriate types.
 */
public class PVAIListener implements ActionListener
{
    private ChessFrame chessFrame;
    public PVAIListener (ChessFrame frame){
        chessFrame = frame;
    }
         public void actionPerformed(final ActionEvent e) {
	     chessFrame.chessComponent.setGameMode(Mode.PVAI);
	     chessFrame.chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
      }
