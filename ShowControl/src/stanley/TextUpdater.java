package stanley;

import media.MessageTransmitter;
import media.screens.ScreenMessage;

public class TextUpdater {
	
	private MessageTransmitter transmitter;
	
	public TextUpdater(MessageTransmitter transmitter)
	{
		this.transmitter = transmitter;
	}
	
	public void updateText(String string)
	{
		String toSend = string.replace('\n', '|');
		
		transmitter.sendMessage(ScreenMessage.showText(toSend));
	}
}
