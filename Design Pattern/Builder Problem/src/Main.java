/**
 * Entry point for the application.
 * 
 * @author
 *
 */
public class Main {

	public static void main(String[] args) {
		User user = new User.UserBuilder("Betty", "White").age(98).build();
	}

}
