package media;

public class ScreenMessage implements MediaMessage {

	private String message;
	
	private ScreenMessage(String message)
	{
		this.message = message;
	}
	
	public static ScreenMessage blackout(Screen screen)
	{
		//TODO: Create screen message
		return new ScreenMessage(null);
	}
	
	public static ScreenMessage showMedia(Screen screen, Video video)
	{
		StringBuilder builder = new StringBuilder("screen");
		builder.append(" ");
		
		builder.append(screen.getName());
		builder.append(" ");
		
		builder.append("play");
		builder.append(" ");
		
		builder.append("\"");
		builder.append(video.getVideoMessage());
		builder.append("\"");
			
		return new ScreenMessage(builder.toString());
	}
	
	
	@Override
	public String getMessage() {
		return message;
	}

}
