package controls;

import java.time.Duration;

public class ViewShowEvent implements Event {
	
	private View viewToRender;
	private Duration duration;
	
	private Action renderAction;
	
	public ViewShowEvent(View view, Duration duration)
	{
		this.viewToRender = view;
		this.duration = duration;
	}

	@Override
	public boolean hasCompleted() {
		return renderAction.hasCompleted();
	}

	@Override
	public void stop() {
		renderAction.stop();
	}

	@Override
	public Duration getDuration() {
		return duration;
	}

	@Override
	public void start() {
		renderAction = viewToRender.render();
	}
}
