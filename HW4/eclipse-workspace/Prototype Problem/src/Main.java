/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		ShapeCache.loadCache();
		System.out.println("Shape: " + ShapeCache.getShape("1").getType());
		System.out.println("Shape: " + ShapeCache.getShape("2").getType());
	}

}
