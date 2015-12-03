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
import media.sound.AudioSequence;
import stanley.StanleyInterfaces;
import stanley.Volumes;

public class Freedom extends StanleyScene {

	public Freedom(StanleyInterfaces interfaces) {
		super("Freedom", interfaces);
		TimelineBuilder builder = new TimelineBuilder();

		/* Narration */
		TimelineSequence audioTimelineSequence = builder.addTimelineSequence();
		AudioSequence audioSequence = new AudioSequence(audioTimelineSequence, soundViewFactory, sounds.NARRATION_VOLUME);
		
		audioSequence.addAudio(sounds.freedom1, Duration.ofSeconds(6));
		audioSequence.addAudio(sounds.freedom2, Duration.ofSeconds(1));
		audioSequence.addAudio(sounds.freedom3, Duration.ofSeconds(3));
		audioSequence.addAudio(sounds.freedom4, Duration.ofSeconds(10));
		audioSequence.addAudio(sounds.freedom5, Duration.ofSeconds(9));
		audioSequence.addAudio(sounds.freedom6, Duration.ofSeconds(10));
		Event sunlight = audioSequence.addAudio(sounds.freedom7, Duration.ofSeconds(12));
		audioSequence.addAudio(sounds.freedom8, Duration.ofSeconds(6));
		audioSequence.addAudio(sounds.freedom9, Duration.ofSeconds(10));
		audioSequence.addAudio(sounds.freedom10, Duration.ofSeconds(7));
		Event lastAudio = audioSequence.addAudio(sounds.freedom11, Duration.ofSeconds(3));
		
		/* Music */
		View musicView = soundViewFactory.createSoundView(sounds.freedomMusic, Volumes.MUSIC_VOLUME);
		builder.addEvent(new ViewShowEvent(musicView, Duration.ofSeconds(97)), Duration.ZERO);
		
		/* Video */
		View leftScreenView = screenViewFactory.createScreenView(leftScreen, media.landscapes);
		View middleScreenView = screenViewFactory.createScreenView(middleScreen, media.landscapes);
		View rightScreenView = screenViewFactory.createScreenView(rightScreen, media.landscapes);
		
		builder.addEvent(new ViewShowEvent(leftScreenView, Duration.ofSeconds(1)), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(middleScreenView, Duration.ofSeconds(1)), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(rightScreenView, Duration.ofSeconds(1)), Duration.ZERO);
		
		View blackLeft = screenViewFactory.createBlackout(leftScreen);
		View blackMiddle = screenViewFactory.createBlackout(middleScreen);
		View blackRight = screenViewFactory.createBlackout(rightScreen);
		
		builder.addEventAfterEvent(new ViewShowEvent(blackLeft, Duration.ofSeconds(10)), lastAudio);
		builder.addEventAfterEvent(new ViewShowEvent(blackMiddle, Duration.ofSeconds(10)), lastAudio);
		builder.addEventAfterEvent(new ViewShowEvent(blackRight, Duration.ofSeconds(10)), lastAudio);
		
		/* Lights */
		Duration sunlightDuration = Duration.ofSeconds(5);
		LightingView view = lightingViews.getSunLight(sunlightDuration);
		builder.addEventWith(new ViewShowEvent(view, sunlightDuration), sunlight);
		
		LightingView sunlightOff = lightingViews.getSunLightOff(sunlightDuration);
		builder.addEventAfterEvent(new ViewShowEvent(sunlightOff, sunlightDuration), lastAudio);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}

}
