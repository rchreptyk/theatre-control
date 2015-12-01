package stanley.views;

import java.time.Duration;

import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;
import controls.timeline.TimelineView;
import media.sound.AudioSequence;
import media.sound.UniqueSound;
import stanley.StanleyInterfaces;

public class MeetingRoom extends StanleyScene{

	public MeetingRoom(StanleyInterfaces interfaces) {
		super("Meeting Room", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		/* Audio */
		TimelineSequence audioTimelineSequence = builder.addTimelineSequence();
		AudioSequence audioSequence = new AudioSequence(audioTimelineSequence, soundViewFactory, sounds.NARRATION_VOLUME);
		audioSequence.addAudio(sounds.meetingRoom1, Duration.ofSeconds(3), Duration.ofSeconds(7));
		audioSequence.addAudio(sounds.meetingRoom2, Duration.ofSeconds(5));
		
		/* Slides */
		Duration slideDuration = Duration.ofSeconds(5);
		
		TimelineSequence clickerNoiseSequence = builder.addTimelineSequence();
		AudioSequence clickerNoises = new AudioSequence(clickerNoiseSequence, soundViewFactory, 30);
		
		TimelineSequence leftMediaSequence = builder.addTimelineSequence();
		TimelineSequence rightMediaSequence = builder.addTimelineSequence();
		
		UniqueSound clickerSound = new UniqueSound(sounds.meetingRoomClicker);
		
		for(int i = 0; i < 5; i++)
		{
			View leftView = screenViewFactory.createScreenView(leftScreen, media.slides[i]);
			View rightView = screenViewFactory.createScreenView(rightScreen, media.slides[i]);
			
			leftMediaSequence.addEvent(new ViewShowEvent(leftView, slideDuration));
			rightMediaSequence.addEvent(new ViewShowEvent(rightView, slideDuration));
			
			clickerNoises.addAudio(clickerSound, slideDuration);
		}
			
		
		/* Media */
		View meetingRoomMiddle = screenViewFactory.createScreenView(middleScreen, media.meetingRoom);
		builder.addEvent(new ViewShowEvent(meetingRoomMiddle, Duration.ofSeconds(5)), Duration.ZERO);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}

}
