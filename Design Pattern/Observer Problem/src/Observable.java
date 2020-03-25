
/**
 * An observable object can have one or more observers. After an observable
 * instance changes, an application calling the Observable's notifyObservers
 * method causes all of its observers to be notified of the change by a call to
 * their update method.
 * 
 * @author ripke1tj
 *
 */
public interface Observable {

	public void addFollower(Observer o);

	public void removeFollower(Observer o);

	public void notifyObservers(String tweet);

	public String getName();

}
