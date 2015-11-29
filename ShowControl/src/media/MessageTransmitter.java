package media;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageTransmitter implements AutoCloseable {
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

	@Override
	public void close(){
		writer.close();
	}
}
