package media.screens;

import media.MediaMessage;

public class ScreenMessage implements MediaMessage {

	private String message;
	
	private ScreenMessage(String message)
	{
		this.message = message;
	}
	
	public static ScreenMessage blackout(Screen screen)
	{
		StringBuilder builder = new StringBuilder("screen");
		builder.append(" ");
		
		builder.append(screen.getName());
		builder.append(" ");
		
		builder.append("blackout");

		return new ScreenMessage(builder.toString());
	}
	
	public static ScreenMessage showMedia(Screen screen, Video video)
	{
		StringBuilder builder = new StringBuilder("screen");
		builder.append(" ");
		
		builder.append(screen.getName());
		builder.append(" ");
		
		builder.append("play");
		builder.append(" ");
		
		builder.append(video.getVideoMessage());
			
		return new ScreenMessage(builder.toString());
	}
	
	public static ScreenMessage showText(String text)
	{
		StringBuilder builder = new StringBuilder("text");
		builder.append(" ");
		
		builder.append(text);
			
		return new ScreenMessage(builder.toString());
	}
	
	
	@Override
	public String getMessage() {
		return message;
	}

}
