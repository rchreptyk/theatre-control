package lighting.intruments;

import java.util.HashSet;
import java.util.Set;

import lighting.DMXChannel;
import lighting.Scroller;

public class BasicScroller implements Scroller, DMXChannel {

	int dmxChannel;
	int scrollerPosition = 0;
	String name;
	
	public BasicScroller(String name, int decoderDmxChannel, int scrollerChannel)
	{
		/* A DMX decoder listening on 160 with a scroller channel of 2 will listen on 161. Channels start at 1, not 0 */
		dmxChannel = decoderDmxChannel + (scrollerChannel - 1);
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<DMXChannel> getLevels() {
		HashSet<DMXChannel> set = new HashSet<DMXChannel>();
		set.add(this);
		return set;
	}

	@Override
	public void setPosition(int position) {
		if(position < 0 || position > 255)
		{
			throw new RuntimeException("Position of " + position + " invalid, must be 0-255");
		}
		
		this.scrollerPosition = position;
	}

	@Override
	public int getPosition() {
		return scrollerPosition;
	}

	@Override
	public int getLevel() {
		return scrollerPosition;
	}

	@Override
	public int getChannel() {
		return dmxChannel;
	}

}
