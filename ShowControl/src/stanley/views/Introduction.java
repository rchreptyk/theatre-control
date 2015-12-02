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

public class Introduction extends StanleyScene {
	
	public Introduction(StanleyInterfaces interfaces) {
		super("Introduction", interfaces);
		
		TimelineBuilder builder = new TimelineBuilder();
		
		// Narration
		TimelineSequence audioTimelineSequence = builder.addTimelineSequence();
		AudioSequence audioSequence = new AudioSequence(audioTimelineSequence, soundViewFactory, sounds.NARRATION_VOLUME);
		audioSequence.addAudio(sounds.intro1, Duration.ofSeconds(4));
		audioSequence.addAudio(sounds.intro2, Duration.ofSeconds(6));
		Event employee427sjobs = audioSequence.addAudio(sounds.intro3, Duration.ofSeconds(9));
		audioSequence.addAudio(sounds.intro4, Duration.ofSeconds(9));
		audioSequence.addAudio(sounds.intro5, Duration.ofSeconds(10));
		audioSequence.addAudio(sounds.intro6, Duration.ofSeconds(7));
		audioSequence.addAudio(sounds.intro7, Duration.ofSeconds(5));
		
		/////////
		// Media
		/////////
		
		//#face
		
		TimelineSequence leftMediaSequence = builder.addTimelineSequence(Duration.ofSeconds(2));
		TimelineSequence middleMediaSequence = builder.addTimelineSequence(Duration.ofSeconds(2));
		TimelineSequence rightMediaSequence = builder.addTimelineSequence(Duration.ofSeconds(2));
		
		View stanleyLeft = screenViewFactory.createScreenView(leftScreen, media.pictureOfStanley);
		View stanleyCenter = screenViewFactory.createScreenView(middleScreen, media.pictureOfStanley);
		View stanleyRight = screenViewFactory.createScreenView(rightScreen, media.pictureOfStanley);
		
		Duration stanleyFaceLengthTime = Duration.ofSeconds(6);
		Duration buildingTime = Duration.ofMillis(3200);
		
		leftMediaSequence.addEvent(new ViewShowEvent(stanleyLeft, stanleyFaceLengthTime));
		middleMediaSequence.addEvent(new ViewShowEvent(stanleyCenter, stanleyFaceLengthTime.minus(buildingTime)));
		rightMediaSequence.addEvent(new ViewShowEvent(stanleyRight, stanleyFaceLengthTime));
		
		View bigBuilding = screenViewFactory.createScreenView(middleScreen, media.bigBuilding);
		middleMediaSequence.addEvent(new ViewShowEvent(bigBuilding, buildingTime));
		
		// Four Two Seven Collection
		
		View fourTwoSevenLeft = screenViewFactory.createScreenView(leftScreen, media.fourTwoSevenLeft);
		View fourTwoSevenMiddle = screenViewFactory.createScreenView(middleScreen, media.fourTwoSevenMiddle);
		View fourTwoSevenRight = screenViewFactory.createScreenView(rightScreen, media.fourTwoSevenRight);
		
		leftMediaSequence.addEvent(new ViewShowEvent(fourTwoSevenLeft, Duration.ofSeconds(3)));
		middleMediaSequence.addEvent(new ViewShowEvent(fourTwoSevenMiddle, Duration.ofSeconds(3)));
		rightMediaSequence.addEvent(new ViewShowEvent(fourTwoSevenRight, Duration.ofSeconds(3)));
		
		//Big 4 2 7 numbers
		
		View bigFour = screenViewFactory.createScreenView(leftScreen, media.bigFour);
		View bigTwo = screenViewFactory.createScreenView(middleScreen, media.bigTwo);
		View bigSeven = screenViewFactory.createScreenView(rightScreen, media.bigSeven);
		
		Duration bigNumberLength = Duration.ofMillis(8700);
		
		leftMediaSequence.addEvent(new ViewShowEvent(bigFour, bigNumberLength));
		middleMediaSequence.addEvent(new ViewShowEvent(bigTwo, bigNumberLength.minusMillis(200)), Duration.ofMillis(200));
		rightMediaSequence.addEvent(new ViewShowEvent(bigSeven, bigNumberLength.minusMillis(300)), Duration.ofMillis(300));
		
		// Orders to press buttons
		
		View ordersOne = screenViewFactory.createScreenView(leftScreen, media.ordersOne);
		View ordersTwo = screenViewFactory.createScreenView(middleScreen, media.ordersTwo);
		View ordersThree = screenViewFactory.createScreenView(rightScreen, media.ordersThree);
		
		Duration ordersLength = Duration.ofSeconds(9);
		leftMediaSequence.addEvent(new ViewShowEvent(ordersOne, ordersLength));
		middleMediaSequence.addEvent(new ViewShowEvent(ordersTwo, ordersLength));
		rightMediaSequence.addEvent(new ViewShowEvent(ordersThree, ordersLength));
		
		//Big 4 2 7 numbers
		Duration secondBigNumberLength = Duration.ofMillis(1500);
		leftMediaSequence.addEvent(new ViewShowEvent(bigFour, secondBigNumberLength));
		middleMediaSequence.addEvent(new ViewShowEvent(bigTwo, secondBigNumberLength.minusMillis(200)), Duration.ofMillis(200));
		rightMediaSequence.addEvent(new ViewShowEvent(bigSeven, secondBigNumberLength.minusMillis(300)), Duration.ofMillis(300));
		
		/* Every month day year */
		View dayLeft = screenViewFactory.createScreenView(leftScreen, media.day);
		View dayMiddle = screenViewFactory.createScreenView(middleScreen, media.day);
		View dayRight = screenViewFactory.createScreenView(rightScreen, media.day);
		View monthLeft = screenViewFactory.createScreenView(leftScreen, media.month);
		View monthMiddle = screenViewFactory.createScreenView(middleScreen, media.month);
		View monthRight = screenViewFactory.createScreenView(rightScreen, media.month);
		View yearLeft = screenViewFactory.createScreenView(leftScreen, media.year);
		View yearMiddle = screenViewFactory.createScreenView(middleScreen, media.year);
		View yearRight = screenViewFactory.createScreenView(rightScreen, media.year);
		
		Duration dayLength = Duration.ofSeconds(2);
		leftMediaSequence.addEvent(new ViewShowEvent(dayLeft, dayLength));
		middleMediaSequence.addEvent(new ViewShowEvent(dayMiddle, dayLength));
		rightMediaSequence.addEvent(new ViewShowEvent(dayRight, dayLength));
		
		Duration monthLength = Duration.ofMillis(1500);
		leftMediaSequence.addEvent(new ViewShowEvent(monthLeft, monthLength ));
		middleMediaSequence.addEvent(new ViewShowEvent(monthMiddle, monthLength ));
		rightMediaSequence.addEvent(new ViewShowEvent(monthRight, monthLength ));
		
		Duration yearLength = Duration.ofSeconds(12);
		leftMediaSequence.addEvent(new ViewShowEvent(yearLeft, yearLength));
		middleMediaSequence.addEvent(new ViewShowEvent(yearMiddle, yearLength));
		rightMediaSequence.addEvent(new ViewShowEvent(yearRight, yearLength));
		
		View happyLeft = screenViewFactory.createScreenView(leftScreen, media.happy);
		View happyMiddle = screenViewFactory.createScreenView(middleScreen, media.happy);
		View happyRight = screenViewFactory.createScreenView(rightScreen, media.happy);
		
		Duration happyLength = Duration.ofMillis(1500);
		Event showHappyEvent = new ViewShowEvent(happyLeft, happyLength);
		
		leftMediaSequence.addEvent(showHappyEvent);
		middleMediaSequence.addEvent(new ViewShowEvent(happyMiddle, happyLength ));
		rightMediaSequence.addEvent(new ViewShowEvent(happyRight, happyLength ));
		
		/* Lighting */
		
		Duration fadeLightTime = Duration.ofSeconds(5);
		LightingView view = lightingViews.getTopDown(fadeLightTime);
		builder.addEventWith(new ViewShowEvent(view, fadeLightTime), employee427sjobs);
		
		LightingView black = lightingViews.getTopDownBlack(Duration.ZERO);
		builder.addEventWith(new ViewShowEvent(black, Duration.ofSeconds(2)), showHappyEvent);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	
		
	}

	
}
