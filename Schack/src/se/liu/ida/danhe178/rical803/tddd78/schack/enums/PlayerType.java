package se.liu.ida.danhe178.rical803.tddd78.schack.enums;

/**
 * Each type represent a type of player
 *
 * From start PlayerType is the default PlayerType kept in ChessComponent class. Change in PlayerType can then be triggered by
 * AIVAIListener, PVAIListener, PVPListener and ResetListener.
 */
public enum PlayerType {
    /**
     * Human player
     */
    PLAYER,
    /**
     * AI player. Name too short, but descriptive enough.
     */
    AI
}