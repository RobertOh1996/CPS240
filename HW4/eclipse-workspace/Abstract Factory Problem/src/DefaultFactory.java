
public class DefaultFactory {
	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SUV:
			car = new SUV(Location.DEFAULT);
			break;

		case SPORTS:
			car = new SportsCar(Location.DEFAULT);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.DEFAULT);
			break;

		default:
			break;

		}
		return car;
	}
}
