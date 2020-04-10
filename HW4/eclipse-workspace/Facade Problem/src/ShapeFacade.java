/**
 * 
 * @author Jaemin Oh
 *
 */
public class ShapeFacade {
	Shape circle;
	Shape rectangle;
	
	public ShapeFacade() {
		circle = new Circle();
		rectangle = new Rectangle();
	}
	
	public void drawCircle() {
		circle.draw();
	}
	
	public void drawRectangle() {
		rectangle.draw();
	}
}
