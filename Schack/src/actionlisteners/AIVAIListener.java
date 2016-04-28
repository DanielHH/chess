package actionlisteners;

import main.ChessFrame;
import main.Mode;
import main.PlayerType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
