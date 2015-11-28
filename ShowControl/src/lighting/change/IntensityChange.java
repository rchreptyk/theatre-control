package lighting.change;

import java.util.ArrayList;
import java.util.List;

import lighting.Intensity;

public class IntensityChange implements LightingChange {

	private Intensity intensity;
	private int newIntensity;
	
	public IntensityChange(Intensity intensity, int newIntensity)
	{
		this.intensity = intensity;
		this.newIntensity = newIntensity;
	}
	
	@Override
	public void execute() {
		intensity.setIntensity(newIntensity);
	}

	@Override
	public List<LightingChange> getIntermediateChanges(int count) {
		
		if(count < 1)
			throw new RuntimeException("The change count must be 1 or more");
		
		List<LightingChange> changes = new ArrayList<>();
		
		if(count == 1)
		{
			changes.add(this);
			return changes;
		}
		
		int oldIntensity = intensity.getIntensity();
		int difference = newIntensity - oldIntensity;
		
		double intensityPerTick = (double)difference / (double)count;
		double nextIntensity = oldIntensity + intensityPerTick;
	
		for(int i = 0; i < count - 1; i++)
		{
			int roundedIntensity = (int) Math.round(nextIntensity);
			changes.add(new IntensityChange(intensity, roundedIntensity));
			nextIntensity += intensityPerTick;
		}
		
		changes.add(this);
		return changes;
	}

}
