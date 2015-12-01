package stanley.views;

import controls.Sequence;
import lighting.LightingLoop;
import media.screens.Screen;
import media.screens.ScreenViewFactory;
import media.sound.SoundViewFactory;
import stanley.StanleyInterfaces;
import stanley.StanleyLightingConfig;
import stanley.StanleyMediaConfig;
import stanley.StanleySoundConfig;

public abstract class StanleyScene {
	protected StanleyLightingConfig lights;
	protected StanleyMediaConfig media;
	protected StanleySoundConfig sounds;
	
	protected SoundViewFactory soundViewFactory;
	protected ScreenViewFactory screenViewFactory;
	protected LightingLoop lightingLoop;
	
	protected Screen leftScreen;
	protected Screen middleScreen;
	protected Screen rightScreen;
	
	protected Sequence sequence;
	
	public StanleyScene(String name, StanleyInterfaces interfaces)
	{
		this.media = interfaces.getMedia();
		this.lights = interfaces.getLights();
		this.sounds = interfaces.getSounds();
		this.soundViewFactory = interfaces.getSoundViewFactory();
		this.screenViewFactory = interfaces.getScreenViewFactory();
		this.lightingLoop = interfaces.getLightingLoop();
		this.leftScreen = interfaces.getLeftScreen();
		this.middleScreen = interfaces.getMiddleScreen();
		this.rightScreen = interfaces.getRightScreen();
		
		this.sequence = new Sequence(name);
	}
	
	public void run()
	{
		sequence.run();
	}
}
