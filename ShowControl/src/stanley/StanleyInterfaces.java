package stanley;

import lighting.LightingLoop;
import media.Screen;
import media.ScreenViewFactory;
import media.SoundViewFactory;

public final class StanleyInterfaces {
	private StanleyLightingConfig lights;
	private StanleyMediaConfig media;
	private StanleySoundConfig sounds;
	
	private SoundViewFactory soundViewFactory;
	private ScreenViewFactory screenViewFactory;
	private LightingLoop lightingLoop;
	
	private Screen leftScreen;
	private Screen middleScreen;
	private Screen rightScreen;
	
	public Screen getLeftScreen() {
		return leftScreen;
	}
	public void setLeftScreen(Screen leftScreen) {
		this.leftScreen = leftScreen;
	}
	public Screen getMiddleScreen() {
		return middleScreen;
	}
	public void setMiddleScreen(Screen middleScreen) {
		this.middleScreen = middleScreen;
	}
	public Screen getRightScreen() {
		return rightScreen;
	}
	public void setRightScreen(Screen rightScreen) {
		this.rightScreen = rightScreen;
	}
	public StanleyLightingConfig getLights() {
		return lights;
	}
	public void setLights(StanleyLightingConfig lights) {
		this.lights = lights;
	}
	public StanleyMediaConfig getMedia() {
		return media;
	}
	public void setMedia(StanleyMediaConfig media) {
		this.media = media;
	}
	public StanleySoundConfig getSounds() {
		return sounds;
	}
	public void setSounds(StanleySoundConfig sounds) {
		this.sounds = sounds;
	}
	public SoundViewFactory getSoundViewFactory() {
		return soundViewFactory;
	}
	public void setSoundViewFactory(SoundViewFactory soundViewFactory) {
		this.soundViewFactory = soundViewFactory;
	}
	public ScreenViewFactory getScreenViewFactory() {
		return screenViewFactory;
	}
	public void setScreenViewFactory(ScreenViewFactory screenViewFactory) {
		this.screenViewFactory = screenViewFactory;
	}
	public LightingLoop getLightingLoop() {
		return lightingLoop;
	}
	public void setLightingLoop(LightingLoop lightingLoop) {
		this.lightingLoop = lightingLoop;
	}
}
