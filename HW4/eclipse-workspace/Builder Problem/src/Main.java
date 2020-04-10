/**
 * Entry point for the application.
 * 
 * @author Jaemin Oh
 *
 */
public class Main {

	public static void main(String[] args) {
		User user = new User.UserBuilder("Betty", "White").age(98).build();
		System.out.println(user);
		User user2 = new User
				.UserBuilder("Jaemin", "Oh")
				.age(25)
				.phone("989-572-3427")
				.build();
		System.out.println(user2);
		User user3 = new User
				.UserBuilder("Joonwoo", "Park")
				.age(28)
				.phone("916-0040-3674")
				.address("806 W Brommfield")
				.build();
		System.out.println(user3);
	}

}
