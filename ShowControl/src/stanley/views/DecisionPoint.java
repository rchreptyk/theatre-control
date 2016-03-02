package stanley.views;

import java.time.Duration;

import controls.Event;
import controls.Sequence;
import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineView;
import lighting.LightingView;
import stanley.DecisionMaker;
import stanley.StanleyInterfaces;
import stanley.Volumes;

public class DecisionPoint extends StanleyScene {

	private Sequence startSequence; 
	private Sequence exitSequence;
	
	private DecisionMaker maker;
	private String decisionResult;
	
	public DecisionPoint(String name, DecisionMaker maker, StanleyInterfaces interfaces) {
		this(name, maker, interfaces, true);
	}
	
	public DecisionPoint(String name, DecisionMaker maker, StanleyInterfaces interfaces, boolean lights) {
		super(name, interfaces);
		
		this.maker = maker;
		startSequence = new Sequence("Start Decision"); 
		exitSequence = new Sequence("Start Decision"); 
		
		Duration leaveSceneLength = Duration.ofSeconds(5);
		
		/* Entry */
		if(lights)
		{
			LightingView lightingView = lightingViews.getDecisionView(Duration.ZERO);
			startSequence.addEvent(new ViewShowEvent(lightingView, Duration.ZERO));
		}
		
		View musicView = soundViewFactory.createSoundView(sounds.decisionMusic, Volumes.MUSIC_VOLUME);
		startSequence.addEvent(new ViewShowEvent(musicView, Duration.ofSeconds(2)));
		
		View videoView = screenViewFactory.createScreenView(middleScreen, media.decision);
		startSequence.addEvent(new ViewShowEvent(videoView, Duration.ofSeconds(0)));
		
		/* Exit scene timeline */
		TimelineBuilder builder = new TimelineBuilder();
		
		musicView = soundViewFactory.createFade(sounds.decisionMusic, Volumes.MUSIC_VOLUME, 0, leaveSceneLength);
		Event fadeMusic = new ViewShowEvent(musicView, leaveSceneLength);
		builder.addEvent(fadeMusic, Duration.ZERO);
		
		musicView = soundViewFactory.createHaltView(sounds.decisionMusic);
		builder.addEventAfterEvent(new ViewShowEvent(musicView, Duration.ZERO), fadeMusic, Duration.ofMillis(250));
		
		if(lights)
		{
			LightingView lightingViewOut = lightingViews.getDecisionViewOff(leaveSceneLength);
			builder.addEvent(new ViewShowEvent(lightingViewOut, leaveSceneLength), Duration.ZERO);
		}
		
		View blackout = screenViewFactory.createBlackout(middleScreen);
		builder.addEventAfterEvent(new ViewShowEvent(blackout, Duration.ofSeconds(0)), fadeMusic);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		exitSequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}
	
	public String getResult()
	{
		return decisionResult;
	}
	
	@Override
	public void run()
	{
		startSequence.run();
		decisionResult = maker.makeDecision();
		exitSequence.run();
	}
	
}
