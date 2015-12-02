package lighting.intruments;

import lighting.Scroller;
import lighting.intruments.colorfusion.ColorFusionLight;

public abstract class LightingConfiguration {
	private InstrumentCollection collection = new InstrumentCollection();
	
	protected BasicLight addBasicLight(String name, int channel)
	{
		return addBasicLight(name, channel, 0);
	}
	
	protected BasicLight addBasicLight(String name, int channel, int initialIntensity)
	{
		BasicLight light = new BasicLight(name, channel, initialIntensity);
		collection.addInstrument(light);
		
		return light;
	}
	
	protected ColorFusionLight addColorFusionLight(BasicLight basicLight, Scroller scroller1, Scroller scroller2)
	{
		ColorFusionLight light = new ColorFusionLight(basicLight, scroller1, scroller2);
		collection.addInstrument(light);
		
		return light;
	}
	
	public InstrumentCollection getCollection()
	{
		return collection;
	}
}
