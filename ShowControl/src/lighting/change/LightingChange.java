package lighting.change;

import java.util.List;

public interface LightingChange {

	/**
	 * Request the lighting change to occur. Lighting changes can be 
	 * requested multiple times since the lighting scheduler may
	 * decide not to implement the change and re-request the change
	 * to occur again later.
	 */
	public void execute();
	
	public List<LightingChange> getIntermediateChanges(int count);
}
