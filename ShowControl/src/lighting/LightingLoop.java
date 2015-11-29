package lighting;

import java.util.logging.Logger;

import controls.timeline.Timeline;
import lighting.intruments.InstrumentCollection;

/**
 * A "game loop" that renders a new lighting view
 * @author russell
 *
 */
public class LightingLoop implements Runnable {
	
	private static Logger logger = Logger.getLogger("LightingLoop");
	
	private InstrumentCollection channels;
	private DMXFrameTransmitter transmitter;
	
	private Timeline timeline;
	
	private boolean stopped = false;
	
	public LightingLoop(InstrumentCollection channels, DMXFrameTransmitter transmitter)
	{
		this.channels = channels;
		this.transmitter = transmitter;
	}

	public synchronized void stop()
	{
		logger.info("Stopping lighting loop");
		stopped = true;
		notify();
	}
	
	@Override
	public void run() {
		Thread timelineThread = new Thread(timeline);
		timelineThread.start();
		
		try {
			while(!stopped)
			{
				logger.info("Waiting for a new render request");
				synchronized(this) {
					wait();
				}
				
				logger.info("Sending DMX signal");
				transmitter.send(channels);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			this.transmitter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Signal that the lights have been updated
	 */
	public synchronized void update()
	{
		notify();
	}
}
