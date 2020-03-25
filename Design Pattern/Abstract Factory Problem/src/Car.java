/**
 * Basic representation of a car.
 * 
 * @author ripke1tj
 *
 */
public abstract class Car {

	private CarType model;
	private Location location;

	public Car(CarType model, Location location) {
		this.model = model;
		this.location = location;
	}

	abstract void construct();

	@Override
	public String toString() {
		return "Car [model=" + model + ", location=" + location + "]";
	}

}
