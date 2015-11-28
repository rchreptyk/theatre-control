package controls;

public interface View {
	/**
	 * Start rendering the view. This method must not block.
	 * @return A view control to stop the view from rendering.
	 */
	Action render();
}
