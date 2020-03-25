
public class ToledoFactory {

	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SUV:
			car = new SUV(Location.TOLEDO);
			break;

		case SPORTS:
			car = new SportsCar(Location.TOLEDO);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.TOLEDO);
			break;

		default:
			break;

		}
		return car;
	}
}
