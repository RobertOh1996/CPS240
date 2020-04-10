package usa;

/**
 * Control system for USA Rockets.
 * 
 * @author Jaemin Oh
 *
 */
public interface USARocket {
	/**
	 * Aborts an active launch.
	 */
	void abort();

	/**
	 * Initiates a launch.
	 */
	void launch();

}
