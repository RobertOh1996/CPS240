/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		Game game = new Football();
		game.play();
		Game game2 = new Basketball();
		game2.play();		
	}
}
