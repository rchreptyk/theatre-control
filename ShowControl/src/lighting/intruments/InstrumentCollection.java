package lighting.intruments;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lighting.DMXChannel;
import lighting.DMXFrame;

/**
 * Contains all instruments in a system. Instruments
 * cannot have overlapping channels.
 * @author russell
 *
 */
public class InstrumentCollection implements DMXFrame{
	
	private HashMap<String, Instrument> instruments = new HashMap<String, Instrument>();
	private HashSet<DMXChannel> channels = new HashSet<DMXChannel>();
	private HashSet<Integer> channelNumbers = new HashSet<>();
	
	public void addInstrument(Instrument instrument)
	{
		for(DMXChannel channel : instrument.getLevels())
		{
			int channelNumber = channel.getChannel();
			if(channelNumbers.contains(channelNumber))
				throw new RuntimeException("Cannot add duplicate DMXChannels to an instrument colleciton");
			
			channelNumbers.add(channelNumber);
			channels.add(channel);
		}
		
		if(instruments.containsKey(instrument.getName()))
			throw new RuntimeException("Cannot add intrument with duplicate type");
		
		instruments.put(instrument.getName(), instrument);
	}
	
	@Override
	public Set<DMXChannel> getChannels() {
		return channels;
	}
	
}
