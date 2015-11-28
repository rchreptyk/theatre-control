package controls;

import java.time.Duration;

public interface Event extends Action {

	public Duration getDuration();
	
	public void start();

}
