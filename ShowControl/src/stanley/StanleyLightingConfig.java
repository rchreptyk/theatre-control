package stanley;

import lighting.intruments.BasicLight;
import lighting.intruments.LightingConfiguration;

public class StanleyLightingConfig extends LightingConfiguration{
	
	public BasicLight houseOne = addBasicLight("Left Screen Top", 1);
	public BasicLight houseTwo = addBasicLight("Left Screen Area Front", 2);
	
	public BasicLight area2Top = addBasicLight("Area 2 Top", 41); //ashtons and ellies top
	public BasicLight area2Front = addBasicLight("Area 2 Front", 19); //other left 45, make dimm
	public BasicLight area2Left45 = addBasicLight("Area 2 Left-45", 23); //ashtons and ellies left
	public BasicLight area2Right45 = addBasicLight("Area 2 right-45", 35); //right 45
	
	//house
	// 107 108
	
	//53 right scroller intensity
	//(160 + 15) = 175 X
	//(160 + 16) = 176 Y
	
	//42 left scroller intensity
	//(160 + 0) 160
	//(160 + 1) 161
	
	public BasicLight warmBackLeft = addBasicLight("Warm back left", 3);
	public BasicLight warmBackRight = addBasicLight("Warm back right", 4);
}
