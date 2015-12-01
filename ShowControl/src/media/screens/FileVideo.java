package media.screens;

public class FileVideo implements Video {
	private String path;
	
	public FileVideo(String path)
	{
		this.path = path;
	}

	@Override
	public String getVideoMessage() {
		return path;
	}
}
