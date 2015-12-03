package stanley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

public class WebDecisionMaker implements DecisionMaker {

	private static String host = "http://russellc.ca"; 
	
	private String failureChoice; 
	private Set<String> choices;
	
	public WebDecisionMaker(Set<String> choices, String failureChoice)
	{
		this.choices = choices;
		this.failureChoice = failureChoice;
	}
	
	@Override
	public String makeDecision() {
		
		String decisionRequest = host + "/ask";
		
		for(String choice : choices)
		{
			decisionRequest += "/";
			decisionRequest += choice;
		}
		
		
		URL webUrl;
		try {
			webUrl = new URL(decisionRequest);
			URLConnection yc = webUrl.openConnection();
			JsonReader reader = Json.createReader();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return failureChoice;
		} catch (IOException e) {
			e.printStackTrace();
			return failureChoice;
		}
	}

}
