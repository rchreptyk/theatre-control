package stanley;

import lighting.intruments.BasicLight;
import lighting.intruments.LightingConfiguration;

public class StanleyLightingConfig extends LightingConfiguration{
	public BasicLight area2Top = addBasicLight("Area 2 Top", 38);
	public BasicLight area2Front = addBasicLight("Area 2 Front", 19);
	public BasicLight area2Left45 = addBasicLight("Area 2 Left-45", 23);
	public BasicLight area2Right45 = addBasicLight("Area 2 right-45", 35);
}
