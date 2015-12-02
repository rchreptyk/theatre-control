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

public class Lounge extends StanleyScene {

	public Lounge(StanleyInterfaces interfaces) {
		super("Lounge", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		Duration officeFadeIn = Duration.ofSeconds(5);
		LightingView officeIn = lightingViews.getOfficeView(officeFadeIn);
		builder.addEvent(new ViewShowEvent(officeIn, officeFadeIn), Duration.ZERO);
		
		TimelineSequence narrTimelineSequence = builder.addTimelineSequence();
		AudioSequence narrAudioSequence = new AudioSequence(narrTimelineSequence, soundViewFactory, sounds.NARRATION_VOLUME);
		narrAudioSequence.addAudio(sounds.lounge1, Duration.ofSeconds(11));
		narrAudioSequence.addAudio(sounds.lounge2, Duration.ofSeconds(6));
		narrAudioSequence.addAudio(sounds.lounge3, Duration.ofSeconds(8));
		narrAudioSequence.addAudio(sounds.lounge4, Duration.ofSeconds(11));
		narrAudioSequence.addAudio(sounds.lounge5, Duration.ofSeconds(9));
		narrAudioSequence.addAudio(sounds.lounge6, Duration.ofSeconds(10));
		Event lastAudio = narrAudioSequence.addAudio(sounds.loungeExit, Duration.ofSeconds(6), Duration.ofSeconds(5));
		
		View musicView = soundViewFactory.createSoundView(sounds.lounge_music, Volumes.MUSIC_VOLUME);
		builder.addEvent(new ViewShowEvent(musicView, Duration.ofSeconds(40)), Duration.ZERO);
		
		Duration officeFadeOut = Duration.ofSeconds(5);
		View musicFadeout = soundViewFactory.createFade(sounds.lounge_music, Volumes.MUSIC_VOLUME, 0, Duration.ofSeconds(5));
		builder.addEventWith(new ViewShowEvent(musicFadeout, officeFadeOut), lastAudio);
		
		View leftLounge = screenViewFactory.createScreenView(leftScreen, media.loungeLeft);
		View middleLounge = screenViewFactory.createScreenView(middleScreen, media.loungeMiddle);
		View rightLounge = screenViewFactory.createScreenView(rightScreen, media.loungeRight);
		
		builder.addEvent(new ViewShowEvent(leftLounge, Duration.ZERO), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(middleLounge, Duration.ZERO), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(rightLounge, Duration.ZERO), Duration.ZERO);
		
		LightingView officeOff = lightingViews.getOfficeViewBlack(officeFadeOut);
		builder.addEventAfterEvent(new ViewShowEvent(officeOff, officeFadeOut), lastAudio);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	

	}

}
