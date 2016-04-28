package actionlisteners;

import main.ChessFrame;
import main.Mode;
import main.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PVAIListener implements ActionListener
{
    ChessFrame chessFrame;
    public PVAIListener (ChessFrame frame){
        chessFrame = frame;
    }
         public void actionPerformed(final ActionEvent e) {
	     chessFrame.chessComponent.setGameMode(Mode.PVAI);
	     chessFrame.chessComponent.setPlayers(PlayerType.PLAYER, PlayerType.AI);
         }
      }
