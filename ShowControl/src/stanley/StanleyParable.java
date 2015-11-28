package stanley;

import java.io.IOException;
import java.net.Socket;
import java.time.Duration;

import controls.Sequence;
import controls.ViewShowEvent;
import lighting.LightingLoop;
import lighting.LightingView;
import lighting.OutputStreamDMXTransmitter;
import lighting.change.IntensityChange;
import media.FileVideo;
import media.MessageTransmitter;
import media.Screen;
import media.ScreenView;
import media.ScreenViewFactory;

public class StanleyParable {
	
	private MessageTransmitter mediaTransmitter;
	private ScreenViewFactory factory;
	
	private Screen leftScreen = new Screen("left");
	private Screen middleScreen = new Screen("center");
	private Screen rightScreen = new Screen("right");
	
	public StanleyParable()
	{
		mediaTransmitter = new MessageTransmitter(System.out);
		factory = new ScreenViewFactory(mediaTransmitter);
	}
	
	public void beginShow()
	{
		try(Socket socket = new Socket("localhost", 5000))
		{
			StanleyLightingConfig config = new StanleyLightingConfig();
			
			OutputStreamDMXTransmitter transmitter = new OutputStreamDMXTransmitter(socket.getOutputStream());
			LightingLoop loop = new LightingLoop(config.getCollection(), transmitter);
			
			Thread thread = new Thread(loop);
			thread.start();
			
			LightingView view = new LightingView(loop, Duration.ofSeconds(5));
			view.addChange(new IntensityChange(config.area2Top, 80));
			view.addChange(new IntensityChange(config.area2Front, 80));
			view.addChange(new IntensityChange(config.area2Left45, 80));
			view.addChange(new IntensityChange(config.area2Right45, 80));
			
			LightingView reduced = new LightingView(loop, Duration.ofSeconds(5));
			reduced.addChange(new IntensityChange(config.area2Top, 40));
			reduced.addChange(new IntensityChange(config.area2Front, 40));
			reduced.addChange(new IntensityChange(config.area2Left45, 40));
			reduced.addChange(new IntensityChange(config.area2Right45, 40));
			
			Sequence sequence = new Sequence("Introduction");
			sequence.addEvent(new ViewShowEvent(view, Duration.ofSeconds(30)));
			sequence.addEvent(new ViewShowEvent(reduced, Duration.ofSeconds(30)));
			sequence.addEvent(new ViewShowEvent(view, Duration.ofSeconds(30)));
			sequence.run();
			
			loop.stop();
		}
		catch(IOException e)
		{
			System.out.println("Failed to connect to lighting");
		}
		
		
	}
	
	public Sequence GetPreshow()
	{
		return null;
	}
	
	public Sequence GetIntroduction()
	{
		ScreenView landscapesView = factory.createScreenView(leftScreen, new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/landscapes.mp4"));
		ScreenView landscapesView2 = factory.createScreenView(middleScreen, new FileVideo("/Users/russell/Desktop/Workspace/stanely/media/landscapes.mp4"));
		
		Sequence sequence = new Sequence("Introduction");
		sequence.addEvent(new ViewShowEvent(landscapesView, Duration.ofSeconds(30)));
		sequence.addEvent(new ViewShowEvent(landscapesView2, Duration.ofSeconds(30)));
		
		return sequence;
	}
	
	public static void main(String args[])
	{
		StanleyParable stanleyParable = new StanleyParable();
		stanleyParable.beginShow();
	}
}
