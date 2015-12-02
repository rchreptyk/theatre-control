package stanley.views;

import java.time.Duration;

import lighting.LightingLoop;
import lighting.LightingView;
import lighting.change.IntensityChange;
import stanley.StanleyLightingConfig;

public class LightingViews {
	
	private LightingLoop loop;
	private StanleyLightingConfig lights;
	
	public LightingViews(LightingLoop loop, StanleyLightingConfig lights)
	{
		this.loop = loop;
		this.lights = lights;
	}
	
	public LightingView getTopDown(Duration fade)
	{
		LightingView view = new LightingView(loop, fade);
		view.addChange(new IntensityChange(lights.area2Top, 80));
		return view;
	}
	
	public LightingView getTopDownBlack(Duration fade)
	{
		LightingView view = new LightingView(loop, fade);
		view.addChange(new IntensityChange(lights.area2Top, 0));
		return view;
	}
	
	public LightingView getOfficeView(Duration fade)
	{
		LightingView officeView = new LightingView(loop, fade);
		officeView.addChange(new IntensityChange(lights.area2Top, 60));
		
		officeView.addChange(new IntensityChange(lights.secondTop, 80));
		officeView.addChange(new IntensityChange(lights.area2Front, 80));
		officeView.addChange(new IntensityChange(lights.area3Top, 80));
		officeView.addChange(new IntensityChange(lights.area1Top, 80));
		
		return officeView;
	}
	
	public LightingView getOfficeViewBlack(Duration fade)
	{
		LightingView officeView = new LightingView(loop, fade);
		officeView.addChange(new IntensityChange(lights.secondTop, 0));
		officeView.addChange(new IntensityChange(lights.area2Front, 0));
		officeView.addChange(new IntensityChange(lights.area3Top, 0));
		officeView.addChange(new IntensityChange(lights.area1Top, 0));
		return officeView;
	}
	
	public LightingView getPaintingView(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.rightPainting, 100));
		houseView.addChange(new IntensityChange(lights.middlePainting, 100));
		houseView.addChange(new IntensityChange(lights.leftPainting, 100));
		return houseView;
	}
	
	public LightingView getPaintingViewOff(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.rightPainting, 0));
		houseView.addChange(new IntensityChange(lights.middlePainting, 0));
		houseView.addChange(new IntensityChange(lights.leftPainting, 0));
		return houseView;
	}
	
	public LightingView getDecisionView(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.backHallLightLeft, 80));
		houseView.addChange(new IntensityChange(lights.backHallLightRight, 80));
		return houseView;
	}
	
	public LightingView getDecisionViewOff(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.backHallLightLeft, 0));
		houseView.addChange(new IntensityChange(lights.backHallLightRight, 0));
		return houseView;
	}
	
	public LightingView getHouseView(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.houseOne, 100));
		houseView.addChange(new IntensityChange(lights.houseTwo, 100));
		return houseView;
	}
	
	public LightingView getHouseViewOff(Duration fade)
	{
		LightingView houseView = new LightingView(loop, fade);
		houseView.addChange(new IntensityChange(lights.houseOne, 0));
		houseView.addChange(new IntensityChange(lights.houseTwo, 0));
		return houseView;
	}
	
	public LightingView getBossesOfficeView(Duration fade)
	{
		LightingView officeView = new LightingView(loop, fade);
		officeView.addChange(new IntensityChange(lights.area2Top, 40));
		
		officeView.addChange(new IntensityChange(lights.secondTop, 60));
		officeView.addChange(new IntensityChange(lights.area2Front, 60));
		officeView.addChange(new IntensityChange(lights.area3Top, 60));
		officeView.addChange(new IntensityChange(lights.area1Top, 60));
		officeView.addChange(new IntensityChange(lights.rightWarmBack, 80));
		officeView.addChange(new IntensityChange(lights.leftWarmBack, 80));
		officeView.addChange(new IntensityChange(lights.rightWarmSide, 80));
		return officeView;
	}
	
	public LightingView getBossesOfficeViewOff(Duration fade)
	{
		LightingView officeView = new LightingView(loop, fade);
		officeView.addChange(new IntensityChange(lights.area2Top, 0));
		
		officeView.addChange(new IntensityChange(lights.secondTop, 0));
		officeView.addChange(new IntensityChange(lights.area2Front, 0));
		officeView.addChange(new IntensityChange(lights.area3Top, 0));
		officeView.addChange(new IntensityChange(lights.area1Top, 0));
		officeView.addChange(new IntensityChange(lights.rightWarmBack, 0));
		officeView.addChange(new IntensityChange(lights.leftWarmBack, 0));
		officeView.addChange(new IntensityChange(lights.rightWarmSide, 0));
		return officeView;
	}
	
	public LightingView getSunLight(Duration fade)
	{
		LightingView view = new LightingView(loop, fade);
		view.addChange(new IntensityChange(lights.area2Left45, 40));
		view.addChange(new IntensityChange(lights.area2Right45, 40));
		
		return view;
	}
	
	public LightingView getSunLightOff(Duration fade)
	{
		LightingView view = new LightingView(loop, fade);
		view.addChange(new IntensityChange(lights.area2Left45, 0));
		view.addChange(new IntensityChange(lights.area2Right45, 0));
		
		return view;
	}
}
