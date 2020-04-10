import russia.RURocket;
import usa.USARocket;

public class RocketAdapter implements USARocket {
	
	private RURocket rocket;
	
	public RocketAdapter(RURocket rocket) {
		this.rocket = rocket;
	}
	
	@Override
	public void abort() {
		rocket.terminate();		
	}

	@Override
	public void launch() {
		rocket.takeOff();		
	}

}
