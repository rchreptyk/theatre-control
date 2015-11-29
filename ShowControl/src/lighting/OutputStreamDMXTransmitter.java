package lighting;

import java.io.OutputStream;
import java.util.Formatter;
import java.util.Set;

public class OutputStreamDMXTransmitter implements DMXFrameTransmitter{
	
	private static int DMX_SIZE = 512;
	
	private Formatter formatter;
	
	public OutputStreamDMXTransmitter(OutputStream stream)
	{
		formatter = new Formatter(stream); 
	}
	
	@Override
	public void send(DMXFrame frame) {
		int[] int_frame = new int[DMX_SIZE]; 
		
		Set<DMXChannel> channels = frame.getChannels();
		
		for(DMXChannel channel : channels)
		{
			int level =  channel.getLevel();
			
			if(level > 256)
				throw new RuntimeException("Level cannot be greater then 256");
			
			if(level < 0)
				throw new RuntimeException("Level cannot be less then 256");
			
			int_frame[channel.getChannel()] = level;
		}
		
		for(int level : int_frame)
		{
			formatter.format(" %03d", level);
		}
		
		formatter.format("\n");
		formatter.flush();
	}
	
	public void close()
	{
		this.formatter.close();
	}

}
