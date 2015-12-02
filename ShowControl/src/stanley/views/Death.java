package stanley.views;

import java.time.Duration;

import controls.Event;
import controls.View;
import controls.ViewShowEvent;
import controls.timeline.Timeline;
import controls.timeline.TimelineBuilder;
import controls.timeline.TimelineSequence;
import controls.timeline.TimelineView;
import lighting.LightingLoopUpdateEvent;
import lighting.LightingView;
import lighting.change.CompoundLightingChange;
import lighting.change.IntensityChange;
import lighting.change.LightingChangeEvent;
import lighting.intruments.BasicLight;
import media.sound.AudioSequence;
import stanley.StanleyInterfaces;

public class Death extends StanleyScene {

	public Death(StanleyInterfaces interfaces) {
		super("Death", interfaces);
	
		TimelineBuilder builder = new TimelineBuilder();
		TimelineSequence narrTimelineSeq = builder.addTimelineSequence();
		
		Duration freakOutTime = Duration.ofMillis(65000);
		Duration terminateTime = freakOutTime.plus(Duration.ofSeconds(34));
		
		AudioSequence narrSequence = new AudioSequence(narrTimelineSeq, soundViewFactory, sounds.NARRATION_VOLUME);
		narrSequence.addAudio(sounds.death1, Duration.ofSeconds(5));
		narrSequence.addAudio(sounds.death2, Duration.ofSeconds(9));
		narrSequence.addAudio(sounds.death3, Duration.ofSeconds(9));
		narrSequence.addAudio(sounds.death4, Duration.ofSeconds(11));
		narrSequence.addAudio(sounds.death5, Duration.ofSeconds(7));
		narrSequence.addAudio(sounds.death6, Duration.ofSeconds(17));
		narrSequence.addAudio(sounds.death7, Duration.ofSeconds(8));
		
		narrSequence.addAudio(sounds.countdown1, Duration.ofSeconds(5), Duration.ofSeconds(1));
		narrSequence.addAudio(sounds.countdown2, Duration.ofSeconds(7));
		narrSequence.addAudio(sounds.countdown3, Duration.ofSeconds(8));
		narrSequence.addAudio(sounds.countdown4, Duration.ofSeconds(4));
		narrSequence.addAudio(sounds.countdown5, Duration.ofSeconds(10));
		
		View deathMusic = soundViewFactory.createSoundView(sounds.deathMusic, 45);
		builder.addEvent(new ViewShowEvent(deathMusic, Duration.ofSeconds(34)), freakOutTime);
		
		View deathCountdownLeft = screenViewFactory.createScreenView(leftScreen, media.death);
		View deathCountdownMiddle = screenViewFactory.createScreenView(middleScreen, media.death);
		View deathCountdownRight = screenViewFactory.createScreenView(rightScreen, media.death);
		
		builder.addEvent(new ViewShowEvent(deathCountdownLeft, Duration.ofSeconds(34)), freakOutTime);
		builder.addEvent(new ViewShowEvent(deathCountdownMiddle, Duration.ofSeconds(34)), freakOutTime);
		builder.addEvent(new ViewShowEvent(deathCountdownRight, Duration.ofSeconds(34)), freakOutTime);
		
		View leftBlackout = screenViewFactory.createBlackout(leftScreen);
		View middleBlackout = screenViewFactory.createBlackout(middleScreen);
		View rightBlackout = screenViewFactory.createBlackout(rightScreen);
		
		builder.addEvent(new ViewShowEvent(leftBlackout, Duration.ofSeconds(5)), terminateTime);
		builder.addEvent(new ViewShowEvent(middleBlackout, Duration.ofSeconds(5)), terminateTime);
		builder.addEvent(new ViewShowEvent(rightBlackout, Duration.ofSeconds(5)), terminateTime);
	
		/* Lighting */
		
		Duration fadeLightTime = Duration.ofSeconds(10);
		LightingView officeView = lightingViews.getOfficeView(fadeLightTime);
		builder.addEvent(new ViewShowEvent(officeView, fadeLightTime), Duration.ZERO);
		
		LightingView officeViewOut = lightingViews.getOfficeViewBlack(Duration.ZERO);
		builder.addEvent(new ViewShowEvent(officeViewOut, fadeLightTime), freakOutTime);
		
		BasicLight[] flashing = new BasicLight[] { lights.audienceBlinderLeft, lights.audienceBlinderRight };
		BasicLight[] flashing2 = new BasicLight[] { lights.area1Top, lights.area2Top, lights.area3Top };
		
		boolean directionToggle = false;
		
		Duration currentTime = freakOutTime;
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.leftIQ, 80)), currentTime);
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.rightIQ, 80)), currentTime);
		
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.leftIQX, 35)), currentTime);
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.leftIQY, 0)), currentTime);
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.rightIQX, 34)), currentTime);
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.rightIQY, 0)), currentTime);
		
		for(int i = 0; i < (34000 / 500) - 1; i += 1)
		{
			CompoundLightingChange lightingChange = new CompoundLightingChange();
			
			lightingChange.add(new IntensityChange(flashing[i % flashing.length], 80));
			lightingChange.add(new IntensityChange(flashing2[i % flashing2.length], 80));
			
			if(i > 0)
			{
				lightingChange.add(new IntensityChange(flashing[(i - 1) % flashing.length], 0));
				lightingChange.add(new IntensityChange(flashing2[(i - 1) % flashing2.length], 0));
			}
			
			if(i % 5 == 0)
			{
				lightingChange.add(new IntensityChange(lights.leftIQY, directionToggle ? 0 : 100));
				lightingChange.add(new IntensityChange(lights.leftIQX, directionToggle ? 0 : 100));
				lightingChange.add(new IntensityChange(lights.rightIQY, directionToggle ? 100 : 0));
				lightingChange.add(new IntensityChange(lights.rightIQX, directionToggle ? 100 : 0));
				directionToggle = !directionToggle;
			}
			
			builder.addEvent(new LightingChangeEvent(lightingChange), currentTime);
			builder.addEvent(new LightingLoopUpdateEvent(lightingLoop), currentTime.plusMillis(1));
			
			currentTime = currentTime.plusMillis(500);
		}
		
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.leftIQ, 0)), terminateTime);
		builder.addEvent(new LightingChangeEvent(new IntensityChange(lights.rightIQ, 0)), terminateTime);
		
		CompoundLightingChange lightsOff = new CompoundLightingChange();
		
		for(BasicLight light : flashing)
			lightsOff.add(new IntensityChange(light, 0));
		
		for(BasicLight light : flashing2)
			lightsOff.add(new IntensityChange(light, 0));
		
		builder.addEvent(new LightingChangeEvent(lightsOff), terminateTime);
		builder.addEvent(new LightingLoopUpdateEvent(lightingLoop), terminateTime.plusMillis(1));
		
		Timeline timeline = builder.buildTimeline();
		TimelineView timelineView = new TimelineView(timeline);
		sequence.addEvent(new ViewShowEvent(timelineView, timeline.getDuration()));	

	}

}
