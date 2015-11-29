package media;

public class SoundViewFactory {
	private MessageTransmitter transmitter;
	
	public SoundViewFactory(MessageTransmitter transmitter)
	{
		this.transmitter = transmitter;
	}
	
	public SoundView createSoundView(Sound sound, int volume)
	{
		return new SoundView(transmitter, sound, volume);
	}
}
