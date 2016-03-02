package stanley.views;

import java.time.Duration;

import controls.Sequence;
import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import stanley.StanleyInterfaces;
import stanley.TestPromptEvent;
import stanley.Volumes;

public class TestSequence extends StanleyScene {
	
	private Sequence sequence2 = new Sequence("Exit2");

	public TestSequence(StanleyInterfaces interfaces) {
		super("TestSequence", interfaces);
		
		
		ViewShowEvent testEvent = new ViewShowEvent(lightingViews.getTestView(Duration.ZERO), Duration.ofSeconds(1));
		sequence.addEvent(testEvent);
		
		View leftScreenView = screenViewFactory.createScreenView(leftScreen, media.testKitten);
		View middleScreenView = screenViewFactory.createScreenView(middleScreen, media.testKitten);
		View rightScreenView = screenViewFactory.createScreenView(rightScreen, media.testKitten);
		
		sequence.addEvent(new ViewShowEvent(leftScreenView, Duration.ofSeconds(1)));
		sequence.addEvent(new ViewShowEvent(middleScreenView, Duration.ofSeconds(1)));
		sequence.addEvent(new ViewShowEvent(rightScreenView, Duration.ofSeconds(1)));
		
		View musicView = soundViewFactory.createSoundView(sounds.testSequenceMusic1, Volumes.MUSIC_VOLUME);
		sequence.addEvent(new ViewShowEvent(musicView, Duration.ofSeconds(61)));
		
		
		testEvent = new ViewShowEvent(lightingViews.getTestViewOff(Duration.ZERO), Duration.ofSeconds(1));
		sequence2.addEvent(testEvent);
		
		leftScreenView = screenViewFactory.createBlackout(leftScreen);
		middleScreenView = screenViewFactory.createBlackout(middleScreen);
		rightScreenView = screenViewFactory.createBlackout(rightScreen);
		
		sequence2.addEvent(new ViewShowEvent(leftScreenView, Duration.ofSeconds(1)));
		sequence2.addEvent(new ViewShowEvent(middleScreenView, Duration.ofSeconds(1)));
		sequence2.addEvent(new ViewShowEvent(rightScreenView, Duration.ofSeconds(1)));
		
		sequence2.addEvent(new WaitEvent(Duration.ofSeconds(5)));
	}
	
	public void run()
	{
		sequence.run();
		new TestPromptEvent().start();
		sequence2.run();
	}

}
