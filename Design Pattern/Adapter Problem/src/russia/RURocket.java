package russia;

/**
 * Control system for Russian Rockets.
 * 
 * @author ripke1tj
 *
 */
public interface RURocket {

	/**
	 * Terminates an active launch.
	 */
	void terminate();

	/**
	 * Initiates a take off.
	 */
	void takeOff();

}
