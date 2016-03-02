package stanley;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;


public class WebDecisionMaker implements DecisionMaker {

	private static Logger LOG = Logger.getLogger("Decision Maker");
	
	private WebPuller puller;
	private TextUpdater updater;
	
	private String failureChoice; 
	private Set<String> choices;
	
	public WebDecisionMaker(WebPuller puller, TextUpdater updater, Set<String> choices, String failureChoice)
	{
		this.updater = updater;
		this.puller = puller;
		this.choices = choices;
		this.failureChoice = failureChoice;
	}
	
	@Override
	public String makeDecision() {
		
		try 
		{
			LOG.info("Sending request");
			puller.sendDecision(choices);
			
			int numUsers = puller.getUsers().size();
			
			int sleepTime = 30000;
			int timeSlept = 0;
			
			Thread.sleep(5000);
			
			while(timeSlept < sleepTime)
			{
				HashMap<String, String> results = puller.getResults();
				
				String resultString = "";
				
				for(String key : results.keySet())
				{
					resultString += key + ": " + results.get(key) + "\n";
				}
				
				updater.updateText(resultString);
				
				if(results.size() >= numUsers)
				{
					break;
				}
				
				Thread.sleep(1000);
				timeSlept += 1000;
			}
			
			HashMap<String, String> resultMap = puller.getResults();
			
			HashMap<String, Integer> voteMap = new HashMap<String, Integer>();
			
			for(String choice : choices)
			{
				voteMap.put(choice, 0);
			}
			
			Collection<String> votes = resultMap.values();
			for(String vote : votes)
			{
				LOG.info("Vote: " + vote);
				if(voteMap.containsKey(vote))
				{
					LOG.info("Counting vote" + vote);
					voteMap.put(vote, voteMap.get(vote) + 1);
				}
			}
			
			String result = failureChoice;
			int count = 0;
			
			for(String choice : voteMap.keySet())
			{
				int choiceCount = voteMap.get(choice);
				if(choiceCount > count)
				{
					choiceCount = count;
					result = choice;
				}
			}
			
			updater.updateText(result);
			puller.closePoll();
			Thread.sleep(2000);

			updater.updateText("");
			
			return result;
			
		} catch (MalformedURLException e) {
			LOG.info("Incorrect URL");
			e.printStackTrace();
		} catch (IOException e) {
			LOG.info("Failed to read");
			e.printStackTrace();
		} catch (InterruptedException e) {
			LOG.info("Thread canceled");
			e.printStackTrace();
			
		}
		
		return failureChoice;
	}

}
