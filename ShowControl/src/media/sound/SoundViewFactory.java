package media.sound;

import java.time.Duration;

import media.MessageTransmitter;

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
	
	public SoundView createSoundView(Sound sound, int volume, Duration offset)
	{
		return new SoundView(transmitter, sound, volume, offset);
	}
	
	public SoundHaltView createHaltView(Sound sound)
	{
		return new SoundHaltView(transmitter, sound);
	}
	
	public SoundFadeView createFade(Sound sound, int from, int to, Duration duration)
	{
		return new SoundFadeView(transmitter, sound, from, to, duration);
	}
}
