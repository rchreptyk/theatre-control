package lighting.intruments;

public abstract class LightingConfiguration {
	private InstrumentCollection collection = new InstrumentCollection();
	
	protected BasicLight addBasicLight(String name, int channel)
	{
		BasicLight light = new BasicLight(name, channel);
		collection.addInstrument(light);
		
		return light;
	}
	
	public InstrumentCollection getCollection()
	{
		return collection;
	}
}
