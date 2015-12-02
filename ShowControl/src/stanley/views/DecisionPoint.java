package stanley.views;

import java.time.Duration;

import controls.Event;
import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineView;
import lighting.LightingView;
import stanley.StanleyInterfaces;
import stanley.Volumes;

public class DecisionPoint extends StanleyScene {

	public DecisionPoint(String name, StanleyInterfaces interfaces) {
		super(name, interfaces);
		
		Duration sceneLength = Duration.ofSeconds(30);
		Duration leaveSceneLength = Duration.ofSeconds(5);
		
		/* Music */
		LightingView lightingView = lightingViews.getDecisionView(Duration.ZERO);
		sequence.addEvent(new ViewShowEvent(lightingView, Duration.ZERO));
		
		View musicView = soundViewFactory.createSoundView(sounds.decisionMusic, Volumes.MUSIC_VOLUME);
		sequence.addEvent(new ViewShowEvent(musicView, Duration.ofSeconds(2)));
		
		sequence.addEvent(new WaitEvent(sceneLength));
		
		TimelineBuilder builder = new TimelineBuilder();
		
		musicView = soundViewFactory.createFade(sounds.decisionMusic, Volumes.MUSIC_VOLUME, 0, leaveSceneLength);
		Event fadeMusic = new ViewShowEvent(musicView, leaveSceneLength);
		builder.addEvent(fadeMusic, Duration.ZERO);
		
		musicView = soundViewFactory.createHaltView(sounds.decisionMusic);
		builder.addEventAfterEvent(new ViewShowEvent(musicView, Duration.ZERO), fadeMusic);
		
		LightingView lightingViewOut = lightingViews.getDecisionViewOff(leaveSceneLength);
		builder.addEvent(new ViewShowEvent(lightingViewOut, leaveSceneLength), Duration.ZERO);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	

		
		
	}
}
