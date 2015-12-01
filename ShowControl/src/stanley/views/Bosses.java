package stanley.views;

import java.time.Duration;

import controls.Event;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;
import controls.timeline.TimelineView;
import lighting.LightingView;
import lighting.change.IntensityChange;
import media.sound.AudioSequence;
import media.sound.UniqueSound;
import stanley.StanleyInterfaces;

public class Bosses extends StanleyScene {

	public Bosses(StanleyInterfaces interfaces) {
		super("Bosses", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		/* Narration */
		TimelineSequence narrTimelineSeq = builder.addTimelineSequence(Duration.ofSeconds(2));
		AudioSequence narrationSeq = new AudioSequence(narrTimelineSeq, soundViewFactory, sounds.NARRATION_VOLUME);
		
		narrationSeq.addAudio(sounds.bossesOffice1, Duration.ofSeconds(7));
		narrationSeq.addAudio(sounds.bossesOffice2, Duration.ofSeconds(9));
		narrationSeq.addAudio(sounds.bossesOffice3, Duration.ofSeconds(9));
		
		Duration bossOffice4Length = Duration.ofSeconds(7);
		narrationSeq.addAudio(sounds.bossesOffice4, bossOffice4Length);
		Event beforePin = narrationSeq.addAudio(sounds.bossesOffice5, Duration.ofSeconds(3));
		
		Event incred = narrationSeq.addAudio(sounds.bosseSuccess1, Duration.ofSeconds(9), Duration.ofSeconds(8));
		
		View lightsShutDown = soundViewFactory.createSoundView(sounds.lightsShutDown, 80);
		Event lightShutDownEvent = new ViewShowEvent(lightsShutDown, Duration.ofSeconds(1));
		builder.addEventAfterEvent(lightShutDownEvent, incred);
		
		/* Beeps */
		
		Duration beepDuration = Duration.ofMillis(500);
		
		UniqueSound beepSound = new UniqueSound(sounds.pinPadBeep);
		View beepView = soundViewFactory.createSoundView(beepSound, 50);
		
		TimelineSequence beepTimeline = builder.addTimelineSequence(beforePin);
		
		Duration firstBeepOffset = bossOffice4Length.minus(beepDuration.multipliedBy(4));
		beepTimeline.addEvent(new ViewShowEvent(beepView, beepDuration), firstBeepOffset);
		
		//Three following beeps
		for(int i = 0; i < 3; i++)
		{
			beepTimeline.addEvent(new ViewShowEvent(beepView, beepDuration));
		}
		
		/* Media */
		View firePlace = screenViewFactory.createScreenView(leftScreen, media.firePlace);
		View woodMiddle = screenViewFactory.createScreenView(middleScreen, media.woodenWall);
		View woodRight = screenViewFactory.createScreenView(rightScreen, media.woodenWall);
		
		Duration officeSceneLength = Duration.ofSeconds(6);
		
		builder.addEvent(new ViewShowEvent(firePlace, officeSceneLength), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(woodMiddle, officeSceneLength), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(woodRight, officeSceneLength), Duration.ZERO);
		
		View blackoutLeft = screenViewFactory.createBlackout(leftScreen);
		View blackoutMiddle = screenViewFactory.createBlackout(middleScreen);
		View blackoutRight = screenViewFactory.createBlackout(rightScreen);
		
		builder.addEventWith(new ViewShowEvent(blackoutLeft, Duration.ZERO), lightShutDownEvent);
		builder.addEventWith(new ViewShowEvent(blackoutMiddle, Duration.ZERO), lightShutDownEvent);
		builder.addEventWith(new ViewShowEvent(blackoutRight, Duration.ZERO), lightShutDownEvent);
		
		/* Lighting */
		Duration fadeLightTime = Duration.ofSeconds(3);
		LightingView view = new LightingView(lightingLoop, fadeLightTime);
		view.addChange(new IntensityChange(lights.warmBackLeft, 40));
		view.addChange(new IntensityChange(lights.warmBackRight, 40));
		builder.addEvent(new ViewShowEvent(view, fadeLightTime), Duration.ZERO);
		
		LightingView blackout = new LightingView(lightingLoop);
		blackout.addChange(new IntensityChange(lights.area2Front, 0));
		blackout.addChange(new IntensityChange(lights.area2Left45, 0));
		blackout.addChange(new IntensityChange(lights.area2Right45, 0));
		blackout.addChange(new IntensityChange(lights.area2Top, 0));
		blackout.addChange(new IntensityChange(lights.warmBackLeft, 0));
		blackout.addChange(new IntensityChange(lights.warmBackRight, 0));
		
		builder.addEventWith(new ViewShowEvent(blackout, Duration.ZERO), lightShutDownEvent);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}

}
