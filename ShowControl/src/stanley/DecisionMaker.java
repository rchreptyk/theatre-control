package stanley;


public interface DecisionMaker {
	/* Should block until decision is reached. Should take a max of timeout to return */
	public String makeDecision();
}
