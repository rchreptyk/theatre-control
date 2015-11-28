package media;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageTransmitter {
	private PrintWriter writer;
	
	public MessageTransmitter(OutputStream messageStream)
	{
		this.writer = new PrintWriter(messageStream);
	}
	
	public synchronized void sendMessage(MediaMessage message)
	{
		writer.println(message.getMessage());
		writer.flush();
	}
}
