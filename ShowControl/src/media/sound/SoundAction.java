package media.sound;

import controls.Action;
import media.MessageTransmitter;

public class SoundAction implements Action {
	private MessageTransmitter transmitter;
	private Sound sound;
	
	private boolean completed = false;
	
	public SoundAction(MessageTransmitter transmitter, Sound sound)
	{
		this.transmitter = transmitter;
		this.sound = sound;
	}

	@Override
	public boolean hasCompleted() {
		return completed;
	}

	@Override
	public void stop() {
		transmitter.sendMessage(SoundMessage.stop(sound.getName()));
		completed = true;
	}
}
