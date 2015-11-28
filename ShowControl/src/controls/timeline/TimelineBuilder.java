package controls.timeline;

import java.time.Duration;
import java.util.HashMap;
import java.util.PriorityQueue;

import controls.Event;

public class TimelineBuilder {
	
	private PriorityQueue<TimelineEvent> eventQueue = new PriorityQueue<>();
	private HashMap<Event, Duration> eventStartTime = new HashMap<>();
	
	/**
	 * Add an event to the specific time. The 
	 * @param event
	 * @param time
	 */
	public void addEvent(Event event, Duration time)
	{
		if(eventStartTime.containsKey(event))
			throw new RuntimeException("Cannot add a duplicate event");
		
		eventStartTime.put(event, time);
		eventQueue.add(new TimelineEvent(event, time));
	}
	
	public void addEventAfterEvent(Event event, Event afterEvent)
	{
		addEventAfterEvent(event, afterEvent, Duration.ZERO);
	}
	
	public void addEventAfterEvent(Event event, Event afterEvent, Duration offset)
	{
		Duration startTime = eventStartTime.get(afterEvent);
		if(startTime == null)
		{
			throw new RuntimeException("Run after event not registered");
		}
		
		Duration newStartTime = startTime.plus(afterEvent.getDuration()).plus(offset);
		this.addEvent(event, newStartTime);
	}
	
	public Timeline buildTimeline()
	{
		return new Timeline(eventQueue);
	}
}
