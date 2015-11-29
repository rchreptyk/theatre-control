package controls.timeline;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import controls.Event;

public class TimelineBuilder {
	
	private ArrayList<TimelineEvent> eventList = new ArrayList<>();
	private HashMap<Event, Duration> eventStartTime = new HashMap<>();
	
	/**
	 * Add an event to the specific time. The 
	 * @param event
	 * @param time
	 */
	public Event addEvent(Event event, Duration time)
	{
		if(eventStartTime.containsKey(event))
			throw new RuntimeException("Cannot add a duplicate event");
		
		eventStartTime.put(event, time);
		eventList.add(new TimelineEvent(event, time));
		
		return event;
	}
	
	public Event addEventAfterEvent(Event event, Event afterEvent)
	{
		return addEventAfterEvent(event, afterEvent, Duration.ZERO);
	}
	
	public Event addEventAfterEvent(Event event, Event afterEvent, Duration offset)
	{
		Duration startTime = eventStartTime.get(afterEvent);
		if(startTime == null)
		{
			throw new RuntimeException("Run after event not registered");
		}
		
		Duration newStartTime = startTime.plus(afterEvent.getDuration()).plus(offset);
		return this.addEvent(event, newStartTime);
	}
	
	public Timeline buildTimeline()
	{
		return new Timeline(eventList);
	}
}
