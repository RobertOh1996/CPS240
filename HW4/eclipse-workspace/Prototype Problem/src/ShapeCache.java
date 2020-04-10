/**
 * Hashtable supports synchronization in the multi-thread environment, but HashMap is not.
 * @author Jaemin Oh
 */
import java.util.Hashtable;

public class ShapeCache {
	private static Hashtable<String, Shape> shapeMap = new Hashtable<String, Shape>();

	public static Shape getShape(String shapeId) {
		Shape cachedShape = shapeMap.get(shapeId);
		return (Shape) cachedShape.clone();
	}

	public static void loadCache() {
		Circle circle = new Circle();
		Rectangle rectangle = new Rectangle();
		circle.setId("1");
		rectangle.setId("2");
		shapeMap.put(circle.getId(), circle);
		shapeMap.put(rectangle.getId(), rectangle);
	}
}