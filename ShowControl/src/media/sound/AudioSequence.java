package media.sound;

import java.time.Duration;

import controls.Event;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.TimelineSequence;

public class AudioSequence {
	private TimelineSequence sequence;
	private int volume;
	private SoundViewFactory factory;
	
	public AudioSequence(TimelineSequence sequence, SoundViewFactory factory, int volume)
	{
		this.factory = factory;
		this.sequence = sequence;
		this.volume = volume;
	}
	
	public Event addAudio(Sound sound, Duration soundDuration)
	{
		return addAudio(sound, soundDuration, Duration.ZERO);
	}
	
	public Event addAudio(Sound sound, Duration soundDuration, Duration delay)
	{
		View narrationView = factory.createSoundView(sound, volume);
		Event event = new ViewShowEvent(narrationView, soundDuration);
		sequence.addEvent(event, delay);
		
		return event;
	}
}