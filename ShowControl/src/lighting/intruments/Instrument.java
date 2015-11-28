package lighting.intruments;

import java.util.Set;

import lighting.DMXChannel;

public interface Instrument {
	
	String getName();
	
	Set<DMXChannel> getLevels();
}
