package stanley;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

public class WebRaceDecisionMaker implements DecisionMaker {

	private static Logger LOG = Logger.getLogger("Race Decision Maker");
	private String buttonText;
	private WebPuller puller;
	private TextUpdater updater;
	
	public WebRaceDecisionMaker(WebPuller puller, TextUpdater updater, String buttonText)
	{
		this.puller = puller;
		this.updater = updater;
		this.buttonText = buttonText;
	}
	
	@Override
	public String makeDecision() {
		try
		{
			HashSet<String> choices = new HashSet<String>();
			choices.add(buttonText);
			
			LOG.info("Sending request");
			puller.sendDecision(choices);
			
			Thread.sleep(5000);
			
			int maxCutOff = 2 * 60 * 1000;
			int timeSlept = 0;
			
			while(timeSlept < maxCutOff)
			{
				HashMap<String, String> results = puller.getResults();
				
				String resultString = "";
				
				for(String key : results.keySet())
				{
					resultString += key + ": " + results.get(key) + "\n";
				}
				
				updater.updateText(resultString);				
				
				if(results.size() > 0)
				{
					Thread.sleep(2000);
					updater.updateText("");
					
					puller.closePoll();
					return "Voted";
				}
				
				Thread.sleep(1000);
				timeSlept += 1000;
			}
			
			puller.closePoll();
			return "Timeout";
			
		}
		catch(InterruptedException ex)
		{
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "Connection Failure";
	}

}
