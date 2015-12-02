package lighting.change;

import java.util.ArrayList;
import java.util.List;

import lighting.Position;

public class PositionChange implements LightingChange {

	private Position position;
	private int newPosition;
	
	public PositionChange(Position position, int newPosition)
	{
		this.position = position;
		this.newPosition = newPosition;
	}
	
	@Override
	public void execute() {
		this.position.setPosition(newPosition);
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
		
		ChangeCalculator calculator = new ChangeCalculator(position.getPosition(), newPosition);
		for(int change : calculator.getChanges(count))
		{
			changes.add(new PositionChange(position, change));
		}
		
		return changes;
	}

}
