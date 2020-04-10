import java.util.ArrayList;
import java.util.List;

import russia.RURocket;
import usa.USARocket;

/**
 * Control panel for rockets owned by the USA.
 * 
 * @author Jaemin Oh
 *
 */
public class RocketControlPanel {

	List<USARocket> rockets = new ArrayList<USARocket>();

	public void addRocket(USARocket rocket) {
		rockets.add(rocket);
	}
	
	
	public void takeOff() {
		for (USARocket rocket : rockets) {
			rocket.launch();
		}
	}

	public void abort() {
		for (USARocket rocket : rockets) {
			rocket.abort();
		}
	}

}
