package lighting.change;

import controls.InstantEvent;

public class LightingChangeEvent extends InstantEvent{

	public LightingChange change;
	
	public LightingChangeEvent(LightingChange change)
	{
		this.change = change;
	}

	@Override
	public void executeEvent() {
		change.execute();
	}

}
