package stanley.views;

import java.time.Duration;

import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import stanley.StanleyInterfaces;
import stanley.TestPromptEvent;

public class PostShow extends StanleyScene {

	public PostShow(StanleyInterfaces interfaces) {
		super("Post Show", interfaces);
		
		sequence.addEvent(new WaitEvent(Duration.ofSeconds(5)));
		
		View leftView = screenViewFactory.createScreenView(leftScreen, media.bigFour);
		View middleView = screenViewFactory.createScreenView(middleScreen, media.bigTwo);
		View rightView = screenViewFactory.createScreenView(rightScreen, media.bigSeven);
		
		sequence.addEvent(new ViewShowEvent(leftView, Duration.ofSeconds(1)));
		sequence.addEvent(new ViewShowEvent(middleView, Duration.ofSeconds(1)));
		sequence.addEvent(new ViewShowEvent(rightView, Duration.ofSeconds(1)));
		
		sequence.addEvent(new ViewShowEvent(lightingViews.getHouseView(Duration.ofSeconds(5)), Duration.ofSeconds(5)));
		sequence.addEvent(new TestPromptEvent());
	}
}
