package lighting;

public interface DMXChannel {
	/**
	 * Gets the level of the channel from 0-254
	 * @return
	 */
	int getLevel();
	
	/**
	 * Get the DMX channel number
	 * @return
	 */
	int getChannel();
}
