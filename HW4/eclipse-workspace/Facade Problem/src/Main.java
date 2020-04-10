/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		ShapeFacade shapeMaker = new ShapeFacade();
		
		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
	}

}
