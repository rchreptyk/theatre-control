package stanley;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gson.stream.JsonReader;

public class WebPuller {
	private static Logger LOG = Logger.getLogger("Web Puller");
	private String host;
	
	public WebPuller(String host)
	{
		this.host = host;
	}
	
	public void startShow() throws IOException
	{
		getRequest("/start");
	}
	
	public void ping() throws IOException
	{
		HttpURLConnection connection = getRequest("/ping");
		InputStreamReader input = new InputStreamReader(connection.getInputStream());
		
		char result[] = new char[4];
		input.read(result);
		
		if(!"pong".equals(result))
		{
			throw new RuntimeException("Did not return correct result, recieved: " + result.toString());
		}
	}
	
	public void sendDecision(Set<String> choices) throws IOException
	{
		String decisionRequest = "/ask";
		
		for(String choice : choices)
		{
			decisionRequest += "/";
			decisionRequest += choice.replace(" ", "%20");
		}
		
		getRequest(decisionRequest);
	}
	
	public void closePoll() throws IOException
	{
		getRequest("/close");
	}
	
	public HashMap<String, String> getResults() throws IOException
	{
		HttpURLConnection connection = getRequest("/results");
		
		HashMap<String, String> resultMap = new HashMap<String, String>();
		
		try(JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream())))
		{
			reader.beginArray();
			while (reader.hasNext()) {
				reader.beginObject();
				reader.nextName(); //TODO: Check this
				String name = reader.nextString();
				reader.nextName(); //TODO: Check this
			    String vote = reader.nextString();
			    LOG.info(name + " voted " + vote);
			    resultMap.put(name, vote);
			    reader.endObject();
			}
			reader.endArray();
		}
		
		return resultMap;
	}
	
	public ArrayList<String> getUsers() throws IOException
	{
		ArrayList<String> users = new ArrayList<>();
		
		HttpURLConnection connection = getRequest("/users");
		try(JsonReader reader = new JsonReader(new InputStreamReader(connection.getInputStream())))
		{
			reader.beginArray();
			while (reader.hasNext()) {
			    users.add(reader.nextString());
			}
			reader.endArray();
		}
		
		return users;
	}
	
	private HttpURLConnection getRequest(String path) throws IOException
	{
		String fullPath = host + path;
		
		URL webUrl = new URL(fullPath);
		HttpURLConnection connect = (HttpURLConnection)webUrl.openConnection();
		connect.setRequestMethod("GET");
		connect.connect();
		
		int responseCode = connect.getResponseCode();
		if(responseCode != 200)
		{
			throw new RuntimeException("Invalid response code of " + responseCode);
		}
		
		return connect;
	}
}
