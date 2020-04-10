
public class DearbornFactory {

	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case SUV:
			car = new SUV(Location.DEARBORN);
			break;

		case SPORTS:
			car = new SportsCar(Location.DEARBORN);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.DEARBORN);
			break;

		default:
			break;

		}
		return car;
	}
}
