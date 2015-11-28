package controls;

import java.time.Duration;

public abstract class InstantEvent implements Event{
	private boolean completed = false;
	
	@Override
	public boolean hasCompleted() {
		return completed;
	}

	@Override
	public void stop() {
		
	}

	@Override
	public Duration getDuration() {
		return Duration.ZERO;
	}
	
	@Override
	public void start() {
		executeEvent();
		completed = true;
	}
	
	public abstract void executeEvent();
}
