package stanley.views;

import java.time.Duration;

import controls.ViewShowEvent;
import stanley.StanleyInterfaces;
import stanley.TestPromptEvent;

public class TestSequence extends StanleyScene {

	public TestSequence(StanleyInterfaces interfaces) {
		super("TestSequence", interfaces);
		
		final Duration viewTestFadeIn = Duration.ofSeconds(2);
		final Duration viewTestLength = viewTestFadeIn.plus(Duration.ofSeconds(8));
		
		ViewShowEvent testEvent = new ViewShowEvent(lightingViews.getOfficeView(viewTestFadeIn), viewTestLength);
		sequence.addEvent(testEvent);
		
		sequence.addEvent(new TestPromptEvent());
		
		
	}

}
