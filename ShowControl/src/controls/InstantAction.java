package controls;

public class InstantAction implements Action{

	private InstantAction() { }
	
	public static final InstantAction ACTION = new InstantAction(); 
	
	@Override
	public boolean hasCompleted() {
		return true;
	}

	@Override
	public void stop() { }

}
