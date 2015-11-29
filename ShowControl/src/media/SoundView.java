package media;

import controls.Action;
import controls.View;

/**
 * A view that plays a sound at a specified volume
 * @author russell
 *
 */
public class SoundView implements View {

	private MessageTransmitter transmitter;
	private Sound sound;
	private int volume;
	
	public SoundView(MessageTransmitter transmitter, Sound sound, int volume)
	{
		this.transmitter = transmitter;
		this.sound = sound;
		
		if(volume < 0 || volume > 100)
			throw new RuntimeException("Invalid volume of " + volume);
		
		this.volume = volume;
	}
	
	@Override
	public Action render() {
		transmitter.sendMessage(SoundMessage.playSound(sound.getName(), sound.getSoundLocator(), volume));
		return new SoundAction(transmitter, sound);
	}

}
