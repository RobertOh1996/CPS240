import russia.RURocket;
import russia.RocketAdapter;
import russia.SoyuzRocket;
import usa.NasaRocket;
import usa.USARocket;

/**
 * Entry point for the application.
 * 
 * @author 
 *
 */
public class Main {

	public static void main(String[] args) {
		RocketControlPanel p = new RocketControlPanel();
		USARocket rocket1 = new NasaRocket();
		p.addRocket(rocket1);
		System.out.print("rocket1 ");
		p.takeOff();
		System.out.print("rocket1 ");
		p.abort();
		USARocket rocket2 = new NasaRocket();
		
		
	}

}
