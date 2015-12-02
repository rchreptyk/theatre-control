package lighting.intruments.colorfusion;

public class ColorFusionColor {
	private int position1;
	private int position2;
	
	private ColorFusionColor(int position1, int position2)
	{
		this.position1 = position1;
		this.position2 = position2;
	}
	
	public int getPosition1()
	{
		return position1;
	}
	
	public int getPosition2()
	{
		return position2;
	}
	
	public static final ColorFusionColor MED_BLU_GRN = new ColorFusionColor(0, 161);
	public static final ColorFusionColor PRIMARY_GREEN = new ColorFusionColor(0, 242);
	public static final ColorFusionColor DARK_BLUE = new ColorFusionColor(10, 0);
	public static final ColorFusionColor MEDIUM_BLUE = new ColorFusionColor(10, 10);
	public static final ColorFusionColor PRIMARY_BLUE = new ColorFusionColor(10, 23);
	public static final ColorFusionColor BRILLIANT_BLUE = new ColorFusionColor(10, 69);
	public static final ColorFusionColor OFF_BLUE = new ColorFusionColor(10, 79);
	public static final ColorFusionColor DARK_GREEN = new ColorFusionColor(10, 217);
	public static final ColorFusionColor DRK_YELL_GRN = new ColorFusionColor(10, 230);
	public static final ColorFusionColor CONGO_BLUE = new ColorFusionColor(23, 0);
	public static final ColorFusionColor SAPPHIRE_BLUE = new ColorFusionColor(23, 10);
	public static final ColorFusionColor AZTEC_BLUE = new ColorFusionColor(23, 33);
	public static final ColorFusionColor MOON_BLUE = new ColorFusionColor(23, 46);
	public static final ColorFusionColor LIGHT_BLUE = new ColorFusionColor(23, 69);
	public static final ColorFusionColor TWILIGHT = new ColorFusionColor(33, 10);
	public static final ColorFusionColor AQUA_BLUE = new ColorFusionColor(33, 79);
	public static final ColorFusionColor PRINCESS_BLUE = new ColorFusionColor(33, 102);
	public static final ColorFusionColor ROYAL_PURPLE = new ColorFusionColor(46, 0);
	public static final ColorFusionColor CITY_BLUE = new ColorFusionColor(46, 46);
	public static final ColorFusionColor MOODY_BLUE = new ColorFusionColor(46, 56);
	public static final ColorFusionColor DAYLIGHT_BLUE = new ColorFusionColor(46, 69);
	public static final ColorFusionColor ROSE_INDIGO = new ColorFusionColor(56, 0);
	public static final ColorFusionColor PURPLE = new ColorFusionColor(56, 23);
	public static final ColorFusionColor LIGHT_STEEL_BLUE = new ColorFusionColor(56, 56);
	public static final ColorFusionColor LAVENDER = new ColorFusionColor(69, 33);
	public static final ColorFusionColor LIGHT_PURPLE = new ColorFusionColor(69, 46);
	public static final ColorFusionColor FULL_CT_BLUE = new ColorFusionColor(69, 69);
	public static final ColorFusionColor STEEL_BLUE = new ColorFusionColor(69, 92);
	public static final ColorFusionColor PEA_GREEN = new ColorFusionColor(69, 217);
	public static final ColorFusionColor MEDIUM_LAVENDER = new ColorFusionColor(79, 46);
	public static final ColorFusionColor ELECTRIC_BLUE = new ColorFusionColor(79, 92);
	public static final ColorFusionColor ORCHID = new ColorFusionColor(92, 10);
	public static final ColorFusionColor DARK_LAVENDER = new ColorFusionColor(92, 33);
	public static final ColorFusionColor LIGHT_LAVENDER = new ColorFusionColor(92, 56);
	public static final ColorFusionColor SPECIAL_LAVENDER = new ColorFusionColor(92, 79);
	public static final ColorFusionColor ROSE_PURPLE = new ColorFusionColor(102, 23);
	public static final ColorFusionColor QUATER_CT_BLUE = new ColorFusionColor(102, 102);
	public static final ColorFusionColor YELLOW = new ColorFusionColor(102, 242);
	public static final ColorFusionColor FOLIES_PINK = new ColorFusionColor(115, 23);
	public static final ColorFusionColor MEDIUM_YELLOW = new ColorFusionColor(125, 230);
	public static final ColorFusionColor CANARY = new ColorFusionColor(138, 230);
	public static final ColorFusionColor PALE_AMBER_GOLD = new ColorFusionColor(148, 184);
	public static final ColorFusionColor MEDIUM_STRAW = new ColorFusionColor(148, 230);
	public static final ColorFusionColor FULL_CT_ORANGE = new ColorFusionColor(161, 217);
	public static final ColorFusionColor DEEP_STRAW = new ColorFusionColor(161, 230);
	public static final ColorFusionColor CHROME_ORANGE = new ColorFusionColor(171, 230);
	public static final ColorFusionColor MEDIUM_PINK = new ColorFusionColor(194, 161);
	public static final ColorFusionColor MAYAN_SUN = new ColorFusionColor(194, 207);
	public static final ColorFusionColor DARK_PINK = new ColorFusionColor(207, 161);
	public static final ColorFusionColor ORANGE = new ColorFusionColor(207, 230);
	public static final ColorFusionColor MIDDLE_ROSE = new ColorFusionColor(217, 138);
	public static final ColorFusionColor MEDIUM_SALMON_PINK = new ColorFusionColor(217, 207);
	public static final ColorFusionColor DEEP_AMBER = new ColorFusionColor(217, 242);
	public static final ColorFusionColor FIRE = new ColorFusionColor(230, 242);
	public static final ColorFusionColor EXOTIC_SANGRIA = new ColorFusionColor(242, 148);
	public static final ColorFusionColor MAGENTA = new ColorFusionColor(242, 194);
	public static final ColorFusionColor LIGHT_RED = new ColorFusionColor(242, 242);
	public static final ColorFusionColor DARK_ROSE = new ColorFusionColor(230, 125);
	public static final ColorFusionColor BRIGHT_PINK = new ColorFusionColor(230, 148);
	public static final ColorFusionColor ROSE = new ColorFusionColor(207, 138);
	public static final ColorFusionColor LIGHT_PINK = new ColorFusionColor(161, 138);
	public static final ColorFusionColor CHORUS_PINK = new ColorFusionColor(171, 138);
	public static final ColorFusionColor CHERRY = new ColorFusionColor(230, 194);
	public static final ColorFusionColor AUTUMN_GLORY = new ColorFusionColor(207, 242);
	public static final ColorFusionColor BASTARD_AMBER = new ColorFusionColor(148, 148);
	public static final ColorFusionColor SEPIA = new ColorFusionColor(161, 161);
	public static final ColorFusionColor CORAL = new ColorFusionColor(194, 242);
	public static final ColorFusionColor HONEY = new ColorFusionColor(171, 207);
	public static final ColorFusionColor SAFFRON = new ColorFusionColor(138, 217);
	public static final ColorFusionColor PALE_GREEN = new ColorFusionColor(92, 171);
	public static final ColorFusionColor GRASS_GREEN = new ColorFusionColor(33, 217);
	public static final ColorFusionColor MED_GREEN = new ColorFusionColor(46, 207);
	public static final ColorFusionColor KELLY_GREEN = new ColorFusionColor(46, 161);
	public static final ColorFusionColor PISTACHIO = new ColorFusionColor(33, 161);
	public static final ColorFusionColor BLUEGRASS = new ColorFusionColor(23, 138);
	public static final ColorFusionColor BLUE_GREEN = new ColorFusionColor(46, 92);
}
