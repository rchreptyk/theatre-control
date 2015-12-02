package stanley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;

import controls.Event;
import controls.InstantEvent;

public class TestPromptEvent extends InstantEvent {

	@Override
	public void executeEvent() {
		System.out.println("Next?");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Did not confirm");
		}
		
		
	}


}
