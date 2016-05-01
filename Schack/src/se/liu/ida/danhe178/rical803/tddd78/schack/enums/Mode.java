package se.liu.ida.danhe178.rical803.tddd78.schack.enums;

/**
 * Each mode represents a type of game mode
 *
 * Mode is set in respective listeners and value kept in ChessComponent.
 */
public enum Mode
{
    /**
     * PlayerVsPlayer
     */
    PVP,
    /**
     * PlayerVsAI
     */
    PVAI,
    /**
     * AIVsAI
     */
    AIVAI,
    /**
     * Pause game
     */
    PAUSE
}
