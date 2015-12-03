package stanley;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.logging.Logger;

import controls.Sequence;
import lighting.LightingLoop;
import lighting.OutputStreamDMXTransmitter;
import media.MessageTransmitter;
import media.screens.Screen;
import media.screens.ScreenViewFactory;
import media.sound.SoundViewFactory;
import stanley.views.Appartment;
import stanley.views.Bosses;
import stanley.views.Death;
import stanley.views.DecisionPoint;
import stanley.views.Freedom;
import stanley.views.Introduction;
import stanley.views.Lounge;
import stanley.views.MeetingRoom;
import stanley.views.MindControl;
import stanley.views.Office;
import stanley.views.PostShow;
import stanley.views.Preshow;
import stanley.views.TestSequence;

public class StanleyParable implements AutoCloseable{
	private static Logger LOG = Logger.getLogger("StanleyParable");
	
	private OutputStream mediaPipe;
	private MessageTransmitter mediaTransmitter;
	
	private StanleyInterfaces interfaces = new StanleyInterfaces();
	
	private Socket lightingSocket;
	private OutputStreamDMXTransmitter dmxTransmitter;
	private LightingLoop loop;	
	
	public StanleyParable() throws UnknownHostException, IOException
	{
		mediaPipe = new FileOutputStream("/Users/russell/Desktop/Workspace/stanely/program/media_pipe");
		mediaTransmitter = new MessageTransmitter(mediaPipe);
		interfaces.setScreenViewFactory(new ScreenViewFactory(mediaTransmitter));
		interfaces.setSoundViewFactory(new SoundViewFactory(mediaTransmitter));
		
		lightingSocket = new Socket("localhost", 5000);
		dmxTransmitter = new OutputStreamDMXTransmitter(lightingSocket.getOutputStream());
		
		interfaces.setMedia(new StanleyMediaConfig());
		interfaces.setSounds(new StanleySoundConfig());
		
		interfaces.setLeftScreen(new Screen("left"));
		interfaces.setMiddleScreen(new Screen("center"));
		interfaces.setRightScreen(new Screen("right"));
		
		StanleyLightingConfig lightingConfig = new StanleyLightingConfig();
		interfaces.setLights(lightingConfig);
		
		loop = new LightingLoop(lightingConfig.getCollection(), dmxTransmitter);
		interfaces.setLightingLoop(loop);
	}
	
	public void beginShow()
	{
		startLightingLoop();
		
//		TestSequence testSequence = new TestSequence(interfaces);
		Preshow preshow = new Preshow(interfaces);
		Introduction intro = new Introduction(interfaces);
		Office office = new Office(interfaces);
		Lounge lounge = new Lounge(interfaces);
		MeetingRoom meeting = new MeetingRoom(interfaces);
		Bosses bosses = new Bosses(interfaces);
		MindControl mindControl = new MindControl(interfaces);
		Freedom freedom = new Freedom(interfaces);
		Appartment appartment = new Appartment(interfaces);
		Death death = new Death(interfaces);
		PostShow postshow = new PostShow(interfaces);
		
		DecisionMaker twoDoorsDecider = new MockDecisionMaker("right", Duration.ofSeconds(30)); //left/right
		DecisionMaker turnOffOnMachineDecider = new MockDecisionMaker("on", Duration.ofSeconds(30)); //on/off
		DecisionMaker straightOfLeftDecider = new MockDecisionMaker("left", Duration.ofSeconds(30)); //left/straight
		
		DecisionPoint twoDoors = new DecisionPoint("Two Doors", twoDoorsDecider, interfaces);
		DecisionPoint turnOffOnMachine = new DecisionPoint("On Off Machine", turnOffOnMachineDecider, interfaces);
		DecisionPoint straightOrLeft = new DecisionPoint("Straight of Left", straightOfLeftDecider, interfaces);
		
		boolean goToAppartment = false;
		
		LOG.info("Preshow");
		preshow.run();
	
		LOG.info("Running intro");
		intro.run();
		
		LOG.info("Running office");
		office.run();
		
		LOG.info("Two Doors");
		twoDoors.run();
		
		if(twoDoors.getResult().equals("right"))
		{
			LOG.info("Running lounge");
			lounge.run();
			
			LOG.info("Straight or left");
			straightOrLeft.run();
			
			goToAppartment = straightOrLeft.getResult().equals("straight");
		}
		
		if(goToAppartment)
		{
			LOG.info("Appartment scene");
			appartment.run();
		}
		else
		{
			LOG.info("Running meeting");
			meeting.run();
			
			LOG.info("Running bosses");
			bosses.run();
			
			LOG.info("Running mind control");
			mindControl.run();
			
			turnOffOnMachine.run();
			
			if(turnOffOnMachine.getResult().equals("off"))
			{
				LOG.info("Running freedom");
				freedom.run();
			}
			else
			{
				LOG.info("Death");
				death.run();
			}
		}
		
		postshow.run();
		
		stopLightingLoop();
	}
	
	public Sequence GetPreshow()
	{
		return null;
	}
	
	private void startLightingLoop()
	{
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
