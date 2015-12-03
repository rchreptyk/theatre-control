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
import media.sound.Sound;
import media.sound.UniqueSound;
import stanley.StanleyInterfaces;
import stanley.Volumes;

public class Appartment extends StanleyScene {

	public Appartment(StanleyInterfaces interfaces) {
		super("Appartment", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		/* Lighting Up */
		Duration bringUpOffice = Duration.ofSeconds(5); 
		LightingView upOffice = lightingViews.getOfficeView(bringUpOffice);
		Event upOfficeEvent = new ViewShowEvent(upOffice, bringUpOffice);
		builder.addEvent(upOfficeEvent, Duration.ZERO);
		
		TimelineSequence narrTimelineSequence = builder.addTimelineSequence(upOfficeEvent);
		AudioSequence firstNarration = new AudioSequence(narrTimelineSequence, soundViewFactory, Volumes.NARRATION_VOLUME);

		firstNarration.addAudio(sounds.phone1, Duration.ofSeconds(6));
		Event lookStanley = firstNarration.addAudio(sounds.phone2, Duration.ofSeconds(4));
		firstNarration.addAudio(sounds.phone3, Duration.ofSeconds(13));
		firstNarration.addAudio(sounds.phone4, Duration.ofMillis(9500));
		firstNarration.addAudio(sounds.phone5, Duration.ofSeconds(2));
		firstNarration.addAudio(sounds.phone6, Duration.ofSeconds(3));
		Event prePhoneTaunt = firstNarration.addAudio(sounds.phone7, Duration.ofSeconds(2));

		firstNarration.addAudio(sounds.phoneCallTaunt, Duration.ofSeconds(12), Duration.ofSeconds(3));
		
		TimelineSequence phoneRingTimelineSequence = builder.addTimelineSequence(prePhoneTaunt);
		AudioSequence phoneRingSequence = new AudioSequence(phoneRingTimelineSequence, soundViewFactory, Volumes.NARRATION_VOLUME);
		
		Sound phoneSound = new UniqueSound(sounds.phoneRing);
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		Event phoneAudioOut = phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		
		TimelineSequence secondTimelineSequence = builder.addTimelineSequence(phoneAudioOut);
		AudioSequence secondNarration = new AudioSequence(secondTimelineSequence, soundViewFactory, Volumes.NARRATION_VOLUME);
		
		secondNarration.addAudio(sounds.appt1, Duration.ofSeconds(4));
		secondNarration.addAudio(sounds.appt2, Duration.ofSeconds(7));
		secondNarration.addAudio(sounds.appt3, Duration.ofSeconds(5));
		secondNarration.addAudio(sounds.appt4, Duration.ofSeconds(4));
		Event thisIsTheStory = secondNarration.addAudio(sounds.appt5, Duration.ofSeconds(5)); //death of a man named stanley, drop lights
		secondNarration.addAudio(sounds.appt6, Duration.ofSeconds(11));
		secondNarration.addAudio(sounds.appt7, Duration.ofSeconds(14)); //pushing buttons
		secondNarration.addAudio(sounds.appt8, Duration.ofSeconds(4));
		secondNarration.addAudio(sounds.appt9, Duration.ofSeconds(6));
		secondNarration.addAudio(sounds.appt10, Duration.ofSeconds(10)); //discovery of new lands, show freedom
		secondNarration.addAudio(sounds.appt11, Duration.ofSeconds(6));
		secondNarration.addAudio(sounds.appt12, Duration.ofSeconds(10)); //10.5 people vanished off the face of the earth
		secondNarration.addAudio(sounds.appt13, Duration.ofSeconds(8));
		secondNarration.addAudio(sounds.appt14, Duration.ofSeconds(9)); //two open doors
		secondNarration.addAudio(sounds.appt15, Duration.ofSeconds(10));
		secondNarration.addAudio(sounds.appt16, Duration.ofSeconds(7));
		secondNarration.addAudio(sounds.appt17, Duration.ofSeconds(13)); //mind control facility
		secondNarration.addAudio(sounds.appt18, Duration.ofSeconds(2));
		secondNarration.addAudio(sounds.appt19, Duration.ofSeconds(14));
		secondNarration.addAudio(sounds.appt20, Duration.ofSeconds(7));
		secondNarration.addAudio(sounds.appt21, Duration.ofSeconds(4));
		secondNarration.addAudio(sounds.appt22, Duration.ofSeconds(12));
		secondNarration.addAudio(sounds.appt23, Duration.ofSeconds(13));
		secondNarration.addAudio(sounds.appt24, Duration.ofSeconds(8)); // do not press button
		secondNarration.addAudio(sounds.appt25, Duration.ofSeconds(10));
		secondNarration.addAudio(sounds.appt26, Duration.ofSeconds(4));
		secondNarration.addAudio(sounds.appt27, Duration.ofSeconds(4));
		secondNarration.addAudio(sounds.appt28, Duration.ofSeconds(14));
		secondNarration.addAudio(sounds.appt29, Duration.ofSeconds(6));
		secondNarration.addAudio(sounds.appt30, Duration.ofSeconds(11));
		
		/* Views */
		
		View stanleyLeft = screenViewFactory.createScreenView(leftScreen, media.pictureOfStanley);
		View stanleyCenter = screenViewFactory.createScreenView(middleScreen, media.pictureOfStanley);
		View stanleyRight = screenViewFactory.createScreenView(rightScreen, media.pictureOfStanley);
		
		Duration stanleyFaceLengthTime = Duration.ofSeconds(6);
		
		builder.addEventWith(new ViewShowEvent(stanleyLeft, stanleyFaceLengthTime), thisIsTheStory);
		builder.addEventWith(new ViewShowEvent(stanleyCenter, stanleyFaceLengthTime), thisIsTheStory);
		builder.addEventWith(new ViewShowEvent(stanleyRight, stanleyFaceLengthTime), thisIsTheStory);
		
		Duration topDownRaise = Duration.ofSeconds(5);
		LightingView view = lightingViews.getOfficeViewToTopDown(topDownRaise);
		builder.addEventWith(new ViewShowEvent(view, topDownRaise), lookStanley);
		
		LightingView topDownOffView = lightingViews.getTopDownBlack(Duration.ZERO);
		builder.addEventWith(new ViewShowEvent(topDownOffView, Duration.ZERO), thisIsTheStory);

		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
	}

}
