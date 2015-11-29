package lighting;

public interface DMXFrameTransmitter extends AutoCloseable {
	public void send(DMXFrame frame);
}
