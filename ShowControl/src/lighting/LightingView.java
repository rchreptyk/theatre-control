package lighting;

import java.time.Duration;
import java.util.ArrayList;

import controls.Action;
import controls.InstantAction;
import controls.View;
import controls.timeline.TimelineThread;
import controls.timeline.TimelineBuilder;
import lighting.change.LightingChange;
import lighting.change.LightingChangeEvent;

/**
 * Represents a simple lighting view which brings up lights to 
 * a certain level. 
 *
 */
public class LightingView implements View {

	private Duration fadeView = Duration.ZERO; 
	private ArrayList<LightingChange> changes = new ArrayList<LightingChange>();
	private LightingLoop loop;
	
	private static int refreshRateMili = 100;
	
	public LightingView(LightingLoop loop)
	{
		this.loop = loop;
	}
	
	public LightingView(LightingLoop loop, Duration initialFade)
	{
		this.loop = loop;
		this.fadeView = initialFade;
	}
	
	public void addChange(LightingChange change)
	{
		changes.add(change);
	}
	
	public void setDuration(Duration duration)
	{
		this.fadeView = duration;
	}
	
	@Override
	public Action render() {
		
		if(fadeView.isZero())
		{
			return instantRender();
		}
		else
		{
			return fadeRender();
		}
	}
	
	private Action fadeRender()
	{
		int numberOfEvents = (int)(fadeView.toMillis() / refreshRateMili);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		for(LightingChange change : changes)
		{
			int time = 0;
			for(LightingChange subChange : change.getIntermediateChanges(numberOfEvents))
			{
				builder.addEvent(new LightingChangeEvent(subChange), Duration.ofMillis(time));
				time += refreshRateMili;
			}
		}
		
		int time = 0;
		for(int i = 0; i < numberOfEvents + 1; i++)
		{
			builder.addEvent(new LightingLoopUpdateEvent(loop), Duration.ofMillis(time));
			time += refreshRateMili;
		}
		
		
		TimelineThread thread =  new TimelineThread(builder.buildTimeline());
		thread.start();
		return thread;
	}
	
	private Action instantRender()
	{
		for(LightingChange change : changes)
		{
			change.execute();
		}
		
		loop.update();
		
		return InstantAction.ACTION;
	}
	
}
