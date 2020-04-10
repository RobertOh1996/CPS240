/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		User user = new User("Taylor Ripke");
		User user2 = new User("Alan Warner");
		User user3 = new User("Dylan kelly");
		
		user.addFollower(user2);
		user.addFollower(user3);
		user.tweet("Testing!");
	}

}
