package stanley.views;

import java.time.Duration;

import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineView;
import lighting.LightingView;
import stanley.StanleyInterfaces;
import stanley.Volumes;

public class Preshow extends StanleyScene{

	public Preshow(StanleyInterfaces interfaces) {
		super("Preshow", interfaces);
		
		View leftScreenView = screenViewFactory.createScreenView(leftScreen, media.phoneScene);
		View middleScreenView = screenViewFactory.createScreenView(middleScreen, media.phoneScene);
		View rightScreenView = screenViewFactory.createScreenView(rightScreen, media.phoneScene);
		
		TimelineBuilder builder = new TimelineBuilder();
		builder.addEvent(new ViewShowEvent(leftScreenView, Duration.ofSeconds(60)), Duration.ofSeconds(0));
		builder.addEvent(new ViewShowEvent(middleScreenView, Duration.ofSeconds(60)), Duration.ofSeconds(0));
		builder.addEvent(new ViewShowEvent(rightScreenView, Duration.ofSeconds(60)), Duration.ofSeconds(0));
		
		View introMusic = soundViewFactory.createSoundView(sounds.introMusic, Volumes.MUSIC_VOLUME);
		builder.addEvent(new ViewShowEvent(introMusic, Duration.ofSeconds(74)), Duration.ofSeconds(0));
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
		
		LightingView houseOff = lightingViews.getHouseViewOff(Duration.ofSeconds(7));
		sequence.addEvent(new ViewShowEvent(houseOff, Duration.ofSeconds(7)));
		
		sequence.addEvent(new WaitEvent(Duration.ofSeconds(5)));
	}

}
