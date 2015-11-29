package media;

public class SoundMessage implements MediaMessage {

	private String message;
	
	private SoundMessage(String message)
	{
		this.message = message;
	}
	
	public static SoundMessage playSound(String name, String path, int volume)
	{
		StringBuilder message = new StringBuilder("sound play");
		message.append(" ");
		
		message.append(name);
		message.append(" ");
		
		message.append(volume);
		message.append(" ");
		
		message.append(path);
		
		return new SoundMessage(message.toString());
	}
	
	public static SoundMessage setVolume(String name, int volume)
	{
		StringBuilder message = new StringBuilder("sound volume");
		message.append(" ");
		
		message.append(name);
		message.append(" ");
		
		message.append(volume);
		
		return new SoundMessage(message.toString());
	}
	
	public static SoundMessage stop(String name)
	{
		return new SoundMessage("sound stop " + name);
	}
	
	@Override
	public String getMessage()
	{
		return message;
	}

}
