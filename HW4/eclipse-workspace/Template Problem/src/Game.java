
public abstract class Game {

	// Template
	public final void play() {
		initialize();
		startPlay();
		endPlay();
	}

	abstract void initialize();

	abstract void startPlay();

	abstract void endPlay();

}
