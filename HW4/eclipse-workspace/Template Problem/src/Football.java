
public class Football extends Game {

	@Override
	void initialize() {
		System.out.println("Gathering the football players...");
	}

	@Override
	void startPlay() {
		System.out.println("Start of the first quarter!");
	}

	@Override
	void endPlay() {
		System.out.println("End of the fourth quarter! CMU wins!");
	}

}
