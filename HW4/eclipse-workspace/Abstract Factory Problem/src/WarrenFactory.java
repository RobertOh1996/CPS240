/**
 * 
 * @author Jaemin Oh
 *
 */
public class WarrenFactory {

	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SUV:
			car = new SUV(Location.WARREN);
			break;

		case SPORTS:
			car = new SportsCar(Location.WARREN);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.WARREN);
			break;

		default:
			break;

		}
		return car;
	}
}
