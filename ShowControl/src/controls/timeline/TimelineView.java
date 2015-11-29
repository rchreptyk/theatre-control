package controls.timeline;

import controls.Action;
import controls.View;

/**
 * Starts a timeline as a view
 */
public class TimelineView implements View {
	private Timeline timeline;
	
	public TimelineView(Timeline timeline)
	{
		this.timeline = timeline;
	}
	
	@Override
	public Action render() {
		TimelineThread thread = new TimelineThread(timeline);
		thread.start();
		return thread;
	}

}
