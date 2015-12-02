package controls;

import java.time.Duration;

public class WaitEvent implements Event {

	private boolean completed = false;
	
	private Duration duration;
	
	public WaitEvent(Duration duration)
	{
		this.duration = duration;
	}
	
	@Override
	public boolean hasCompleted() {
		return completed;
	}

	@Override
	public void stop() {
		completed = true;
	}

	@Override
	public Duration getDuration() {
		return duration;
	}

	@Override
	public void start() {
		completed = true;
	}

}
