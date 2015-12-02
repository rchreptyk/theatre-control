package lighting.intruments.colorfusion;

import java.util.HashSet;
import java.util.Set;

import lighting.DMXChannel;
import lighting.Scroller;
import lighting.intruments.BasicLight;
import lighting.intruments.Instrument;

public class ColorFusionLight implements Instrument {
	BasicLight light;
	
	private Scroller scroller1;
	private Scroller scroller2;

	public ColorFusionLight(BasicLight light, Scroller firstScroller, Scroller secondScroller)
	{
		this.light = light;
		
		this.scroller1 = firstScroller;
		this.scroller2 = secondScroller;
	}

	public Scroller getFirstScroller()
	{
		return scroller1;
	}
	
	public Scroller getSecondScroller()
	{
		return scroller2;
	}

	@Override
	public String getName() {
		return light.getName();
	}

	@Override
	public Set<DMXChannel> getLevels() {
		HashSet<DMXChannel> channels = new HashSet<DMXChannel>();
		channels.addAll(light.getLevels());
		channels.addAll(scroller1.getLevels());
		channels.addAll(scroller2.getLevels());
		return channels;
	}
}
