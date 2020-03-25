package usa;

/**
 * Control system for USA Rockets.
 * 
 * @author ripke1tj
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
