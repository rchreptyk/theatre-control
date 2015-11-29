package media;

public class FileSound implements Sound{
	private String name;
	private String path;
	
	public FileSound(String name, String path)
	{
		this.name = name;
		this.path = path;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSoundLocator() {
		return path;
	}
	
	
}
