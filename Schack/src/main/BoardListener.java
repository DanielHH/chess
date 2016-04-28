package main;

/**
 * Listener interface for a board.
 */
public interface BoardListener
{
    public void boardChanged() throws InterruptedException;
}
