package stanley.views;

import java.time.Duration;

import controls.View;
import controls.ViewShowEvent;
import controls.WaitEvent;
import lighting.LightingView;
import stanley.StanleyInterfaces;

public class Preshow extends StanleyScene{

	public Preshow(StanleyInterfaces interfaces) {
		super("Preshow", interfaces);
		
		View leftView = screenViewFactory.createBlackout(leftScreen);
		View middleView = screenViewFactory.createBlackout(middleScreen);
		View rightView = screenViewFactory.createBlackout(rightScreen);
		
		sequence.addEvent(new ViewShowEvent(leftView, Duration.ZERO));
		sequence.addEvent(new ViewShowEvent(middleView, Duration.ZERO));
		sequence.addEvent(new ViewShowEvent(rightView, Duration.ZERO));
		
		LightingView houseOff = lightingViews.getHouseViewOff(Duration.ofSeconds(7));
		sequence.addEvent(new ViewShowEvent(houseOff, Duration.ofSeconds(7)));
		
		sequence.addEvent(new WaitEvent(Duration.ofSeconds(10)));
	}

}
