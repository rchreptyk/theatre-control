package stanley;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

import controls.Event;
import controls.Sequence;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineView;
import lighting.LightingLoop;
import lighting.OutputStreamDMXTransmitter;
import media.MessageTransmitter;
import media.Screen;
import media.ScreenViewFactory;
import media.SoundViewFactory;

public class StanleyParable implements AutoCloseable{
	private OutputStream mediaPipe;
	private MessageTransmitter mediaTransmitter;
	private ScreenViewFactory screenViewFact;
	private SoundViewFactory soundViewFact;
	
	private Screen leftScreen = new Screen("left");
	private Screen middleScreen = new Screen("center");
	private Screen rightScreen = new Screen("right");
	
	private Socket lightingSocket;
	private OutputStreamDMXTransmitter dmxTransmitter;
	private LightingLoop loop;	
	
	private StanleyMediaConfig media = new StanleyMediaConfig();
	private StanleyLightingConfig lightingConfig = new StanleyLightingConfig();
	private StanleySoundConfig sounds = new StanleySoundConfig(); 
	
	public StanleyParable() throws UnknownHostException, IOException
	{
		mediaPipe = new FileOutputStream("/Users/russell/Desktop/Workspace/stanely/program/media_pipe");
		mediaTransmitter = new MessageTransmitter(mediaPipe);
		screenViewFact = new ScreenViewFactory(mediaTransmitter);
		soundViewFact = new SoundViewFactory(mediaTransmitter);
		
		lightingSocket = new Socket("localhost", 5000);
		dmxTransmitter = new OutputStreamDMXTransmitter(lightingSocket.getOutputStream());
	}
	
	public void beginShow()
	{
		startLightingLoop();
		
		Sequence sequence;
		sequence = getIntroduction();
		sequence.run();
		
//		LightingView view = new LightingView(loop, Duration.ofSeconds(5));
//		view.addChange(new IntensityChange(config.area2Top, 80));
//		view.addChange(new IntensityChange(config.area2Front, 80));
//		view.addChange(new IntensityChange(config.area2Left45, 80));
//		view.addChange(new IntensityChange(config.area2Right45, 80));
//		
//		LightingView reduced = new LightingView(loop, Duration.ofSeconds(5));
//		reduced.addChange(new IntensityChange(config.area2Top, 40));
//		reduced.addChange(new IntensityChange(config.area2Front, 40));
//		reduced.addChange(new IntensityChange(config.area2Left45, 40));
//		reduced.addChange(new IntensityChange(config.area2Right45, 40));
//		
//		Sequence sequence = new Sequence("Introduction");
//		sequence.addEvent(new ViewShowEvent(view, Duration.ofSeconds(30)));
//		sequence.addEvent(new ViewShowEvent(reduced, Duration.ofSeconds(30)));
//		sequence.addEvent(new ViewShowEvent(view, Duration.ofSeconds(30)));
//		sequence.run();
		
		stopLightingLoop();
	}
	
	public Sequence GetPreshow()
	{
		return null;
	}
	
	public Sequence getIntroduction()
	{
		Sequence sequence = new Sequence("Introduction");
		
		TimelineBuilder builder = new TimelineBuilder();
		
		// Narration
		View introNarr = soundViewFact.createSoundView(sounds.intro1, 80);
		Event event = new ViewShowEvent(introNarr, Duration.ofMillis(4000));
		builder.addEvent(event, Duration.ZERO);
		
		introNarr = soundViewFact.createSoundView(sounds.intro2, 80);
		Event nextEvent = new ViewShowEvent(introNarr, Duration.ofSeconds(6));
		builder.addEventAfterEvent(nextEvent, event);
		event = nextEvent;
		
		// Media
		
		View stanleyLeft = screenViewFact.createScreenView(leftScreen, media.pictureOfStanley);
		View stanleyCenter = screenViewFact.createScreenView(middleScreen, media.pictureOfStanley);
		View stanleyRight = screenViewFact.createScreenView(rightScreen, media.pictureOfStanley);
		
		Duration stanleyFaceTime = Duration.ofSeconds(2);
		Duration stanleyFaceLengthTime = Duration.ofSeconds(5);
		
		builder.addEvent(new ViewShowEvent(stanleyLeft, stanleyFaceLengthTime), stanleyFaceTime);
		builder.addEvent(new ViewShowEvent(stanleyCenter, stanleyFaceLengthTime), stanleyFaceTime);
		builder.addEvent(new ViewShowEvent(stanleyRight, stanleyFaceLengthTime), stanleyFaceTime);
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));		
				
		return sequence;
	}
	
	
	private void startLightingLoop()
	{
		loop = new LightingLoop(lightingConfig.getCollection(), dmxTransmitter);
		
		Thread thread = new Thread(loop);
		thread.start();
	}
	
	private void stopLightingLoop()
	{
		loop.stop();
	}
	
	public static void main(String args[])
	{
		try(StanleyParable stanleyParable = new StanleyParable())
		{
			stanleyParable.beginShow();
		} catch (UnknownHostException e) {
			System.out.println("Cannot find lighting host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Cannot connect to lighting host");
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		this.mediaTransmitter.close();
	}
}
