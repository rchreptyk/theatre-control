package media.screens;

import controls.Action;
import controls.InstantAction;
import controls.View;
import media.MessageTransmitter;

public class ScreenBlackoutView implements View {

	MessageTransmitter transmitter;
	Screen screen;
	
	public ScreenBlackoutView(MessageTransmitter transmitter, Screen screen)
	{
		this.transmitter = transmitter;
		this.screen = screen;
	}

	@Override
	public Action render() {
		ScreenMessage message = ScreenMessage.blackout(screen);
		transmitter.sendMessage(message);
		return InstantAction.ACTION;
	}

}
