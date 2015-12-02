package lighting.change;

import java.util.ArrayList;
import java.util.List;

public class ChangeCalculator {
	int from;
	int to;
	
	public ChangeCalculator(int from, int to)
	{
		this.from = from;
		this.to = to;
	}
	
	public List<Integer> getChanges(int count)
	{
		ArrayList<Integer> changeList = new ArrayList<>();
		
		int difference = to - from;
		
		double changePerTick = (double)difference / (double)count;
		double nextAmount = from + changePerTick;
	
		for(int i = 0; i < count - 1; i++)
		{
			int roundedAmount = (int) Math.round(nextAmount);
			changeList.add(roundedAmount);
			nextAmount += changePerTick;
		}
		
		changeList.add(to);
		
		return changeList;
	}
}
