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
import stanley.StanleyInterfaces;

public class MindControl extends StanleyScene {

	public MindControl(StanleyInterfaces interfaces) {
		super("Mind Control", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		/* Sound */
		View soundView = soundViewFactory.createSoundView(sounds.hatchOpening, 70);
		Event hatchOpenEvent = new ViewShowEvent(soundView, Duration.ofSeconds(24));
		builder.addEvent(hatchOpenEvent, Duration.ZERO);
		
		/* Narration */
		TimelineSequence narrTimelineSeq = builder.addTimelineSequence(hatchOpenEvent);
		AudioSequence narrationSeq = new AudioSequence(narrTimelineSeq, soundViewFactory, sounds.NARRATION_VOLUME);
		
		Event lightsRose = narrationSeq.addAudio(sounds.monitorNarr1, Duration.ofSeconds(4));
		narrationSeq.addAudio(sounds.monitorNarr2, Duration.ofSeconds(7));
		Event monitorJumped = narrationSeq.addAudio(sounds.monitorNarr3, Duration.ofSeconds(4), Duration.ofSeconds(3));
		narrationSeq.addAudio(sounds.monitorNarr4, Duration.ofSeconds(5));
		narrationSeq.addAudio(sounds.monitorNarr5, Duration.ofSeconds(11));
		narrationSeq.addAudio(sounds.monitorNarr6, Duration.ofSeconds(6));
		narrationSeq.addAudio(sounds.monitorNarr7, Duration.ofSeconds(7));
		narrationSeq.addAudio(sounds.monitorNarr8, Duration.ofSeconds(4));
		narrationSeq.addAudio(sounds.monitorNarr9, Duration.ofSeconds(9));
		narrationSeq.addAudio(sounds.monitorNarr10, Duration.ofSeconds(4));
		narrationSeq.addAudio(sounds.monitorNarr11, Duration.ofSeconds(7));
		
		Event controls = narrationSeq.addAudio(sounds.controls1, Duration.ofSeconds(9));
		narrationSeq.addAudio(sounds.controls2, Duration.ofSeconds(6));
		narrationSeq.addAudio(sounds.controls3, Duration.ofSeconds(11));
		narrationSeq.addAudio(sounds.controls4, Duration.ofSeconds(6));
		
		/* Music */
		
		int musicVolume = 60;
		Duration musicDuration = Duration.ofSeconds(90);
		Duration musicFadeLength = Duration.ofSeconds(5);
		
		
		View musicView = soundViewFactory.createSoundView(sounds.monitorMusic, 0, Duration.ofSeconds(76));
		Event playMusicEvent = new ViewShowEvent(musicView, musicDuration);
		builder.addEventAfterEvent(playMusicEvent, hatchOpenEvent);
		
		View fadeMusicIn = soundViewFactory.createFade(sounds.monitorMusic, 0, musicVolume, musicFadeLength);
		builder.addEventWith(new ViewShowEvent(fadeMusicIn, musicFadeLength), playMusicEvent, Duration.ofMillis(500));
		
		
		/* Media */
		Duration blankDuration = Duration.ofSeconds(5);
	
		View leftBlank = screenViewFactory.createScreenView(leftScreen, media.blackMonitors);
		View middleBlank = screenViewFactory.createScreenView(middleScreen, media.blackMonitors);
		View rightBlank = screenViewFactory.createScreenView(rightScreen, media.blackMonitors);
		
		builder.addEventWith(new ViewShowEvent(leftBlank, blankDuration), lightsRose);
		builder.addEventWith(new ViewShowEvent(middleBlank, blankDuration), lightsRose);
		builder.addEventWith(new ViewShowEvent(rightBlank, blankDuration), lightsRose);
		
		/* Monitors */
		
		View leftMonitors = screenViewFactory.createScreenView(leftScreen, media.monitors1);
		View middleMonitors = screenViewFactory.createScreenView(middleScreen, media.monitors2);
		View rightMonitors = screenViewFactory.createScreenView(rightScreen, media.monitors3);
		
		builder.addEventWith(new ViewShowEvent(leftMonitors, blankDuration), monitorJumped);
		builder.addEventWith(new ViewShowEvent(middleMonitors, blankDuration), monitorJumped);
		builder.addEventWith(new ViewShowEvent(rightMonitors, blankDuration), monitorJumped);
		
		View middleControls = screenViewFactory.createScreenView(middleScreen, media.mindControls);
		builder.addEventWith(new ViewShowEvent(middleControls, Duration.ofSeconds(5)), controls);
		
		/* Lights */
		Duration fadeLightTime = Duration.ofSeconds(5);
		LightingView view = new LightingView(lightingLoop, fadeLightTime);
		view.addChange(new IntensityChange(lights.area2Top, 60));
		builder.addEventWith(new ViewShowEvent(view, fadeLightTime), lightsRose);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}
	
}
