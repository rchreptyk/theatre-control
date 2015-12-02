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
		
		ChangeCalculator calculator = new ChangeCalculator(intensity.getIntensity(), newIntensity);
		for(int change : calculator.getChanges(count))
		{
			changes.add(new IntensityChange(intensity, change));
		}
		
		return changes;
	}

}
