package controls.timeline;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

import controls.Event;

/**
 * A timeline executes a queue of events, sleeping
 * the correct amount of time between each of the executions.
 * 
 * @author russell
 *
 */
public class Timeline implements Runnable {
	private static Logger LOG = Logger.getLogger("Timeline");
	
	private volatile boolean stopped = false;
	
	private PriorityQueue<TimelineEvent> eventQueue = new PriorityQueue<>();
	private List<Event> executedEvents = new ArrayList<Event>();
	
	private Duration duration = Duration.ZERO;
	
	public Timeline(Collection<TimelineEvent> events)
	{
		for(TimelineEvent event : events)
		{
			eventQueue.add(event);
			
			Duration endTime = event.getStartTime().plus(event.getDuration());
			if(endTime.compareTo(duration) > 0)
				duration = endTime;
		}
	}
	
	public void stop()
	{
		LOG.info("Stopping timeline");
		stopped = true;
		
		for(Event event : executedEvents)
		{
			if(!event.hasCompleted())
				event.stop();
		}
	}
	
	public boolean hasStopped()
	{
		return stopped;
	}
	
	public Duration getDuration()
	{
		return duration;
	}
	
	@Override
	public void run() {
		LOG.info("Starting timeline.");
		
		Duration currentTime = Duration.ZERO;
		
		TimelineEvent event;
		try
		{
			while((event = eventQueue.poll()) != null)
			{
				LOG.info("Processing event. Current time " + currentTime);
				
				Duration eventStartTime = event.getStartTime();
				
				LOG.info("Event should starts at" + eventStartTime.getSeconds());
				
				Duration timeToWait = eventStartTime.minus(currentTime);
				if(!timeToWait.isZero() && !timeToWait.isNegative())
				{
					LOG.info("Waiting for " + timeToWait.getSeconds() + " seconds.");
					Thread.sleep(timeToWait.toMillis());
				}
				
				LOG.info("Starting event");
				event.start();
				
				currentTime = eventStartTime;
				
				if(stopped)
				{
					LOG.info("Stopping timeline, prematurely stopped");
					break;
				}
					
			}
		}
		catch(InterruptedException e)
		{
			LOG.info("Aborting timeline");
		}
		
		
		stopped = true;
	}
}
