package controls.timeline;

import java.time.Duration;

import controls.Event;

public class TimelineSequence {
	
	TimelineBuilder builder;
	Duration initialTime;
	
	Event previousEvent = null;
	
	TimelineSequence(TimelineBuilder builder, Duration initialTime)
	{
		this.initialTime = initialTime;
		this.builder = builder;
	}
	
	/**
	 * Create a timeline sequence that starts after another event.
	 * @param builder
	 * @param initialEvent
	 */
	TimelineSequence(TimelineBuilder builder, Event afterEvent)
	{
		this.previousEvent = afterEvent;
		this.builder = builder;
	}
	
	public void addEvent(Event event)
	{
		addEvent(event, Duration.ZERO);
	}
	
	public void addEvent(Event event, Duration delay)
	{
		if(previousEvent == null)
		{
			builder.addEvent(event, initialTime.plus(delay));
		}
		else
		{
			builder.addEventAfterEvent(event, previousEvent, delay);
		}
		
		previousEvent = event;
	}
	
	/**
	 * Add an event at the specified point in the future, continuing the sequence from
	 * there.
	 * @param event
	 * @param duration
	 */
	public void addAt(Event event, Duration duration)
	{
		builder.addEvent(event, duration);
		previousEvent = event;
	}
}
