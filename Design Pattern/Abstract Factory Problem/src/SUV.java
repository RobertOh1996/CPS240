/**
 * SUV concrete implementation of Car.
 * 
 * @author ripke1tj
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
