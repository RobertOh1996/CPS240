/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {
	public static void main(String[] args) {
		Context context = new Context(new AddOperation());
		System.out.println("10 + 30: " + context.executeStrategy(10, 30));
		context = new Context(new SubtractOperation());
		System.out.println("10 - 30: "+ context.executeStrategy(10, 30));
	}
}
