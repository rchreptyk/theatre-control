package lighting.change;

import java.util.ArrayList;
import java.util.List;

public class CompoundLightingChange implements LightingChange {
	ArrayList<LightingChange> changes = new ArrayList<LightingChange>();
	
	public void add(LightingChange lightingChange)
	{
		changes.add(lightingChange);
	}


	@Override
	public void execute() {
		for(LightingChange change : changes)
		{
			change.execute();
		}
	}

	@Override
	public List<LightingChange> getIntermediateChanges(int count) {
		CompoundLightingChange[] changes = new CompoundLightingChange[count];
		
		for(int i = 0; i < count; i++)
		{
			changes[i] = new CompoundLightingChange();
		}
		
		for(LightingChange change : changes)
		{
			List<LightingChange> subChanges = change.getIntermediateChanges(count);
			
			if(subChanges.size() != count)
				throw new RuntimeException("Invalid number of changes returned");
			
			for(int i = 0; i < count; i++)
			{
				changes[i].add(subChanges.get(i));
			}
		}
		
		ArrayList<LightingChange> changeList = new ArrayList<LightingChange>(count);
		
		for(CompoundLightingChange change : changes)
		{
			changeList.add(change);
		}
		 
		return changeList;
	}
}
