package lighting;

import controls.InstantEvent;

public class LightingLoopUpdateEvent extends InstantEvent {

	private LightingLoop loop;
	
	public LightingLoopUpdateEvent(LightingLoop loop)
	{
		this.loop = loop;
	}
	
	@Override
	public void executeEvent() {
		loop.update();
	}

}
