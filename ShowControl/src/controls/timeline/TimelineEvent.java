package controls.timeline;

import java.time.Duration;

import controls.Event;

public class TimelineEvent implements Comparable<TimelineEvent>, Event {
	
	private Duration startTime;
	private Event event;
	
	public TimelineEvent(Event event, Duration startTime)
	{
		this.startTime = startTime;
		this.event = event;
	}
	
	public Event getEvent()
	{
		return event;
	}
	
	public Duration getStartTime()
	{
		return startTime;
	}

	@Override
	public int compareTo(TimelineEvent o) {
		return startTime.compareTo(o.startTime);
	}

	@Override
	public boolean hasCompleted() {
		return event.hasCompleted();
	}

	@Override
	public void stop() {
		event.stop();
	}

	@Override
	public Duration getDuration() {
		return event.getDuration();
	}

	@Override
	public void start() {
		event.start();
	}
}
