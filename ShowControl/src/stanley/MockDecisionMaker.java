package stanley;

import java.time.Duration;

public class MockDecisionMaker implements DecisionMaker {

	private String result;
	private Duration waitTime;
	
	public MockDecisionMaker(String result, Duration waitTime)
	{
		this.result = result;
		this.waitTime = waitTime;
	}
	
	@Override
	public String makeDecision() {
		try {
			Thread.sleep(waitTime.toMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
