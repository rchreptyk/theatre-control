package controls;

import java.time.Duration;
import java.util.logging.Logger;

import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;

/*
 * A sequence is an automated set of views that are executed in order. There can be no branching in
 * transitions.
 */
public class Sequence implements Runnable {
	private static Logger LOG = Logger.getLogger("Sequence");
	
	private String name;
	private TimelineBuilder timelineBuilder = new TimelineBuilder();
	
	private Event previousEvent = null;
	
	public Sequence(String name)
	{
		this.name = name;
	}
	
	public void addEvent(Event event)
	{
		if(previousEvent == null)
			timelineBuilder.addEvent(event, Duration.ZERO);
		else
			timelineBuilder.addEventAfterEvent(event, previousEvent);
		
		previousEvent = event;
	}

	@Override
	public void run() {
		LOG.info("Running " + name + " sequence");
		Timeline timeline = timelineBuilder.buildTimeline();
		timeline.run();
		
		//Wait for last event to complete before returning
		try {
			Thread.sleep(previousEvent.getDuration().toMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
