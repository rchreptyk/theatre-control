package media.sound;

public interface Sound {
	/**
	 * A unique name that identifies the sound playing among others
	 * @return
	 */
	public String getName();
	
	/**
	 * Return a string that locates the sound required
	 * @return
	 */
	public String getSoundLocator();
}
