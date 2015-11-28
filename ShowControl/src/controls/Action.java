package controls;

public interface Action {
	/**
	 * Whether the action has finished completing. Some actions
	 * may never stop (e.g. an action on a loop)
	 * @return Whether or not the action has completed.
	 */
	boolean hasCompleted();
	
	/**
	 * Stopping performing the operation. This must allow another view to take over.
	 */
	void stop();
}
