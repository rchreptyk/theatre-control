package lighting.intruments;

import java.util.HashSet;
import java.util.Set;

import lighting.DMXChannel;
import lighting.Intensity;

public class BasicLight implements Instrument, Intensity, DMXChannel {

	private String name;
	private Set<DMXChannel> resultSet = new HashSet<>();
	
	private int channel;
	private int intensity;
	
	public BasicLight(String name, int channel)
	{
		this(name, channel, 0);
	}
	
	public BasicLight(String name, int channel, int initialIntensity)
	{
		this.channel = channel;
		this.name = name;
		resultSet.add(this);
		
		this.setIntensity(initialIntensity);
	}
	
	@Override
	public Set<DMXChannel> getLevels() {
		return resultSet;
	}

	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the intensity of the light from 0 - 100
	 * @param intensity the intensity of the light from 0 - 100
	 */
	public void setIntensity(int intensity)
	{
		if(intensity < 0 || intensity > 100)
			throw new RuntimeException("Intensity must be between 0 and 100");
		
		this.intensity = intensity;
	}
	
	public int getIntensity()
	{
		return intensity;
	}

	@Override
	public int getLevel() {
		return 256 * intensity / 100;
	}

	@Override
	public int getChannel() {
		return channel;
	}

}
