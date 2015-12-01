package media.sound;

import java.time.Duration;
import java.util.logging.Logger;

import controls.Action;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;
import controls.timeline.TimelineThread;
import media.MessageTransmitter;

public class SoundFadeView implements View {
	private Logger LOG = Logger.getLogger("SoundFadeView");
	
	private Sound sound;
	private Duration duration;
	private MessageTransmitter transmitter;
	
	private int fromVolume;
	private int newVolume;
	
	private final static Duration audioTickLength = Duration.ofMillis(500); 
	
	public SoundFadeView(MessageTransmitter transmitter, Sound sound, int fromVolume, int newVolume, Duration duration)
	{
		this.transmitter = transmitter;
		this.sound = sound;
		this.duration = duration;
		this.newVolume = newVolume;
		this.fromVolume = fromVolume;
	}

	@Override
	public Action render() {
		LOG.info("Fading out sound");
		
		long totalTime = duration.toMillis();
		long numberOfChanges = totalTime / audioTickLength.toMillis();
		int volumeChangeAmount = (int)((newVolume - fromVolume) / numberOfChanges);
		
		TimelineBuilder builder = new TimelineBuilder();
		TimelineSequence sequence = builder.addTimelineSequence();
		
		
		int currentVolume = fromVolume + volumeChangeAmount;
		
		for(int i = 0; i < numberOfChanges - 1; i++)
		{
			SoundVolumeView view = new SoundVolumeView(transmitter, sound, currentVolume);
			sequence.addEvent(new ViewShowEvent(view, audioTickLength));
			
			currentVolume += volumeChangeAmount;
		}
		
		SoundVolumeView view = new SoundVolumeView(transmitter, sound, newVolume);
		builder.addEvent(new ViewShowEvent(view, Duration.ZERO), duration);
		
		Timeline timeline = builder.buildTimeline();
		TimelineThread thread = new TimelineThread(timeline);
		thread.start();
		return thread;
	}
	
	
}
