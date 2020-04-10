/**
 * @author Jaemin Oh
 */
package russia;

public class SoyuzRocket implements RURocket {

	@Override
	public void terminate() {
		System.out.println("Aborting...");
	}

	@Override
	public void takeOff() {
		System.out.println("Launching...");
	}

}
