package stanley;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import controls.Sequence;
import lighting.LightingLoop;
import lighting.OutputStreamDMXTransmitter;
import media.MessageTransmitter;
import media.Screen;
import media.ScreenViewFactory;
import media.SoundViewFactory;
import stanley.views.Introduction;

public class StanleyParable implements AutoCloseable{
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
				
		Introduction intro = new Introduction(interfaces);
		intro.run();
		
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
