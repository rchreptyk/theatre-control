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

public class Office extends StanleyScene {

	public Office(StanleyInterfaces interfaces) {
		super("Office", interfaces);
		

		TimelineBuilder builder = new TimelineBuilder();
		
		// Naration
		TimelineSequence audioTimelineSequence = builder.addTimelineSequence();
		AudioSequence audioSequence = new AudioSequence(audioTimelineSequence, soundViewFactory, sounds.NARRATION_VOLUME);
		
		audioSequence.addAudio(sounds.introb1, Duration.ofMillis(3500));
		audioSequence.addAudio(sounds.introb2, Duration.ofSeconds(3));
		audioSequence.addAudio(sounds.introb3, Duration.ofSeconds(3));
		Event noOrders = audioSequence.addAudio(sounds.introb4, Duration.ofSeconds(9));
		audioSequence.addAudio(sounds.introb5, Duration.ofSeconds(12));
		audioSequence.addAudio(sounds.introb6, Duration.ofSeconds(9));
		Event gotUpFromDesk = audioSequence.addAudio(sounds.introb7, Duration.ofSeconds(7));
		
		audioSequence.addAudio(sounds.office1, Duration.ofSeconds(4), Duration.ofSeconds(10));
		Event goingToMeetingRoom = audioSequence.addAudio(sounds.office2, Duration.ofSeconds(5), Duration.ofSeconds(3));
		
		Event twoDoorsEvent = audioSequence.addAudio(sounds.twodoors, Duration.ofSeconds(5), Duration.ofSeconds(5));
		
		
		/* Media */
		/* Blackout */
		
		View leftBlackout = screenViewFactory.createBlackout(leftScreen);
		View middleBlackout = screenViewFactory.createBlackout(middleScreen);
		View rightBlackout = screenViewFactory.createBlackout(rightScreen);
		
		Duration blackoutDuration = Duration.ofSeconds(3);
		
		builder.addEvent(new ViewShowEvent(leftBlackout, blackoutDuration), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(middleBlackout, blackoutDuration), Duration.ZERO);
		builder.addEvent(new ViewShowEvent(rightBlackout, blackoutDuration), Duration.ZERO);
		
		/* Office */
		View leftOffice = screenViewFactory.createScreenView(leftScreen, media.officeLeft);
		View middleOffice = screenViewFactory.createScreenView(middleScreen, media.officeMiddle);
		View rightOffice = screenViewFactory.createScreenView(rightScreen, media.officeRight);
		
		Duration officeDuration = Duration.ofSeconds(10);
		Duration officeDelay = Duration.ofSeconds(3);
		
		builder.addEventWith(new ViewShowEvent(leftOffice, officeDuration), gotUpFromDesk, officeDelay);
		builder.addEventWith(new ViewShowEvent(middleOffice, officeDuration), gotUpFromDesk, officeDelay);
		builder.addEventWith(new ViewShowEvent(rightOffice, officeDuration), gotUpFromDesk, officeDelay);
		
		/* Two Doors */
		View leftDoor = screenViewFactory.createScreenView(leftScreen, media.doorsLeft);
		View middleBlack = screenViewFactory.createBlackout(middleScreen);
		View rightDoor = screenViewFactory.createScreenView(rightScreen, media.doorsRight);
		
		Duration doorLength = Duration.ofSeconds(10);
		
		builder.addEventWith(new ViewShowEvent(leftDoor, doorLength), goingToMeetingRoom);
		builder.addEventWith(new ViewShowEvent(middleBlack, doorLength), goingToMeetingRoom);
		builder.addEventWith(new ViewShowEvent(rightDoor, doorLength), goingToMeetingRoom);
		
		/* Lighting */
		
		// Office View
		Duration fadeLightTime = Duration.ofSeconds(10);
		LightingView officeView = lightingViews.getOfficeView(fadeLightTime);
		builder.addEventWith(new ViewShowEvent(officeView, fadeLightTime), noOrders);
		
		//House light
		Duration sideFadeTime = Duration.ofMillis(500);
		LightingView sideLightsOn = lightingViews.getPaintingView(sideFadeTime);
		
		//Side lights off
		LightingView sideLightsOff = lightingViews.getPaintingViewOff(sideFadeTime);
	
		builder.addEventAfterEvent(new ViewShowEvent(sideLightsOn, sideFadeTime), gotUpFromDesk, Duration.ofSeconds(4));
		builder.addEventWith(new ViewShowEvent(sideLightsOff, sideFadeTime), twoDoorsEvent);
		
		LightingView officeOff = lightingViews.getBossesOfficeViewOff(Duration.ZERO);
		builder.addEventAfterEvent(new ViewShowEvent(officeOff, Duration.ZERO), twoDoorsEvent);
		
		/* Build Sequence */
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}

}
