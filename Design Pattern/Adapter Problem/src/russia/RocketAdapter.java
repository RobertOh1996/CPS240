package russia;

import usa.USARocket;

public class RocketAdapter implements USARocket {
	RURocket ruRocket;
	
	public RocketAdapter(RURocket ruRocket) {
		this.ruRocket = ruRocket;
	}

	
	public void abort(RURocket ruRocket) {
		ruRocket.terminate();
		
	}

	
	public void launch(RURocket ruRocket) {
		ruRocket.takeOff();
		
	}


	@Override
	public void abort() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void launch() {
		// TODO Auto-generated method stub
		
	}


	
}
