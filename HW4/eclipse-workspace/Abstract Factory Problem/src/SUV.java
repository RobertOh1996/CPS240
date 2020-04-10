/**
 * SUV concrete implementation of Car.
 * 
 * @author Jaemin Oh
 *
 */
public class SUV extends Car {

	public SUV(Location location) {
		super(CarType.SUV, location);
		construct();
	}

	@Override
	void construct() {
		System.out.println("Building a SUV...");
	}

}
