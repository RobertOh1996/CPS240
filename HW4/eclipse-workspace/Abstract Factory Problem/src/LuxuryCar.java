/**
 * Luxury Car concrete implementation of Car.
 * 
 * @author Jaemin Oh
 *
 */
public class LuxuryCar extends Car {

	public LuxuryCar(Location location) {
		super(CarType.LUXURY, location);
		construct();
	}

	@Override
	void construct() {
		System.out.println("Building a Luxury Car...");
	}

}