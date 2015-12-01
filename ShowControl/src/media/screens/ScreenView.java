package media.screens;

import controls.Action;
import controls.InstantAction;
import controls.View;
import media.MessageTransmitter;

public class ScreenView implements View {
	MessageTransmitter transmitter;
	Screen screen;
	Video video;
	
	public ScreenView(MessageTransmitter transmitter, Screen screen, Video video)
	{
		this.transmitter = transmitter;
		this.screen = screen;
		this.video = video;
	}

	@Override
	public Action render() {
		ScreenMessage message = ScreenMessage.showMedia(screen, video);
		transmitter.sendMessage(message);
		return InstantAction.ACTION;
	}
}
