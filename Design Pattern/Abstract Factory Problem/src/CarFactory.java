
public class CarFactory {
	
	private static Car car;
	private static CarFactory carfactory = null;
	
	private CarFactory(){
		
	}
	
	public static CarFactory getCarFactory() {
		if(carfactory == null) {
			carfactory = new CarFactory();
		}
		return carfactory;
	}
	
	public static Car buildCar(CarType type, Location location) {		
		switch(location){
			case DEARBORN:
				car = DearbornFactory.buildCar(type);
				break;
			case TOLEDO:
				car = ToledoFactory.buildCar(type);
				break;
			case WARREN:
				car = WarrenFactory.buildCar(type);
				break;
			default:
				car = DefaultFactory.buildCar(type);
				break;
		}
			
		return car;
	}
}
