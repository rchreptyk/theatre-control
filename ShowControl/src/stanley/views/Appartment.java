package stanley.views;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import controls.Event;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;
import controls.timeline.TimelineView;
import lighting.LightingView;
import lighting.change.IntensityChange;
import lighting.change.LightingChange;
import media.MessageTransmitter;
import media.sound.AudioSequence;
import media.sound.Sound;
import media.sound.UniqueSound;
import stanley.StanleyInterfaces;
import stanley.TextUpdater;
import stanley.Volumes;
import stanley.WebPuller;
import stanley.WebRaceDecisionMaker;

public class Appartment extends StanleyScene {
	private ArrayList<TimelineBuilder> builders = new ArrayList<TimelineBuilder>();
	private TimelineBuilder introduction;
	
	private static Queue<String> messageQueue = new LinkedList<String>();
	private static Queue<IntensityChange> removalQueue = new LinkedList<IntensityChange>();
	
	private StanleyInterfaces interfaces;
	private WebPuller puller;
	private TextUpdater updater;
	
	public Appartment(WebPuller puller, TextUpdater updater, StanleyInterfaces interfaces) {
		super("Appartment", interfaces);
		
		this.interfaces = interfaces;
		this.puller = puller;
		this.updater = updater;
		
		messageQueue.add("F Button");
		messageQueue.add("I Button");
		messageQueue.add("Q Button");
		messageQueue.add("Watch TV");
		messageQueue.add("Spend time with the boys");
		messageQueue.add("Prepare dinner");
		messageQueue.add("tell kids a story");
		messageQueue.add("tell wife you love her");
		messageQueue.add("go to sleep");
		messageQueue.add("be at work in the morning");
		messageQueue.add("question nothing");
		
        removalQueue.add(new IntensityChange(lights.area1Top, 0));
        removalQueue.add(new IntensityChange(lights.area3Top, 0));
        removalQueue.add(new IntensityChange(lights.area2Front, 0));
        removalQueue.add(new IntensityChange(lights.secondTop, 0));
        removalQueue.add(new IntensityChange(lights.area2Top, 0));
        
        loadIntroduction();
		loadTimelines();
	}
	
	@Override
	public void run()
	{
		executeTimeline(introduction);
		Iterator<TimelineBuilder> iterator = builders.iterator();
		while(iterator.hasNext())
		{
			TimelineBuilder builder = iterator.next();
			executeTimeline(builder);
			
			if(iterator.hasNext())
			{
				String nextMessage = messageQueue.remove();
				WebRaceDecisionMaker maker = new WebRaceDecisionMaker(puller, updater, nextMessage);
				DecisionPoint decisionPoint = new DecisionPoint(nextMessage, maker, interfaces, false);
				decisionPoint.run();
			}
		}
	}
	
	private void executeTimeline(TimelineBuilder builder)
	{
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		Event event = new ViewShowEvent(timelineView, timeline.getDuration());
		event.start();
		try {
			Thread.sleep(event.getDuration().toMillis());
		} catch (InterruptedException e) {
			return;
		}
	}
	
	public void loadIntroduction()
	{
		TimelineBuilder builder = new TimelineBuilder();
		
		Duration bringUpOffice = Duration.ofSeconds(3); 
		LightingView upOffice = lightingViews.getOfficeView(bringUpOffice);
		Event upOfficeEvent = new ViewShowEvent(upOffice, bringUpOffice);
		builder.addEvent(upOfficeEvent, Duration.ZERO);
		
		TimelineSequence narrTimelineSequence = builder.addTimelineSequence(upOfficeEvent);
		AudioSequence firstNarration = new AudioSequence(narrTimelineSequence, soundViewFactory, Volumes.NARRATION_VOLUME);

		firstNarration.addAudio(sounds.phone1, Duration.ofSeconds(6));
		firstNarration.addAudio(sounds.phone2, Duration.ofSeconds(4));
		firstNarration.addAudio(sounds.phone3, Duration.ofSeconds(13));
		firstNarration.addAudio(sounds.phone4, Duration.ofMillis(9500));
		firstNarration.addAudio(sounds.phone5, Duration.ofSeconds(2));
		firstNarration.addAudio(sounds.phone6, Duration.ofSeconds(3));
		Event prePhoneTaunt = firstNarration.addAudio(sounds.phone7, Duration.ofSeconds(2));

		firstNarration.addAudio(sounds.phoneCallTaunt, Duration.ofSeconds(12), Duration.ofSeconds(3));
		
		TimelineSequence phoneRingTimelineSequence = builder.addTimelineSequence(prePhoneTaunt);
		AudioSequence phoneRingSequence = new AudioSequence(phoneRingTimelineSequence, soundViewFactory, Volumes.NARRATION_VOLUME);
		
		Sound phoneSound = new UniqueSound(sounds.phoneRing);
		Event phoneRing = phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		phoneRingSequence.addAudio(phoneSound, Duration.ofSeconds(3));
		
		Duration topDownViewTime = Duration.ofSeconds(3); 
		LightingView topDownView = lightingViews.getOfficeViewToTopDown(topDownViewTime);
		builder.addEventWith(new ViewShowEvent(topDownView, topDownViewTime), phoneRing);
		
		introduction = builder;
	}
	
	private void loadTimelines()
	{
		SequenceDrawer drawer;
		AudioSequence seq;
		TimelineBuilder builder;
		
		/* SEQUENCE 1 */
		
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		seq.addAudio(sounds.appt1, Duration.ofSeconds(4));
		seq.addAudio(sounds.appt2, Duration.ofSeconds(7));
		seq.addAudio(sounds.appt3, Duration.ofSeconds(5));
		Event comeInside = seq.addAudio(sounds.appt4, Duration.ofSeconds(4));
		Event thisIsTheStory = seq.addAudio(sounds.appt5, Duration.ofSeconds(5));
				
		builder = drawer.builder();
		
		View officeLeft= screenViewFactory.createScreenView(leftScreen, media.officeLeft);
		View officeCenter = screenViewFactory.createScreenView(middleScreen, media.officeMiddle);
		View officeRight = screenViewFactory.createScreenView(rightScreen, media.officeRight);
		builder.addEventWith(new ViewShowEvent(officeLeft, Duration.ofSeconds(1)), comeInside);
		builder.addEventWith(new ViewShowEvent(officeCenter, Duration.ofSeconds(1)), comeInside);
		builder.addEventWith(new ViewShowEvent(officeRight, Duration.ofSeconds(1)), comeInside);
		
		Duration officeViewLength = Duration.ofSeconds(3); 
		LightingView officeView = lightingViews.getOfficeView(officeViewLength);
		builder.addEventWith(new ViewShowEvent(officeView, officeViewLength), comeInside);
		
		View stanleyLeft = screenViewFactory.createScreenView(leftScreen, media.pictureOfStanley);
		View stanleyCenter = screenViewFactory.createScreenView(middleScreen, media.pictureOfStanley);
		View stanleyRight = screenViewFactory.createScreenView(rightScreen, media.pictureOfStanley);
		
		Duration stanleyFaceLengthTime = Duration.ofSeconds(6);
		
		builder.addEventWith(new ViewShowEvent(stanleyLeft, stanleyFaceLengthTime), thisIsTheStory);
		builder.addEventWith(new ViewShowEvent(stanleyCenter, stanleyFaceLengthTime), thisIsTheStory);
		builder.addEventWith(new ViewShowEvent(stanleyRight, stanleyFaceLengthTime), thisIsTheStory);

		/* SEQUENCE 2 */
		
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		Event lookAtOrders = seq.addAudio(sounds.appt6, Duration.ofSeconds(11));
		
		builder = drawer.builder();
		
		View ordersOne = screenViewFactory.createScreenView(leftScreen, media.ordersOne);
		View ordersThree = screenViewFactory.createScreenView(rightScreen, media.ordersThree);
		
		Duration ordersLength = Duration.ofSeconds(9);
		builder.addEventWith(new ViewShowEvent(ordersOne, ordersLength), lookAtOrders);
		builder.addEventWith(new ViewShowEvent(ordersThree, ordersLength), lookAtOrders);

		/* SEQUENCE 3 */
		
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		seq.addAudio(sounds.appt7, Duration.ofSeconds(14)); //pushing buttons
		seq.addAudio(sounds.appt8, Duration.ofSeconds(4));

		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		seq.addAudio(sounds.appt9, Duration.ofSeconds(6));
		Event scenery = seq.addAudio(sounds.appt10, Duration.ofSeconds(10));
		Event office = seq.addAudio(sounds.appt11, Duration.ofSeconds(6));
		
		builder = drawer.builder();
		View leftScreenView = screenViewFactory.createScreenView(leftScreen, media.landscapes);
		View rightScreenView = screenViewFactory.createScreenView(rightScreen, media.landscapes);
		builder.addEventWith(new ViewShowEvent(leftScreenView, Duration.ofSeconds(1)), scenery);
		builder.addEventWith(new ViewShowEvent(rightScreenView, Duration.ofSeconds(1)), scenery);
		
		View leftOffice = screenViewFactory.createScreenView(leftScreen, media.officeLeft);
		View rightOffice = screenViewFactory.createScreenView(rightScreen, media.officeRight);
		Duration officeDuration = Duration.ofSeconds(10);
		builder.addEventWith(new ViewShowEvent(leftOffice, officeDuration), office);
		builder.addEventWith(new ViewShowEvent(rightOffice, officeDuration), office);

		/* SEQUENCE 4 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		Event vanished = seq.addAudio(sounds.appt12, Duration.ofMillis(10500)); //10.5 people vanished off the face of the earth
		seq.addAudio(sounds.appt13, Duration.ofSeconds(9));
		
		/* SEQUENCE 5 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		Event twoOpenDoors = seq.addAudio(sounds.appt14, Duration.ofSeconds(9)); //two open doors
		seq.addAudio(sounds.appt15, Duration.ofSeconds(10));
		
		builder = drawer.builder();
		View leftDoor = screenViewFactory.createScreenView(leftScreen, media.doorsLeft);
		View middleBlack = screenViewFactory.createBlackout(middleScreen);
		View rightDoor = screenViewFactory.createScreenView(rightScreen, media.doorsRight);
		Duration doorLength = Duration.ofSeconds(4);
		builder = drawer.builder();
		builder.addEventWith(new ViewShowEvent(leftDoor, doorLength), twoOpenDoors);
		builder.addEventWith(new ViewShowEvent(rightDoor, doorLength), twoOpenDoors);

		/* SEQUENCE 6 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		seq.addAudio(sounds.appt16, Duration.ofSeconds(7));
		Event mindControlFacility = seq.addAudio(sounds.appt17, Duration.ofSeconds(13)); //mind control facility
		Event theStanleyParable = seq.addAudio(sounds.appt18, Duration.ofSeconds(2));
		
		builder = drawer.builder();
		Duration blankDuration = Duration.ofSeconds(2);
		View leftBlank = screenViewFactory.createScreenView(leftScreen, media.blackMonitors);
		View rightBlank = screenViewFactory.createScreenView(rightScreen, media.blackMonitors);
		builder.addEventWith(new ViewShowEvent(leftBlank, blankDuration), mindControlFacility);
		builder.addEventWith(new ViewShowEvent(rightBlank, blankDuration), mindControlFacility);
		
		View stanleyParableLeft = screenViewFactory.createScreenView(leftScreen, media.stanleyParable);
		View stanleyParableRight = screenViewFactory.createScreenView(rightScreen, media.stanleyParable);
		builder.addEventWith(new ViewShowEvent(stanleyParableLeft, blankDuration), theStanleyParable);
		builder.addEventWith(new ViewShowEvent(stanleyParableRight, blankDuration), theStanleyParable);

		/* SEQUENCE 7 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		addLightRemoval(drawer.builder());
		seq.addAudio(sounds.appt19, Duration.ofSeconds(14));
		seq.addAudio(sounds.appt20, Duration.ofSeconds(7));

		/* SEQUENCE 8 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		addLightRemoval(drawer.builder());
		seq.addAudio(sounds.appt21, Duration.ofSeconds(4));
		seq.addAudio(sounds.appt22, Duration.ofSeconds(12));

		/* SEQUENCE 9 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		addLightRemoval(drawer.builder());
		seq.addAudio(sounds.appt23, Duration.ofSeconds(13));
		seq.addAudio(sounds.appt24, Duration.ofSeconds(8)); // do not press button

		/* SEQUENCE 10 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		addLightRemoval(drawer.builder());
		seq.addAudio(sounds.appt25, Duration.ofSeconds(10));
		seq.addAudio(sounds.appt26, Duration.ofSeconds(4));

		/* SEQUENCE 11 */
		drawer = new SequenceDrawer(this);
		seq = drawer.seq();
		seq.addAudio(sounds.appt27, Duration.ofSeconds(4));
		seq.addAudio(sounds.appt28, Duration.ofSeconds(14));
		seq.addAudio(sounds.appt29, Duration.ofSeconds(6));
		Event lastCue = seq.addAudio(sounds.appt30, Duration.ofSeconds(11));
		
		LightingView view = new LightingView(lightingLoop, Duration.ZERO);
		view.addChange(removalQueue.remove());
		drawer.builder().addEventAfterEvent(new ViewShowEvent(view, Duration.ZERO), lastCue);
	}
	
	private void addLightRemoval(TimelineBuilder builder)
	{
		LightingView view = new LightingView(lightingLoop, Duration.ZERO);
		view.addChange(removalQueue.remove());
		builder.addEvent(new ViewShowEvent(view, Duration.ZERO), Duration.ZERO);
	}
	
	private class SequenceDrawer
	{
		TimelineBuilder timelineBuilder;
		
		public SequenceDrawer(Appartment appartment)
		{
			timelineBuilder = new TimelineBuilder();
			
			appartment.builders.add(timelineBuilder);
		}
		
		public AudioSequence seq()
		{
			AudioSequence sequence = new AudioSequence(timelineBuilder.addTimelineSequence(), soundViewFactory, Volumes.NARRATION_VOLUME);
			return sequence;
		}
		
		public TimelineBuilder builder()
		{
			return timelineBuilder;
		}
	}
	
	

}
