package media.sound;

public class UniqueSound  implements Sound {

	private Sound sound;
	private int count = 0;
	
	public UniqueSound(Sound sound)
	{
		this.sound = sound;
	}
	
	@Override
	public String getName() {
		count++;
		return sound.getName() + count;
	}

	@Override
	public String getSoundLocator() {
		return sound.getSoundLocator();
	}

}
