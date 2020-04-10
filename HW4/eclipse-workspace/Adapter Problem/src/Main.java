import russia.RURocket;
import russia.SoyuzRocket;
import usa.NasaRocket;
import usa.USARocket;

/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		RocketControlPanel missionControl = new RocketControlPanel();
		missionControl.addRocket(new NasaRocket());
		missionControl.addRocket(new RocketAdapter(new SoyuzRocket()));
		
		missionControl.takeOff();
		missionControl.abort();
		
	}

}
