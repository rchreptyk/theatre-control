package media.sound;

import java.time.Duration;

import controls.Action;
import controls.View;
import media.MessageTransmitter;

/**
 * A view that plays a sound at a specified volume
 * @author russell
 *
 */
public class SoundView implements View {

	private MessageTransmitter transmitter;
	private Sound sound;
	private int volume;
	private Duration offset;
	
	public SoundView(MessageTransmitter transmitter, Sound sound, int volume, Duration offset)
	{
		this.transmitter = transmitter;
		this.sound = sound;
		this.offset = offset;
		
		if(volume < 0 || volume > 100)
			throw new RuntimeException("Invalid volume of " + volume);
		
		this.volume = volume;
	}
	
	public SoundView(MessageTransmitter transmitter, Sound sound, int volume)
	{
		this(transmitter, sound, volume, Duration.ZERO);
	}
	
	@Override
	public Action render() {
		transmitter.sendMessage(SoundMessage.playSound(sound.getName(), sound.getSoundLocator(), volume, offset));
		return new SoundAction(transmitter, sound);
	}

}
