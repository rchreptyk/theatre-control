package stanley;

import lighting.intruments.BasicLight;
import lighting.intruments.LightingConfiguration;

public class StanleyLightingConfig extends LightingConfiguration{
	
	public BasicLight houseOne = addBasicLight("House 1", 107, 100);
	public BasicLight houseTwo = addBasicLight("House 2", 108, 100);
	
	//Top Down
	public BasicLight area2Top = addBasicLight("Area 2 Top", 56); //my top
	
	//Office 
	public BasicLight secondTop = addBasicLight("Area 2 Second Top", 38); //secondTop
	public BasicLight area2Front = addBasicLight("Area 2 Front", 17); //was 19, now 17
	public BasicLight area3Top = addBasicLight("Area 3 top", 50); //right 45, hits right screen
	public BasicLight area1Top = addBasicLight("Area 1 top", 25); //right 45, hits right screen
	
	//Paintings
	public BasicLight rightPainting = addBasicLight("Right Painting", 13);
	public BasicLight middlePainting = addBasicLight("Middle Painting", 12);
	public BasicLight leftPainting = addBasicLight("Left Painting", 8);
	
	//Sunlight
	public BasicLight area2Left45 = addBasicLight("Area 2 Left-45", 23); //ashtons and ellies left
	public BasicLight area2Right45 = addBasicLight("Area 2 right-45", 46); //right 45, hits right screen
	
	//Decision
	public BasicLight backHallLightLeft = addBasicLight("Back hall left", 89);
	public BasicLight backHallLightRight = addBasicLight("Back hall right", 95);
	
	//Death
	public BasicLight audienceBlinderLeft = addBasicLight("Audience blinder left", 59);
	public BasicLight audienceBlinderRight = addBasicLight("Audience blinder right", 68);
	public BasicLight redTopLight = addBasicLight("Red top light", 67);
	public BasicLight leftOrangeLight = addBasicLight("Left orange light", 82);
	public BasicLight frontOrangeLight = addBasicLight("Front orange light", 20);
	public BasicLight audienceBlinderFarLeft = addBasicLight("Audience blinder far left", 44);
	public BasicLight audienceBlinderMiddle = addBasicLight("Audience blinder far middle", 57);
	public BasicLight audienceBlinderFarRight = addBasicLight("Audience blinder far right", 69);
	
	//IQs
	public BasicLight leftIQ = addBasicLight("Left IQ intensity", 9);
	public BasicLight leftIQY = addBasicLight("Left IQ Y", 200);
	public BasicLight leftIQX = addBasicLight("Left IQ X", 201);
	public BasicLight rightIQ = addBasicLight("Right IQ intensity", 33);
	public BasicLight rightIQY = addBasicLight("Right IQ Y", 202);
	public BasicLight rightIQX = addBasicLight("Right IQ X", 203);
	
	//Bosses Office
	public BasicLight rightWarmBack = addBasicLight("right warm back", 70);
	public BasicLight leftWarmBack = addBasicLight("left warm back", 45);
	public BasicLight rightWarmSide = addBasicLight("right warm side", 64);

	
//	public BasicLight sideLeftCoolLight= addBasicLight("Side left cool light", 81);
//	public BasicLight sideRightCoolLight= addBasicLight("Side right cool light", 47);
//	
	//public BasicLight hotTop = addBasicLight("Red Top", 67); //bleeds onto left screen
	//public BasicLight hotTop = addBasicLight("Red Top", 82); //cool orange light
	
	//81 cool light, 75, 47, 62
	
	//look at for office 70, 45
	// reds 64, 67, 82, 20
	
	//public BasicLight area2CoolTop = addBasicLight("Area 2 Top Cool", 41); //other left 45, make dim
	//public BasicLight area1TopFocused = addBasicLight("Area 1 Top Focused", 39); //other left 45, make dimm
	
	//public BasicLight middleScreenFront = addBasicLight("Middle Screen Front", 46); //bleeds onto left screen
	//public BasicLight middleScreenFront45 = addBasicLight("Middle Screen Front Right 45", 51); //bleeds onto left screen
	//public BasicLight rightScreenFront = addBasicLight("Area 1 Hot Back", 35); //bleeds onto left screen
	
	//public BasicLight leftScreenBackHot = addBasicLight("Area 1 Hot Back", 35); //bleeds onto left screen
	//public BasicLight rightScreenBackHot = addBasicLight("Area 1 Hot Back", 75); //bleeds onto left screen
	
	//public BasicLight coolLightRight = addBasicLight("Area 1 Hot Back", 65); //bleeds onto left screen
	

	//public BasicLight frontTop = addBasicLight("Red Top", 20); //bleeds onto left screen
	
	//public BasicLight rightWork = addBasicLight("Red Top", 97); //bleeds onto left screen
	
//	public ColorFusionLight leftScroller = addColorFusionLight(new BasicLight("Left Scroller", 42),
//			new BasicScroller("Position 1", 160, 1),
//			new BasicScroller("Position 2", 160, 2));
//	
//	public ColorFusionLight rightScroller = addColorFusionLight(new BasicLight("Right Scroller", 53),
//			new BasicScroller("Position 1", 160, 15),
//			new BasicScroller("Position 2", 160, 16));
	
	//Aqua blue
	//Royal purple
	
	//house
	// 107 108
	
	//53 right scroller intensity
	//(160 + 15) = 175 X
	//(160 + 16) = 176 Y
	
	//42 left scroller intensity
	//(160 + 0) 160
	//(160 + 1) 161
}
