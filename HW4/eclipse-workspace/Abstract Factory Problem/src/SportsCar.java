/**
 * Sports Car concrete implementation of Car.
 * 
 * @author Jaemin Oh
 *
 */
public class SportsCar extends Car {

	
	public SportsCar(Location location) {
		super(CarType.SPORTS, location);
		construct();
	}

	@Override
	void construct() {
		System.out.println("Building a Sports Car...");
	}

}