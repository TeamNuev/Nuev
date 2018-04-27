package ga.nullcraft.global.game.event;

/**
 * An interface for determining if an event can be cancelled.
 * 
 * @author TNuev
 *
 */
public interface ICancellable {
	
	/**
	 * Returns a boolean value that represents if event is cancelled.
	 * @return The cancelled state
	 */
    boolean isCancelled();

    /**
     * Sets the cancel state.
     * Throws a IllegalArgumentExceptionwhen this method is called by a uncancellable event 
     * @param flag New cancelled value
     */
    void setCancelled(boolean flag);
}
