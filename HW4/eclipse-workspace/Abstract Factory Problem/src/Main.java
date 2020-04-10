/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(CarFactory.buildCar(CarType.LUXURY, Location.DEARBORN));
		System.out.println(CarFactory.buildCar(CarType.SPORTS, Location.TOLEDO));
	}

}
