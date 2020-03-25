/**
 * Luxury Car concrete implementation of Car.
 * 
 * @author ripke1tj
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