package se.liu.ida.danhe178.rical803.tddd78.schack.main;

/**
 * Listener interface for a board.
 *
 * Used in class Board by method NotifyListeners to notify listeners
 * that a change has occurred and method boardChanged,
 * which is implemented in ChessComponent, and thus conduct a graphical repaint.
 */
interface BoardListener
{
    void boardChanged();
}
