package enums;

/**
 * Each mode represents a type of gamemode
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
