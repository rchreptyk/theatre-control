package controls;

import java.time.Duration;
import java.util.logging.Logger;

import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;

/*
 * A sequence is an automated set of views that are executed in order. There can be no branching in
 * transitions.
 */
public class Sequence implements Runnable {
	private static Logger LOG = Logger.getLogger("Sequence");
	
	private String name;
	private TimelineBuilder timelineBuilder;
	private TimelineSequence sequence;
	
	private Duration lastEventDuration = Duration.ZERO;
	
	public Sequence(String name)
	{
		this.name = name;
		timelineBuilder = new TimelineBuilder();
		sequence = timelineBuilder.addTimelineSequence();
	}
	
	public void addEvent(Event event)
	{
		sequence.addEvent(event);
		lastEventDuration = event.getDuration();
	}

	@Override
	public void run() {
		LOG.info("Running " + name + " sequence");
		Timeline timeline = timelineBuilder.buildTimeline();
		timeline.run();
		
		//Wait for last event to complete before returning
		try {
			Thread.sleep(lastEventDuration.toMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
