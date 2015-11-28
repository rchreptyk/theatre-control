package media;

public class ScreenViewFactory {
	private MessageTransmitter transmitter;
	
	public ScreenViewFactory(MessageTransmitter transmitter)
	{
		this.transmitter = transmitter;
	}
	
	public ScreenView createScreenView(Screen screen, Video video)
	{
		return new ScreenView(transmitter, screen, video);
	}
}
