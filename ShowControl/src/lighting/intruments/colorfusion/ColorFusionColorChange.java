package lighting.intruments.colorfusion;

import java.util.ArrayList;
import java.util.List;

import lighting.change.CompoundLightingChange;
import lighting.change.LightingChange;
import lighting.change.PositionChange;

public class ColorFusionColorChange implements LightingChange {
	ColorFusionLight light;
	ColorFusionColor color;
	
	public ColorFusionColorChange(ColorFusionLight light, ColorFusionColor color)
	{
		this.light = light;
		this.color = color;
	}

	@Override
	public void execute() {
		PositionChange positionChange = new PositionChange(light.getFirstScroller(), color.getPosition1());
		PositionChange positionChange2 = new PositionChange(light.getSecondScroller(), color.getPosition2());

		positionChange.execute();
		positionChange2.execute();
	}

	@Override
	public List<LightingChange> getIntermediateChanges(int count) {
		PositionChange positionChange = new PositionChange(light.getFirstScroller(), color.getPosition1());
		PositionChange positionChange2 = new PositionChange(light.getSecondScroller(), color.getPosition2());
		
		List<LightingChange> changeList = new ArrayList<LightingChange>();
		
		List<LightingChange> changes1 = positionChange.getIntermediateChanges(count);
		List<LightingChange> changes2 = positionChange2.getIntermediateChanges(count);
		
		if(changes1.size() != count)
			throw new RuntimeException("Incorrect number of changes returned position 1");
		
		if(changes2.size() != count)
			throw new RuntimeException("Incorrect number of changes returned position 2");
		
		for(int i = 0; i < count; i++)
		{
			CompoundLightingChange compound = new CompoundLightingChange();
			compound.add(changes1.get(i));
			compound.add(changes2.get(i));
			
			changeList.add(compound);
		}
		
		return changeList;
	}
}
