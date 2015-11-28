package controls.timeline;

import controls.Action;

/**
 * Run a timeline in another thread
 *
 */
public class TimelineThread implements Action {

	private Timeline timeline;
	private Thread thread;
	
	public TimelineThread(Timeline timeline)
	{
		this.timeline = timeline;
		this.thread = new Thread(timeline);
	}
	
	public void start()
	{
		this.thread.start();
	}
	
	@Override
	public boolean hasCompleted() {
		return timeline.hasStopped();
	}

	@Override
	public void stop() {
		timeline.stop();
	}

}
