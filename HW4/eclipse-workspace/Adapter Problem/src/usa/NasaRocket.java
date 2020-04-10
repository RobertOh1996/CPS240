package usa;

/**
 * A basic representation of a Nasa Rocket.
 * 
 * @author Jaemin Oh
 *
 */
public class NasaRocket implements USARocket {

	
	@Override
	public void abort() {
		System.out.println("Aborting...");
	}

	@Override
	public void launch() {
		System.out.println("Launching...");
	}

}
