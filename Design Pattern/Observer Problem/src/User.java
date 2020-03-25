import java.util.ArrayList;
import java.util.List;

public class User implements Observable, Observer {

	private String name;
	private List<Observer> followers;

	public User(String name) {
		this.name = name;
		this.followers = new ArrayList<Observer>();
	}

	public void tweet(String tweet) {
		notifyObservers(tweet);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.printf("Notification for %s\nRecent Tweet from %s:\n%s\n", this.name, o.getName(), (String) arg);
	}

	@Override
	public void addFollower(Observer o) {
		
	}

	@Override
	public void removeFollower(Observer o) {
		
	}

	@Override
	public void notifyObservers(String tweet) {
		
	}

	@Override
	public String getName() {
		return this.name;
	}

}
