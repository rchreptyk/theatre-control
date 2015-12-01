package media.sound;

import controls.View;
import controls.Action;
import controls.InstantAction;
import media.MessageTransmitter;

public class SoundHaltView implements View {
	private MessageTransmitter transmitter;
	private Sound sound;
	
	public SoundHaltView(MessageTransmitter transmitter, Sound sound)
	{
		this.transmitter = transmitter;
		this.sound = sound;
	}
	
	@Override
	public Action render() {
		transmitter.sendMessage(SoundMessage.stop(sound.getName()));
		return InstantAction.ACTION;
	}

}
