package media.sound;

import java.util.logging.Logger;

import controls.Action;
import controls.InstantAction;
import controls.View;
import media.MessageTransmitter;

public class SoundVolumeView implements View {
	private static Logger LOG = Logger.getLogger("SoundVolumeView");
	
	private Sound sound;
	private MessageTransmitter transmitter;
	private int volume;
	
	public SoundVolumeView(MessageTransmitter transmitter, Sound sound, int volume)
	{
		this.transmitter = transmitter;
		this.sound = sound;
		this.volume = volume;
	}

	@Override
	public Action render() {
		LOG.info("Sending volume decrease to " + volume);
		transmitter.sendMessage(SoundMessage.setVolume(sound.getName(), volume));
		return InstantAction.ACTION;
	}
}
